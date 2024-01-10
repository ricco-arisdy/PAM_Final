package com.example.pam_final.ui

import com.example.pam_final.model.Kontak

data class UIStateKontak(
    val detailKontak: DetailKontak= DetailKontak(),
)

data class  DetailKontak(
    val id: String = "",
    val nama : String= "",
    val alamat : String= "",
    val telpon : String= "",
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya*/
fun DetailKontak.toKontak(): Kontak = Kontak(
    id = id,
    nama = nama,
    alamat = alamat,
    telepon = telpon
)

data class DetailUIState(
    val addEvent: DetailKontak = DetailKontak(),
)

fun Kontak.toDetailKontak(): DetailKontak =
    DetailKontak(
        id = id,
        nama = nama,
        alamat = alamat,
        telpon = telepon
    )

fun Kontak.toUiStateKontak(): UIStateKontak = UIStateKontak(
    detailKontak = this.toDetailKontak()
)

data class HomeUIState(
    val listKontak: List<Kontak> = listOf(),
    val dataLength: Int = 0
)