package com.example.pam_final.data

import android.content.ContentValues
import android.util.Log
import com.example.pam_final.model.Kontak
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface KontakRepository{
    fun getAllSiswaStream(): Flow<List<Kontak>>
    fun getSiswaStream(id: Int): Flow<Kontak?>
    suspend fun save(kontak: Kontak):String
    suspend fun update(kontak: Kontak)
    suspend fun delete(kontakId: String)
    fun getKontakById(kontakId: String): Flow<Kontak>
}
