package com.example.pam_final.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.pam_final.R
import com.example.pam_final.navigation.DestinasiNavigasi
import com.example.pam_final.ui.DetailKontak
import com.example.pam_final.ui.UIStateKontak
import kotlinx.coroutines.launch

object DestinasiKontak : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Kontak"
}



@Composable
fun AddKontakBody(
    uiStateKontak: UIStateKontak,
    onKontakValueChange: (DetailKontak) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInput(
            detailKontak = uiStateKontak.detailKontak,
            onValueChange = onKontakValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.btn_submit))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    detailKontak: DetailKontak,
    modifier: Modifier = Modifier,
    onValueChange: (DetailKontak) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailKontak.nama,
            onValueChange = {onValueChange(detailKontak.copy(nama=it)) },
            label = { Text(stringResource(R.string.nama)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKontak.alamat,
            onValueChange = {onValueChange(detailKontak.copy(alamat=it)) },
            label = { Text(stringResource(R.string.alamat)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = detailKontak.telpon,
            onValueChange = {onValueChange(detailKontak.copy(telpon=it)) },
            label = { Text(stringResource(R.string.telpon)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

    }
}