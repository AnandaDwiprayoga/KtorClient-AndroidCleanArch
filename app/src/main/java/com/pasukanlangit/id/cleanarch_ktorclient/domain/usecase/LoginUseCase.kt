package com.pasukanlangit.id.cleanarch_ktorclient.domain.usecase

import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Login
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.LoginResponse
import com.pasukanlangit.id.cleanarch_ktorclient.domain.repository.MainRepository
import com.pasukanlangit.id.cleanarch_ktorclient.domain.utils.Resource
import com.pasukanlangit.id.cleanarch_ktorclient.domain.utils.generateMd5
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(noHp: String, password: String): Flow<Resource<LoginResponse>> = flow {
        try {
            if(noHp.isEmpty() || password.isEmpty()){
                emit(Resource.Error("Either phone or password cannot empty"))
                return@flow
            }

            emit(Resource.Loading())

            val uuid = "guest-${Math.random().toInt()}"
            val request = Login(
                phone = noHp,
                uuid = uuid
            )
            val signature = generateMd5(input = "$password$noHp")
            mainRepository.login(request, signature).collect { resp ->
                mainRepository.setAuth(
                    uuid = uuid,
                    token = resp.token
                )

                emit(Resource.Success(resp))
            }
        }catch (e: Exception){
            emit(Resource.Error(message = e.message ?: "Unknown Error"))
        }
    }
}