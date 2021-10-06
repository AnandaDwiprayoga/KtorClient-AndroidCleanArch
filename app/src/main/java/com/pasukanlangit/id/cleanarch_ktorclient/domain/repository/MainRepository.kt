package com.pasukanlangit.id.cleanarch_ktorclient.domain.repository

import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Auth
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Login
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun login(login: Login, signature: String): Flow<LoginResponse>
    fun setAuth(uuid: String, token: String)
    fun getAuth(): Auth
}