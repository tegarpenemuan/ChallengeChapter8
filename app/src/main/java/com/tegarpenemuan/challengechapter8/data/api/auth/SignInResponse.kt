package com.tegarpenemuan.challengechapter8.data.api.auth

data class SignInResponse(
    val message: String,
    val success: Boolean,
    val token: String,
    val user: User
) {
    data class User(
        val created_at: String,
        val email: String,
        val email_verified_at: Any,
        val id: Int,
        val image: String,
        val job: String,
        val name: String,
        val updated_at: String
    )
}