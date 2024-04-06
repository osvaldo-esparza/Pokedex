package com.example.pruebakotlin.ui.view
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pruebakotlin.Home
import com.example.pruebakotlin.RegisterActivity
import com.example.pruebakotlin.databinding.ActivityMainBinding
import com.example.pruebakotlin.data.model.LoginModel
import com.example.pruebakotlin.ui.viewmodel.LoginViewModel
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels()
    private val callbackManager = CallbackManager.Factory.create()
    private val EMAIL = "email"
    private lateinit var auth: FirebaseAuth
    private var FB = "FB-------------------------------------------------------------------------------------------------------"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(this.application)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Observa el resultado del inicio de sesión y actúa en consecuencia
        viewModel.loginResult.observe(this, Observer {
            if (it.isSuccessful) {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "-------------------------------------------------------------------------------------------------------Login failed ${it.exception?.message ?: ""}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.login.setOnClickListener {
            println("---------------------------------------------------------------------------------------------HOLA")
            if(!binding.email.text.isNullOrBlank() && !binding.pass.text.isNullOrBlank()) {

                viewModel.signInWithEmailAndPassword(
                    LoginModel(
                        email = binding.email?.text.toString(),
                        password = binding.pass?.text.toString()
                    )
                )
            }
            else{
                Snackbar.make(binding.login,"Usuario y/o Contraseña no pueden ir vacios",Snackbar.LENGTH_SHORT)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.RED)
                    .show()
            }

        }

        binding.register.setOnClickListener{
            val intent: Intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }




        binding.loginButton.setPermissions(EMAIL, "public_profile")

        LoginManager.getInstance().registerCallback(callbackManager,object: FacebookCallback<LoginResult>{
            override fun onCancel() {
                TODO("Not yet implemented")
            }

            override fun onError(error: FacebookException) {
                errorShow(error?.message ?: "")
            }

            override fun onSuccess(result: LoginResult) {
                println( "-------------------------------------------------------------------------------------------------------facebook:onSuccess:$result")
                handleFacebookAccessToken(result.accessToken)
                showHome()
            }

        })

       /* binding.fblogin.setOnClickListener{
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult>
                {
                    override fun onSuccess(result: LoginResult) {
                        result?.let {
                            val token = it.accessToken
                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener{
                                    if(it.isSuccessful){
                                        showHome()
                                    }
                                    else{
                                        Snackbar.make(binding.login,"Usuario y/o Contraseña no pueden ir vacios",Snackbar.LENGTH_SHORT)
                                            .setTextColor(Color.WHITE)
                                            .setBackgroundTint(Color.RED)
                                            .show()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {

                    }

                    override fun onError(error: FacebookException) {
                       // Toast.makeText(this,error.message.toString(),Toast.LENGTH_SHORT)
                    }
                })
        }*/
    }

    private fun showHome() {
        val intent: Intent= Intent(this,Home::class.java)
        startActivity(intent)
    }

    private fun errorShow(msg:String){
        Snackbar.make(binding.textView2,msg,Snackbar.LENGTH_SHORT)
            .setBackgroundTint(Color.RED)
            .setTextColor(Color.WHITE)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


    private fun handleFacebookAccessToken(token: AccessToken) {
        println("-------------------------------------------------------------------------------------------------------handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    println( "-------------------------------------------------------------------------------------------------------signInWithCredential:success")
                    val user = auth.currentUser
                    //updateUI(user)
                    showHome()
                } else {
                    // If sign in fails, display a message to the user.
                    println("-------------------------------------------------------------------------------------------------------signInWithCredential:failure ${task.exception}")
                    Toast.makeText(
                        baseContext,
                        "-------------------------------------------------------------------------------------------------------Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                   // updateUI(null)
                    errorShow(task.result.toString())
                }
            }
    }

}