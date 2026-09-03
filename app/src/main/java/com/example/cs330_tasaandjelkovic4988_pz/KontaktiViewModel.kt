package com.example.cs330_tasaandjelkovic4988_pz

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class KontaktiViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).kontaktDao()

    val kontakti: StateFlow<List<Kontakt>> = dao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    private val _kontaktZaIzmenu = mutableStateOf<Kontakt?>(null)
    val kontaktZaIzmenu: State<Kontakt?> = _kontaktZaIzmenu

    fun postaviKontaktZaIzmenu(kontakt: Kontakt){
        _kontaktZaIzmenu.value = kontakt
    }

    fun addKontakt(ime: String, prezime: String, brojTelefona: String, email: String, kategorija: String, omiljeni: Boolean){
        viewModelScope.launch {
            dao.insert(Kontakt(ime = ime, prezime = prezime, brojTelefona = brojTelefona, email = email, kategorija = kategorija, omiljeni = omiljeni))
        }
    }

    fun updateKontakt(kontakt: Kontakt){
        viewModelScope.launch {
            dao.update(kontakt)
        }
    }

    fun deleteKontakt(kontakt: Kontakt){
        viewModelScope.launch {
            dao.delete(kontakt)
        }
    }
}