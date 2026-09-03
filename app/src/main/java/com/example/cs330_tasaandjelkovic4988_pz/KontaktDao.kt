package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KontaktDao{
    @Insert
    suspend fun insert(kontakt: Kontakt)

    @Update
    suspend fun update(kontakt: Kontakt)

    @Delete
    suspend fun delete(kontakt: Kontakt)

    @Query("SELECT * FROM kontakti")
    fun getAll(): Flow<List<Kontakt>>

}