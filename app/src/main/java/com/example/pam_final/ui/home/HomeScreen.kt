package com.example.pam_final.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pam_final.R
import com.example.pam_final.model.Kontak
import com.example.pam_final.navigation.DestinasiNavigasi



object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Anggota"
}



@Composable
fun BodyHome(
    itemKontak: List<Kontak>,
    modifier: Modifier = Modifier,
    onSiswaClick: (String) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit,
    onSearchClear: () -> Unit ={},
) {
    var searchQuery by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        SearchBar(
            searchQuery = searchQuery,  // Tambahkan parameter searchQuery
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            onSearchQueryChanged = {
                searchQuery = it
                onSearchQueryChanged(it)
            },
            onSearchClear = {
                searchQuery = ""
                onSearchClear()
            }
        )

        if (itemKontak.isEmpty()) {
            Text(
                text = "Tidak ada data Kontak",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            // Filter itemSiswa berdasarkan searchQuery
            val filteredList = itemKontak.filter { kontak ->
                kontak.nama.contains(searchQuery, ignoreCase = true) ||
                        kontak.alamat.contains(searchQuery, ignoreCase = true) ||
                        kontak.telepon.contains(searchQuery, ignoreCase = true)
            }

            if (filteredList.isEmpty() && searchQuery.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.search),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            } else {
                ListKontak(
                    itemKontak = filteredList,
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                    onItemClick = { onSiswaClick(it.id) }
                )
            }
        }
    }
}

@Composable
fun ListKontak(
    itemKontak: List<Kontak>,
    modifier: Modifier = Modifier,
    onItemClick: (Kontak) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemKontak, key = { it.id }) { kontak ->
            DataKontak(
                kontak = kontak,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(kontak) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataKontak(
    kontak: Kontak,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = kontak.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = kontak.telepon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = kontak.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,  // Tambahkan parameter searchQuery
    modifier: Modifier = Modifier,
    onSearchQueryChanged: (String) -> Unit,
    onSearchClear: () -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChanged(it) },
        placeholder = { Text(text = stringResource(R.string.search)) },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchClear() }) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }
        },
        modifier = modifier
    )
}