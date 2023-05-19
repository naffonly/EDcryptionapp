package org.d3if0012.edcryptionapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface EdcDao {

    @Insert
    fun insert(edc: EdcEntity)

    @Query("SELECT * FROM edc_main ORDER BY id DESC LIMIT 1")
    fun getLastData():LiveData<EdcEntity?>

}