package com.fakhri0079.cinerack.ui.screen

import androidx.lifecycle.ViewModel
import com.fakhri0079.cinerack.model.Film

class MainViewModel : ViewModel() {
    val data = listOf(
        Film(
            1,
            "Avengers: Age of Ultron",
            "Avengers: Age of Ultron adalah film Amerika tahun 2015 film pahlawan super berdasarkan Marvel Comics tim superhero Avengers. ...",
            8.0f,
            true
        ),
        Film(
            2,
            "Inception",
            "Inception adalah film fiksi ilmiah tahun 2010 yang disutradarai oleh Christopher Nolan ...",
            9.0f,
            true
        ),
        Film(
            3,
            "Interstellar",
            "Interstellar adalah film fiksi ilmiah epik tahun 2014 yang disutradarai oleh Christopher Nolan ...",
            8.5f,
            true
        ),
        Film(
            4,
            "Parasite",
            "Parasite adalah film Korea Selatan tahun 2019 yang disutradarai oleh Bong Joon-ho ...",
            9.0f,
            false
        ),
        Film(
            5,
            "Spider-Man: No Way Home",
            "Spider-Man: No Way Home adalah film superhero tahun 2021 produksi Marvel Studios dan Sony Pictures ...",
            8.7f,
            true
        ),
        Film(
            6,
            "The Batman",
            "The Batman adalah film superhero tahun 2022 yang disutradarai oleh Matt Reeves ...",
            8.2f,
            false
        )
    )
    fun getFilm(id: Long): Film?{
        return data.find { it.id == id }
    }

}