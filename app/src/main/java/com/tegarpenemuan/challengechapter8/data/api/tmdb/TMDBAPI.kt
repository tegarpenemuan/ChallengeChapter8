package com.tegarpenemuan.challengechapter8.data.api.tmdb

import com.tegarpenemuan.challengechapter8.data.api.tmdb.moviepopuler.MoviePopulerResponse
import com.tegarpenemuan.challengechapter8.data.api.auth.*
import com.tegarpenemuan.challengechapter8.data.api.tmdb.detailmovie.DetailMovieResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TMDBAPI {
    @GET("movie/popular")
    suspend fun getMoviePopuler(
        @Query("api_key") apiKey: String = "0fbaf8c27d542bc99bfc67fb877e3906"
    ): Response<MoviePopulerResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = "0fbaf8c27d542bc99bfc67fb877e3906"
    ): Response<DetailMovieResponse>

    @POST("https://tegarpenemuan.xyz/api/login")
    suspend fun login(@Body request: SignInRequest): Response<SignInResponse>

    @Multipart
    @POST("https://tegarpenemuan.xyz/api/register")
    suspend fun register(
        @Part("name") name: RequestBody? = null,
        @Part("email") email: RequestBody? = null,
        @Part("job") job: RequestBody? = null,
        @Part("password") password: RequestBody? = null,
        @Part data: MultipartBody.Part? = null
    ): Response<SignUpResponse>
}