package com.example.guava.oauth

import com.example.guava.oauth.access.AccessTokenDao
import com.example.guava.oauth.access.AccessTokenEntity
import com.example.guava.oauth.access.InvalidAccessTokenException
import com.example.guava.oauth.refresh.InvalidRefreshTokenException
import com.example.guava.oauth.refresh.RefreshTokenEntity
import com.example.guava.oauth.refresh.RefreshTokenDao
import javax.inject.Inject

class TokenRepository @Inject constructor(
    private val oAuthService: OAuthService,
    private val accessTokenDao: AccessTokenDao,
    private val refreshTokenDao: RefreshTokenDao

){
    suspend fun getToken(code: String): String {
        val accessToken = accessTokenDao.getToken()
        val refreshToken = refreshTokenDao.getToken()

        return if(accessToken == null) {
            fetchAccessToken(code)
        } else {
            if (isAccessTokenExpired(accessToken)) {
                fetchAccessTokenUsingRefreshToken(refreshToken)
            } else {
                accessToken.code
            }
        }
    }

    private fun isAccessTokenExpired(accessTokenEntity: AccessTokenEntity) =
        accessTokenEntity.expiresInSecs <= 0

    private suspend fun fetchAccessToken(code: String): String {
        val accessTokenResponse = oAuthService.requestAccessToken(
            clientId = OAuthConfig.CLIENT_ID,
            clientSecret = OAuthConfig.CLIENT_SECRET,
            grantType = OAuthConfig.GrantType.AUTHORIZATION_CODE.value,
            code = code
        )

        if (accessTokenResponse.isSuccessful) {
            //save in database (both access token table and refresh token table)
            return accessTokenResponse.body()!!.accessToken
        } else {
            throw InvalidAccessTokenException()
        }
    }

    private suspend fun fetchAccessTokenUsingRefreshToken(refreshTokenEntity: RefreshTokenEntity?): String {
        val refreshTokenResponse = oAuthService.refreshToken(
            clientId = OAuthConfig.CLIENT_ID,
            clientSecret = OAuthConfig.CLIENT_SECRET,
            grantType = OAuthConfig.GrantType.REFRESH_TOKEN.value,
            refreshToken = refreshTokenEntity!!.code
        )
        if (refreshTokenResponse.isSuccessful) {
            //save in database (both access token table and refresh token table)
            return refreshTokenResponse.body()!!.accessToken
        } else {
            throw InvalidRefreshTokenException()
        }
    }
}