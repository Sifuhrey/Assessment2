package com.fakhri0079.cinerack.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhri0079.cinerack.database.FilmDao
import com.fakhri0079.cinerack.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dao: FilmDao) : ViewModel() {
    fun insert(title: String, desc: String, rating: Float, isWatched: Boolean){
        val film = Film(
            title = title,
            desc = desc,
            rating = rating,
            isWatched = isWatched
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(film)
        }
    }

    suspend fun getFilm(id: Long): Film? {
        return dao.getFilmById(id)
    }

    fun update(id: Long, title: String, desc: String, rating: Float, isWatched: Boolean){
        val film = Film(
            id = id,
            title = title,
            desc = desc,
            rating = rating,
            isWatched = isWatched
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(film)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }

}