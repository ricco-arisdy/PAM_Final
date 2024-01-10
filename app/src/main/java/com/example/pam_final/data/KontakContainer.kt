package com.example.pam_final.data

import com.google.firebase.firestore.FirebaseFirestore


interface AppContainer{
    val kontakRepository:KontakRepository
}
class KontakContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val kontakRepository: KontakRepository by lazy {
        KontakRepositoryImpl(firestore)
    }
}