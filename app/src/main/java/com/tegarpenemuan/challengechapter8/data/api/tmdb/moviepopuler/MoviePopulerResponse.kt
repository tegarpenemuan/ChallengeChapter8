package com.tegarpenemuan.challengechapter8.data.api.tmdb.moviepopuler

data class MoviePopulerResponse(
    val page: Int,
    val results: List<ResultMoviePopuler>,
    val total_pages: Int,
    val total_results: Int
) {
    data class ResultMoviePopuler(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val vote_average: Double,
        val vote_count: Int
    )
}