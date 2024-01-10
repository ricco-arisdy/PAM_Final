package com.example.pam_final.ui

data class UIStateKontak(
    val detailKontak: DetailKontak= DetailKontak(),
)

data class  DetailKontak(
    val id: String = "",
    val nama : String= "",
    val alamat : String= "",
    val telpon : String= "",
)