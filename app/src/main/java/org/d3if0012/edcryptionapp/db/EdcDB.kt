package org.d3if0012.edcryptionapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [EdcEntity::class], version = 1, exportSchema = false)
abstract class EdcDB :RoomDatabase(){
        abstract val dao : EdcDao
        companion object{
            @Volatile
            private var INSTANCE: EdcDB? = null
            fun getInstance(context: Context): EdcDB {
                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            EdcDB::class.java,
                            "edc.db"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
}