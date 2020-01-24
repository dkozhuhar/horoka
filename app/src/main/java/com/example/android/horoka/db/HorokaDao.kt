package com.example.android.horoka.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HorokaDao {
    @Query("SELECT * FROM photos ORDER BY added_datetime DESC")
    fun getAllPhotos(): LiveData<List<HorokaPhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(vararg photos: HorokaPhoto)

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

    @Query("SELECT * FROM photos WHERE ID = :id")
    suspend fun getPhotoById(id: String) : HorokaPhoto?

    @Query("SELECT count(*) FROM photos")
    fun countPhotos() : Int

    @Query("SELECT id, raw_url FROM photos")
    suspend fun getAllPhotoIdsAndUrls(): List<IdAndUrl>
}