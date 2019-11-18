package com.example.android.horoka.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HorokaDao {
    @Query("SELECT * FROM photos")
    fun getAllPhotos(): List<HorokaPhoto>

    @Insert
    fun insertPhoto(vararg photos: HorokaPhoto)

    @Query("DELETE FROM photos")
    fun deleteAll()
}