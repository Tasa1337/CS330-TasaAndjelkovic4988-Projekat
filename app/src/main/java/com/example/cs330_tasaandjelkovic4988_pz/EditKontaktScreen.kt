package com.example.cs330_tasaandjelkovic4988_pz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditKontaktScreen(viewModel: KontaktiViewModel, kontakt: Kontakt, onDone: () -> Unit) {
    var ime by remember { mutableStateOf(kontakt.ime) }
    var prezime by remember { mutableStateOf(kontakt.prezime) }
    var brojTelefona by remember { mutableStateOf(kontakt.brojTelefona) }
    var email by remember { mutableStateOf(kontakt.email) }
    var kategorija by remember { mutableStateOf(kontakt.kategorija) }
    var omiljeni by remember { mutableStateOf(kontakt.omiljeni) }

    Scaffold(topBar = { TopAppBar(title = { Text("Izmeni kontakt") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            OutlinedTextField(value = ime, onValueChange = { ime = it }, label = { Text("Ime") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = prezime, onValueChange = { prezime = it }, label = { Text("Prezime") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = brojTelefona, onValueChange = { brojTelefona = it }, label = { Text("Broj telefona") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = kategorija, onValueChange = { kategorija = it }, label = { Text("Kategorija") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Omiljeni kontakt")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = omiljeni, onCheckedChange = { omiljeni = it })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val izmenjeniKontakt = kontakt.copy(
                    ime = ime,
                    prezime = prezime,
                    brojTelefona = brojTelefona,
                    email = email,
                    kategorija = kategorija,
                    omiljeni = omiljeni
                )
                viewModel.updateKontakt(izmenjeniKontakt)
                onDone()
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Sacuvaj izmene")
            }
        }
    }
}