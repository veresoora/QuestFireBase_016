package com.nadia.pertemuan11.ui.home.viewmodel

import android.net.http.HttpException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadia.pertemuan11.model.Mahasiswa
import com.nadia.pertemuan11.repository.RepositoryMhs
import com.nadia.pertemuan11.ui.navigation.DetailDestinasi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

sealed class DetailUiState {
    data class Success(val mahasiswa: Mahasiswa) : DetailUiState()
    object Error : DetailUiState()
    object Loading : DetailUiState()
}

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _nim: String = checkNotNull(savedStateHandle[DetailDestinasi.NIM])

    val uiState: StateFlow<DetailUIState> =
        repositoryMhs.getMhs(_nim)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailMahasiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )
}

data class DetailUIState (
    val addEvent: MahasiswaEvent = MahasiswaEvent()
)

fun Mahasiswa.toDetailMahasiswa(): MahasiswaEvent =
    MahasiswaEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan,
        judulSkripsi = judulSkripsi,
        dosenPembimbing1 = dosenPembimbing1,
        dosenPembimbing2 = dosenPembimbing2
    )