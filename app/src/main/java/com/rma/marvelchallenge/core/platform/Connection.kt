package com.rma.marvelchallenge.core.platform

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Connection {
    const val API_TS = "1"
    const val API_PUBLIC_KEY = "3cc538a8155248d5c41c12e8fe54a2cb"
    const val API_PRIVATE_KEY = "981fb2db6a2212745bca2f69b783dfafcbb7ad9e"
    var API_HASH: String = md5("${API_TS}${API_PRIVATE_KEY}${API_PUBLIC_KEY}")
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    fun md5(s: String): String {
        val md5 = "MD5"
        try {
            // Create MD5 Hash
            val digest: MessageDigest = MessageDigest.getInstance(md5)
            digest.update(s.toByteArray())
            val messageDigest: ByteArray = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}