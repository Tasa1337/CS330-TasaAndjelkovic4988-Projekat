package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface KategorijaDao {
    @Insert
    suspend fun insert(kategorija: Kategorija)

    @Query("SELECT * FROM kategorije")
    fun getAll(): Flow<List<Kategorija>>
}