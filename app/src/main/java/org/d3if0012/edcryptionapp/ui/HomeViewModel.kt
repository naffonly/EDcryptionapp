package org.d3if0012.edcryptionapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if0012.edcryptionapp.model.DataEncryption
import java.util.*

class HomeViewModel: ViewModel(){
        private val textEncry = MutableLiveData<DataEncryption?>()


     fun onEncode(encodeData:String,decodeData : String) {

        val encodeText = encodeData
        val decodeText = decodeData

        val encoder: Base64.Encoder = Base64.getEncoder()
        val encoded: String = encoder.encodeToString(encodeText.toByteArray())

        textEncry.value = DataEncryption(encodeText,encoded)
    }

     fun onDecode(encodeData:String,decodeData : String){
        val  encodeText =  encodeData
        val decodeText = decodeData

        val decoder: Base64.Decoder = Base64.getMimeDecoder()
        val decoded  = String(decoder.decode(decodeText))

        textEncry.value = DataEncryption(decoded,decodeText)
    }

    fun getDataEncrytion(): LiveData<DataEncryption?> = textEncry
}