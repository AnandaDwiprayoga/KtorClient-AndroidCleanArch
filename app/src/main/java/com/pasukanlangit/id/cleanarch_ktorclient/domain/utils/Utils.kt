package com.pasukanlangit.id.cleanarch_ktorclient.domain.utils

import java.math.BigInteger
import java.security.MessageDigest

fun generateMd5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    val bigInt = BigInteger(1, md.digest(input.toByteArray(Charsets.UTF_8)))
    return String.format("%032x", bigInt)
}
