package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "kontakti",
    foreignKeys = [
        ForeignKey(
            entity = Kategorija::class,
            parentColumns = ["id"],
            childColumns = ["kategorijaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("kategorijaId")]
)
data class Kontakt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ime: String,
    val prezime: String,
    val brojTelefona: String,
    val email: String,
    val kategorijaId: Int,
    val omiljeni: Boolean = false
)