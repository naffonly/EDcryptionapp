package org.d3if0012.edcryptionapp.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if0012.edcryptionapp.db.EdcDao

class HistoriViewModel(db: EdcDao) : ViewModel(){
    val data = db.getLastData()
}