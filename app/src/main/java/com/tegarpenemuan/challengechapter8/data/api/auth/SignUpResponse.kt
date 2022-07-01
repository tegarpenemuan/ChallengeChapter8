package com.tegarpenemuan.challengechapter8.data.api.auth

data class SignUpResponse(
    val code: Int,
    val `data`: Data,
    val message: String
) {
    data class Data(
        val created_at: String,
        val email: String,
        val id: Int,
        val image: String,
        val name: String,
        val job: String,
        val updated_at: String
    )
}