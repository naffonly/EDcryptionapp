package org.d3if0012.edcryptionapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0012.edcryptionapp.db.EdcDao
import org.d3if0012.edcryptionapp.db.EdcEntity
import org.d3if0012.edcryptionapp.model.DataEncryption
import java.util.*

class HomeViewModel(private val db: EdcDao): ViewModel(){

        private val textEncry = MutableLiveData<DataEncryption?>()
    val data = db.getLastData()


     fun onEncode(encodeData:String,decodeData : String) {

        val encodeText = encodeData
        val decodeText = decodeData

        val encoder: Base64.Encoder = Base64.getEncoder()
        val encoded: String = encoder.encodeToString(encodeText.toByteArray())

        textEncry.value = DataEncryption(encodeText,encoded)

         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 val dataEncrip = EdcEntity(
                     encode = encodeText,
                     decode = encoded
                 )
                 db.insert(dataEncrip)
             }
         }
    }

     fun onDecode(encodeData:String,decodeData : String){
        val  encodeText =  encodeData
        val decodeText = decodeData

        val decoder: Base64.Decoder = Base64.getMimeDecoder()
        val decoded  = String(decoder.decode(decodeText))

        textEncry.value = DataEncryption(decoded,decodeText)
         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 val dataEncrip = EdcEntity(
                     encode = decoded,
                     decode = decodeText
                 )
                 db.insert(dataEncrip)
             }
         }
    }

    fun getDataEncrytion(): LiveData<DataEncryption?> = textEncry
}