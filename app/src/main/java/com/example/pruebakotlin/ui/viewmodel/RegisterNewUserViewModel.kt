package com.example.pruebakotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebakotlin.data.model.LoginModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterNewUserViewModel : ViewModel() {

    var registerUser = MutableLiveData<Task<AuthResult>>()

    fun registerNewUser(loginModel: LoginModel){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            loginModel.email,
            loginModel.password
        )
            .addOnCompleteListener{
                registerUser.postValue(it)
            }
    }
}