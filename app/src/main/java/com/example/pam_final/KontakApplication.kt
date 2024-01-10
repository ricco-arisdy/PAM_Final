package com.example.pam_final

import android.app.Application
import com.example.pam_final.data.AppContainer
import com.example.pam_final.data.KontakContainer

class KontakApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = KontakContainer()
    }
}