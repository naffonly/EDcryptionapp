package org.d3if0012.edcryptionapp.model

import org.d3if0012.edcryptionapp.db.EdcEntity
import java.util.*

fun EdcEntity.onEncode() : DataEncryption{

    val encodeText = encode
    val decodeText = decode

    val encoder: Base64.Encoder = Base64.getEncoder()
    val encoded: String = encoder.encodeToString(encodeText.toByteArray())

    return DataEncryption(encodeText,encoded)

}


fun EdcEntity.onDecode() : DataEncryption{
    val  encodeText =  encode
    val decodeText = decode

    val decoder: Base64.Decoder = Base64.getMimeDecoder()
    val decoded  = String(decoder.decode(decodeText))

    return DataEncryption(decoded,decodeText)

}

