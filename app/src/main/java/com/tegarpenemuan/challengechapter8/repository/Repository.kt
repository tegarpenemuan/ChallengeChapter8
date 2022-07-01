package com.tegarpenemuan.challengechapter8.repository

import com.tegarpenemuan.challengechapter8.data.api.auth.*
import com.tegarpenemuan.challengechapter8.data.api.tmdb.TMDBAPI
import com.tegarpenemuan.challengechapter8.data.api.tmdb.detailmovie.DetailMovieResponse
import com.tegarpenemuan.challengechapter8.data.api.tmdb.moviepopuler.MoviePopulerResponse
import com.tegarpenemuan.challengechapter8.datastore.DatastoreManager
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.Response

/**
 * com.tegarpenemuan.challengchapter6.repository
 *
 * Created by Tegar Penemuan on 31/05/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

class Repository(
    private val dataStoreManager: DatastoreManager,
    private val movieApi: TMDBAPI
) {

    suspend fun signUpApi(request: SignUpRequest): Response<SignUpResponse> {
        return movieApi.register(
            name = request.name,
            email = request.email,
            job = request.job,
            password = request.password,
            data = request.data
        )
    }

    suspend fun signIn(request: SignInRequest): Response<SignInResponse> {
        return movieApi.login(request)
    }

    suspend fun getMoviePopuler(): Response<MoviePopulerResponse> {
        return movieApi.getMoviePopuler()
    }

    fun getMovie(): Response<MoviePopulerResponse> {
        return getMovie()
    }

    suspend fun getMovieDetails(id: Int): Response<DetailMovieResponse> {
        return movieApi.getMovieDetails(id)
    }

    suspend fun getEmail(): String? {
        return dataStoreManager.getEmail().firstOrNull()
    }

    suspend fun setPrefEmail(value: String) {
        dataStoreManager.setEmail(value)
    }

    suspend fun getId(): String? {
        return dataStoreManager.getId().firstOrNull()
    }

    suspend fun setPrefId(value: String) {
        dataStoreManager.setId(value)
    }

    suspend fun getIsLogin(): Boolean {
        return dataStoreManager.getIsLogin().firstOrNull() == false
    }

    suspend fun setIsLogin(value: Boolean) {
        dataStoreManager.setIsLogin(value)
    }

    suspend fun clearId() {
        dataStoreManager.setId("")
    }
}