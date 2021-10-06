package com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
	val msg: String,
	val rc: String,
	val xAccessToken: String
)

