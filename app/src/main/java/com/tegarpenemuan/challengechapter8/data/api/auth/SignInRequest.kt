package com.tegarpenemuan.challengechapter8.data.api.auth

import com.google.gson.annotations.SerializedName

/**
 * com.tegarpenemuan.challengchapter6.data.api.auth
 *
 * Created by Tegar Penemuan on 31/05/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

data class SignInRequest(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null
)