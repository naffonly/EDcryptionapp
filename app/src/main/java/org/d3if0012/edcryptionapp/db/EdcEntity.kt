package org.d3if0012.edcryptionapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "edc_main")
data class EdcEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal : Long = System.currentTimeMillis(),
    var encode : String,
    var decode : String
)
