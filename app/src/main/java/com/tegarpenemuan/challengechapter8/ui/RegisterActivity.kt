package com.tegarpenemuan.challengechapter8.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tegarpenemuan.challengechapter8.R
import com.tegarpenemuan.challengechapter8.data.api.auth.SignInRequest
import com.tegarpenemuan.challengechapter8.ui.theme.ChallengeChapter8Theme
import com.tegarpenemuan.challengechapter8.ui.viewmodel.LoginViewModel
import com.tegarpenemuan.challengechapter8.ui.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeChapter8Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DisplayRegisterUserInterface(viewModel)
                }
            }
        }
    }
}

@Composable
fun DisplayRegisterUserInterface(viewModel: RegisterViewModel) {
    val mContext = LocalContext.current
    val cek = viewModel.shouldShowSuccess.observeAsState().value
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var nama by remember {
            mutableStateOf("")
        }
        var pekerjaan by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by rememberSaveable {
            mutableStateOf(false)
        }
        Text(
            text = "Register",
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_login),
            contentDescription = "img",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
        Spacer(modifier = Modifier.padding(15.dp))
        TextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = "Nama Lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = pekerjaan,
            onValueChange = { pekerjaan = it },
            label = { Text(text = "Pekerjaan") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Button(
            onClick = {
                if (nama.isNotEmpty() && pekerjaan.isNotEmpty() && email.isNotEmpty() &&
                    password.isNotEmpty()
                ) {
                    viewModel.SignUp(
                        name = nama,
                        email = email,
                        job = pekerjaan,
                        password = password
                    )

                    if (cek == true) {
                        Toast.makeText(mContext, "Register berhasil", Toast.LENGTH_SHORT).show()
                        mContext.startActivity(Intent(mContext, LoginActivity::class.java))
                        activity!!.finish()
                    }
                } else {
                    Toast.makeText(mContext, "Field Harus Diisi", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .height(45.dp),

            ) {
            Text(text = "Register")
        }
        Text(
            text = "Sudah Punya Akun ?",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {
                    mContext.startActivity(
                        Intent(
                            mContext,
                            LoginActivity::class.java
                        )
                    )
                })
    }
}