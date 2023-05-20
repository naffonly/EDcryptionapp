package org.d3if0012.edcryptionapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if0012.edcryptionapp.db.EdcDao

class HomeViewModelFactory (private val db: EdcDao): ViewModelProvider.Factory{


    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(db) as  T
        }
        throw IllegalArgumentException("Unkown viewMode Classs")
    }
}