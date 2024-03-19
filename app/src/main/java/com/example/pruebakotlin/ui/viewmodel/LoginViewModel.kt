package com.example.pruebakotlin.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebakotlin.data.model.LoginModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    /*private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult*/

    val loginResult = MutableLiveData<Task<AuthResult>>()


    fun signInWithEmailAndPassword(loginModel: LoginModel) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            loginModel?.email ?:"",
            loginModel?.password ?:""
        ).addOnCompleteListener { task ->
                loginResult.postValue(task)
            }


}