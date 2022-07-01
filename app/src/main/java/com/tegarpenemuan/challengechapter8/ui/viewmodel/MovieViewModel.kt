package com.tegarpenemuan.challengechapter8.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.tegarpenemuan.challengechapter8.data.api.tmdb.moviepopuler.MoviePopulerResponse
import com.tegarpenemuan.challengechapter8.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * com.tegarpenemuan.challengechapter8.ui.viewmodel
 *
 * Created by Tegar Penemuan on 01/07/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val movieState = MutableStateFlow(emptyList<MoviePopulerResponse.ResultMoviePopuler>())
    val dataMovieState: StateFlow<List<MoviePopulerResponse.ResultMoviePopuler>> get() = movieState

    fun getMoviePopuler() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getMoviePopuler()
            withContext(Dispatchers.IO) {
                if (response.isSuccessful) {
                    movieState.value = response.body()!!.results
                }
            }
        }
    }
}