package com.pasukanlangit.id.cleanarch_ktorclient.data.repository

import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.ApiService
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.sharedpref.SharedPref
import com.pasukanlangit.id.cleanarch_ktorclient.data.utils.Mapper.mapDtoToLoginRequest
import com.pasukanlangit.id.cleanarch_ktorclient.data.utils.Mapper.mapLoginResponseDtoToModel
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Auth
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Login
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.LoginResponse
import com.pasukanlangit.id.cleanarch_ktorclient.domain.repository.MainRepository
import io.ktor.client.features.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(
    private val api: ApiService,
    private val sharedPref: SharedPref
): MainRepository {

    override fun login(login: Login, signature: String): Flow<LoginResponse> = flow {
        try {
            val loginDto = mapDtoToLoginRequest(login)
            val response = api.login(loginDto, signature)
            emit(mapLoginResponseDtoToModel(response))
        }catch(e: RedirectResponseException) {
            // 3xx - responses
            throw Exception(e.response.status.description)
        } catch(e: ClientRequestException) {
            // 4xx - responses
            throw Exception(e.response.status.description)
        } catch(e: ServerResponseException) {
            // 5xx - responses
            throw Exception(e.response.status.description)
        } catch(e: Exception) {
            throw Exception(e.message)
        }
    }

    override fun setAuth(uuid: String, token: String) {
        sharedPref.setAuth(uuid, token)
    }

    override fun getAuth(): Auth = sharedPref.getAuth()
}