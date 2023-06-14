package org.d3if0012.edcryptionapp.ui.home

import android.util.Log
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
import org.d3if0012.edcryptionapp.model.onDecode
import org.d3if0012.edcryptionapp.model.onEncode
import org.d3if0012.edcryptionapp.network.ArticleApi
import java.util.*

class HomeViewModel(private val db: EdcDao): ViewModel(){

    private val textEncry = MutableLiveData<DataEncryption?>()
    init {
        retrieveData()
    }
     fun onEncode(encodeData:String,decodeData : String) {

         val dataEncrip = EdcEntity(
             encode = encodeData,
             decode = decodeData,
             isEncode = true
         )

        textEncry.value = dataEncrip.onEncode()

         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 db.insert(dataEncrip)
             }
         }
    }

     fun onDecode(encodeData:String,decodeData : String){

         val dataEncrip = EdcEntity(
             encode = encodeData,
             decode = decodeData,
             isEncode = false
         )

        textEncry.value = dataEncrip.onDecode()
         viewModelScope.launch {
             withContext(Dispatchers.IO){
                 db.insert(dataEncrip)
             }
         }
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = ArticleApi.service.getArticle()
                Log.d("MainViewModel", "Success: $result")
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }


    fun getDataEncrytion(): LiveData<DataEncryption?> = textEncry
}