package com.tegarpenemuan.challengechapter8.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tegarpenemuan.challengechapter8.data.api.auth.SignInRequest
import com.tegarpenemuan.challengechapter8.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val shouldShowSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun signInWithAPI(signInRequest: SignInRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.signIn(signInRequest)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    signInResponse?.let {
                        repository.setIsLogin(true)
                        repository.setPrefEmail(response.body()!!.user.email)
                        repository.setPrefNama(response.body()!!.user.name)
                        repository.setPrefId(response.body()!!.user.id.toString())
                    }
                    shouldShowSuccess.postValue(true)
                } else {
                    shouldShowSuccess.postValue(false)
                }
            }
        }
    }
}