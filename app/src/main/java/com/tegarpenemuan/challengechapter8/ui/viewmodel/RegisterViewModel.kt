package com.tegarpenemuan.challengechapter8.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tegarpenemuan.challengechapter8.data.api.auth.SignInRequest
import com.tegarpenemuan.challengechapter8.data.api.auth.SignUpRequest
import com.tegarpenemuan.challengechapter8.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * com.tegarpenemuan.challengechapter8.ui.viewmodel
 *
 * Created by Tegar Penemuan on 01/07/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val shouldShowSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun SignUp(name:String,email:String,job:String,password:String) {
        val name = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), name)
        val email = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), email)
        val job = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), job)
        val password = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), password)

        val request = SignUpRequest(
            name = name,
            email = email,
            job = job,
            password = password,
            data = null,
        )
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.signUpApi(request = request)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowSuccess.postValue(true)
                } else {
                    shouldShowSuccess.postValue(false)
                }
            }
        }
    }
}