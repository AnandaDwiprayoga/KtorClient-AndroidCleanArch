package com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
	val phone: String,
	val uuid: String
)

