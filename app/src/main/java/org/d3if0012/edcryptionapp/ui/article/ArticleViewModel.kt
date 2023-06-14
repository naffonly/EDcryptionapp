package org.d3if0012.edcryptionapp.ui.article

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0012.edcryptionapp.model.Article
import org.d3if0012.edcryptionapp.network.ArticleApi
import kotlin.math.log

class ArticleViewModel : ViewModel() {
    private val data = MutableLiveData<List<Article>>()
    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(ArticleApi.service.getArticle())
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
    fun getData(): LiveData<List<Article>> = data
}