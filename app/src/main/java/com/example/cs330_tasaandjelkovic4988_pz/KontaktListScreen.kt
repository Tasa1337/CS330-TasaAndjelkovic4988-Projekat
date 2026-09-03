package com.example.cs330_tasaandjelkovic4988_pz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KontaktListScreen(
    viewModel: KontaktiViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Kontakt) -> Unit
) {
    val kontakti by viewModel.kontakti.collectAsState()
    var pretraga by remember { mutableStateOf("") }

    val filtriraniKontakti = kontakti.filter {
        it.kontakt.ime.contains(pretraga, ignoreCase = true) || it.kontakt.prezime.contains(pretraga, ignoreCase = true)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Kontakti") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            OutlinedTextField(
                value = pretraga,
                onValueChange = { pretraga = it },
                label = { Text("Pretrazi po imenu ili prezimenu") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filtriraniKontakti) { stavka ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${stavka.kontakt.ime} ${stavka.kontakt.prezime}", style = MaterialTheme.typography.titleMedium)
                            Text(text = stavka.kontakt.brojTelefona)
                            Text(text = stavka.kontakt.email)
                            Text(text = stavka.kategorija.naziv)
                            if (stavka.kontakt.omiljeni) {
                                Text(text = "★ Omiljeni")
                            }
                            Row(modifier = Modifier.padding(top = 8.dp)) {
                                TextButton(onClick = { onEditClick(stavka.kontakt) }) {
                                    Text("Izmeni")
                                }
                                TextButton(onClick = { viewModel.deleteKontakt(stavka.kontakt) }) {
                                    Text("Obrisi", color = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}