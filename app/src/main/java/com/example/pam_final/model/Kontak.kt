package com.example.pam_final.model

data class Kontak(
    val id: String,
    val nama: String,
    val alamat: String,
    val telepon: String,
){
    constructor(): this("","","","")
}
