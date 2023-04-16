package com.example.mapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("logsTest", "log from Create Main Activity")
        // Choose authentication providers



        val intent = Intent(this, MoviesActivity::class.java)
        intent.putExtra("simpleCode", "answer")
        startActivity(intent)







//        val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
//        // Create and launch sign-in intent
//
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setIsSmartLockEnabled(false)
//            .setAvailableProviders(providers)
//            .build()
//        signInLauncher.launch(signInIntent) // запустили экран firebase

    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse // результат с экрана Firebase auth
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            Log.d("logsTest", "onSignInResult success ${response?.email}")
            val authUser = FirebaseAuth.getInstance().currentUser // создаем текущий объект текущего пользователя
            database = Firebase.database.reference
           authUser?.let { // если пользователь "существует", сохраняем его в БД
                val userFirebase = User(authUser.email.toString(), it.uid)
                database.child("users").child(authUser?.uid.toString()).setValue(userFirebase)

               val intent = Intent(this, MoviesActivity::class.java)
               intent.putExtra("simpleCode", "answer")
               startActivity(intent)

           }
            Log.d("logsTest", "onSignInResult success ${response?.email}")
        } else {
        }
    }

}