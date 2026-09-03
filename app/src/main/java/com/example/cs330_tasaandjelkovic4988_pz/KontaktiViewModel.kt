package com.example.cs330_tasaandjelkovic4988_pz

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KontaktiViewModel(application: Application) : AndroidViewModel(application) {
    private val kontaktDao = AppDatabase.getDatabase(application).kontaktDao()
    private val kategorijaDao = AppDatabase.getDatabase(application).kategorijaDao()

    val kontakti: StateFlow<List<KontaktSaKategorijom>> = kontaktDao.getAllSaKategorijom()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val kategorije: StateFlow<List<Kategorija>> = kategorijaDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _kontaktZaIzmenu = mutableStateOf<Kontakt?>(null)
    val kontaktZaIzmenu: State<Kontakt?> = _kontaktZaIzmenu

    fun postaviKontaktZaIzmenu(kontakt: Kontakt) {
        _kontaktZaIzmenu.value = kontakt
    }

    fun addKategorija(naziv: String) {
        viewModelScope.launch {
            kategorijaDao.insert(Kategorija(naziv = naziv))
        }
    }

    fun addKontakt(ime: String, prezime: String, brojTelefona: String, email: String, kategorijaId: Int, omiljeni: Boolean) {
        viewModelScope.launch {
            kontaktDao.insert(Kontakt(ime = ime, prezime = prezime, brojTelefona = brojTelefona, email = email, kategorijaId = kategorijaId, omiljeni = omiljeni))
        }
    }

    fun updateKontakt(kontakt: Kontakt) {
        viewModelScope.launch {
            kontaktDao.update(kontakt)
        }
    }

    fun deleteKontakt(kontakt: Kontakt) {
        viewModelScope.launch {
            kontaktDao.delete(kontakt)
        }
    }
}