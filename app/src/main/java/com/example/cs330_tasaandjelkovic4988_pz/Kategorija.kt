package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kategorije")
data class Kategorija(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String
)