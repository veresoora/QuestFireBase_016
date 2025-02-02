package com.nadia.pertemuan11.di

import com.google.firebase.firestore.FirebaseFirestore
import com.nadia.pertemuan11.repository.NetworkRepositoryMhs
import com.nadia.pertemuan11.repository.RepositoryMhs

interface AppContainer{
    val repositoryMhs : RepositoryMhs
}

class MahasiswaContainer : AppContainer{
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    override val repositoryMhs: RepositoryMhs by lazy {
        NetworkRepositoryMhs(firestore)
    }
}