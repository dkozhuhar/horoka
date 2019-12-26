package com.example.android.horoka.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HorokaDao {
    @Query("SELECT * FROM photos ORDER BY added_datetime DESC")
    fun getAllPhotos(): LiveData<List<HorokaPhoto>>

    @Insert
    suspend fun insertPhoto(vararg photos: HorokaPhoto)

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

    @Query("SELECT * FROM photos WHERE ID = :id")
    suspend fun getPhotoById(id: String) : HorokaPhoto?
}