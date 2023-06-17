package org.d3if0012.edcryptionapp.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0012.edcryptionapp.db.EdcDao
import org.d3if0012.edcryptionapp.db.EdcEntity
import org.d3if0012.edcryptionapp.model.Article
import org.d3if0012.edcryptionapp.model.DataEncryption
import org.d3if0012.edcryptionapp.model.onDecode
import org.d3if0012.edcryptionapp.model.onEncode
import org.d3if0012.edcryptionapp.network.ArticleApi
import org.d3if0012.edcryptionapp.network.HomeWorker
import org.d3if0012.edcryptionapp.network.UpdateWorker
import java.util.*
import java.util.concurrent.TimeUnit

class HomeViewModel(private val db: EdcDao): ViewModel(){

    private val textEncry = MutableLiveData<DataEncryption?>()
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


    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<HomeWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            HomeWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }


    fun getDataEncrytion(): LiveData<DataEncryption?> = textEncry
}