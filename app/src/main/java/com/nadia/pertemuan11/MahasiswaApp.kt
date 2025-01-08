package com.nadia.pertemuan11

import android.app.Application
import com.nadia.pertemuan11.di.AppContainer
import com.nadia.pertemuan11.di.MahasiswaContainer

class MahasiswaApplication: Application() {
    lateinit var container: MahasiswaContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}