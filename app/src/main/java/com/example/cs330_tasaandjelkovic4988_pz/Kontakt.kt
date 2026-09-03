package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Kontakti")
data class Kontakt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val prezime: String,
    val brojTelefona: String,
    val email: String,
    val kategorija: String,
    val omiljeni: Boolean = false
)