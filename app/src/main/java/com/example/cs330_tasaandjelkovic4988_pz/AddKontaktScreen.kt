package com.example.cs330_tasaandjelkovic4988_pz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddKontaktScreen(viewModel: KontaktiViewModel, onDone: () -> Unit) {
    var ime by remember { mutableStateOf("") }
    var prezime by remember { mutableStateOf("") }
    var brojTelefona by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var omiljeni by remember { mutableStateOf(false) }

    var novaKategorija by remember { mutableStateOf("") }
    val kategorije by viewModel.kategorije.collectAsState()
    var izabranaKategorija by remember { mutableStateOf<Kategorija?>(null) }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(topBar = { TopAppBar(title = { Text("Dodaj kontakt") }) }) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            OutlinedTextField(value = ime, onValueChange = { ime = it }, label = { Text("Ime") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = prezime, onValueChange = { prezime = it }, label = { Text("Prezime") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = brojTelefona, onValueChange = { brojTelefona = it }, label = { Text("Broj telefona") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Text("Kategorija", style = MaterialTheme.typography.titleSmall)
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                OutlinedTextField(
                    value = izabranaKategorija?.naziv ?: "Izaberi kategoriju",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    kategorije.forEach { kat ->
                        DropdownMenuItem(
                            text = { Text(kat.naziv) },
                            onClick = {
                                izabranaKategorija = kat
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = novaKategorija,
                    onValueChange = { novaKategorija = it },
                    label = { Text("Nova kategorija") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (novaKategorija.isNotBlank()) {
                        viewModel.addKategorija(novaKategorija)
                        novaKategorija = ""
                    }
                }) {
                    Text("Dodaj")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text("Omiljeni kontakt")
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = omiljeni, onCheckedChange = { omiljeni = it })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    izabranaKategorija?.let { kat ->
                        viewModel.addKontakt(ime, prezime, brojTelefona, email, kat.id, omiljeni)
                        onDone()
                    }
                },
                enabled = izabranaKategorija != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sacuvaj")
            }
        }
    }
}