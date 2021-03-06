package com.example.android.horoka.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HorokaPhoto::class], version = 3, exportSchema = false)
abstract class HorokaDb : RoomDatabase(){
    abstract val horokaDao: HorokaDao

    companion object {
        @Volatile
        private var INSTANCE : HorokaDb? = null

        fun getInstance(context: Context): HorokaDb {

            synchronized(this) {

                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,HorokaDb::class.java,"photo_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}