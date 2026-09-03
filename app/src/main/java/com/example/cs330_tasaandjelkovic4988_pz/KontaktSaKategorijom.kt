package com.example.cs330_tasaandjelkovic4988_pz

import androidx.room.Embedded
import androidx.room.Relation

data class KontaktSaKategorijom(
    @Embedded
    val kontakt: Kontakt,
    @Relation(
        parentColumn = "kategorijaId",
        entityColumn = "id"
    )
    val kategorija: Kategorija
)