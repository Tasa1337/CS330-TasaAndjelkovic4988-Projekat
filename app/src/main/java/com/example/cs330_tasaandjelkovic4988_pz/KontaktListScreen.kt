package com.example.cs330_tasaandjelkovic4988_pz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KontaktListScreen(
    viewModel: KontaktiViewModel,
    onAddClick: () -> Unit,
    onEditClick: (Kontakt) -> Unit
){
    val kontakti by viewModel.kontakti.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Kontakti") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        })
    { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
            items(kontakti) { kontakt ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${kontakt.ime} ${kontakt.prezime}", style = MaterialTheme.typography.titleMedium)
                        Text(text = kontakt.brojTelefona)
                        Text(text = kontakt.email)
                        Text(text = kontakt.kategorija)
                        if (kontakt.omiljeni) {
                            Text(text = "★ Omiljeni")
                        }
                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            TextButton(onClick = { onEditClick(kontakt) }) {
                                Text("Izmeni")
                            }
                            TextButton(onClick = { viewModel.deleteKontakt(kontakt) }) {
                                Text("Obrisi", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }
    }
}
