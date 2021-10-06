package com.pasukanlangit.id.cleanarch_ktorclient.data.utils

import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto.LoginRequestDto
import com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto.LoginResponseDto
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Login
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.LoginResponse

object Mapper {
    fun mapDtoToLoginRequest(login: Login) =
        LoginRequestDto(
            phone = login.phone,
            uuid = login.uuid
        )

    fun mapLoginResponseDtoToModel(loginResponseDto: LoginResponseDto) =
        LoginResponse(
            token = loginResponseDto.xAccessToken
        )
}