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

class KontakRepositoryImpl(private val firestore: FirebaseFirestore):KontakRepository{
    override fun getAllSiswaStream(): Flow<List<Kontak>> = flow{
        val snapshot = firestore.collection("Kontak")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val kontak = snapshot.toObjects(Kontak::class.java)
        emit(kontak)
    }.flowOn(Dispatchers.IO)

    override fun getSiswaStream(id: Int): Flow<Kontak?> {
        TODO("Not yet implemented")
    }



    override suspend fun save(kontak: Kontak): String {
        return try {
            val documentReference = firestore.collection("Kontak").add(kontak).await()

            firestore.collection("Kontak").document(documentReference.id)
                .set(kontak.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception){
            Log.w(ContentValues.TAG,"Error adding document",e)
            "Gagal $e"
        }
    }

    override suspend fun update(kontak: Kontak) {
        firestore.collection("Kontak").document(kontak.id).set(kontak).await()
    }

    override suspend fun delete(kontakId: String) {
        firestore.collection("Kontak").document(kontakId).delete().await()
    }

    override fun getKontakById(kontakId: String): Flow<Kontak> {
        return flow {
            val snapshot = firestore.collection("Kontak").document(kontakId).get().await()
            val kontak = snapshot.toObject(Kontak::class.java)
            emit(kontak!!)
        }.flowOn(Dispatchers.IO)
    }

}