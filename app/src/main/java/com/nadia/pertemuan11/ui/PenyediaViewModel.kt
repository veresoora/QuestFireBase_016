package com.nadia.pertemuan11.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nadia.pertemuan11.MahasiswaApplication
import com.nadia.pertemuan11.ui.home.viewmodel.HomeViewModel
import com.nadia.pertemuan11.ui.home.viewmodel.InsertViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(mahasiswaApp().container.repositoryMhs)
        }
        initializer {
            InsertViewModel (
                mahasiswaApp().container.repositoryMhs
            )
        }
    }
}
fun CreationExtras.mahasiswaApp(): MahasiswaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplication)