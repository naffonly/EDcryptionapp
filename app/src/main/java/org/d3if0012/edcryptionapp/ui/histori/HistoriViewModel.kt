package org.d3if0012.edcryptionapp.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0012.edcryptionapp.db.EdcDao
import org.d3if0012.edcryptionapp.db.EdcEntity

class HistoriViewModel(private  val db: EdcDao) : ViewModel(){
    val data = db.getLastData()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
    fun hapusSingleData(data : EdcEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.deleteData(data)
        }
    }

}