package com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network

import android.content.Context
import com.pasukanlangit.id.cleanarch_ktorclient.R
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto.LoginRequestDto
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto.LoginResponseDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*

class ApiService(
    private val client: HttpClient,
    private val context: Context
) {
   suspend fun login(login: LoginRequestDto, signature: String): LoginResponseDto =
        client.post {
            url("${context.getString(R.string.base_url)}users/login"){
                body = login
                headers {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                    append("signatureapps", signature)
                }
            }
        }

//    suspend fun login1(login: LoginRequestDto, signature: String): LoginResponseDto =
//        client.post {
//            url { path("${context.getString(R.string.base_url)}users/login") }
//
//            body = login
//        }





}