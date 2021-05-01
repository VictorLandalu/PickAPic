package com.landalu.pickapic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setup()
    }

    private fun setup() {
        title= "Autenticacion"

        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passEditText = findViewById<EditText>(R.id.passEditText)
        val logInButton = findViewById<Button>(R.id.logInButton)
        signUpButton.setOnClickListener(){
            if (emailEditText.text.isNotEmpty() && passEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(), passEditText.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful){
                        showHome()
                    }
                    else{
                        showAlert()
                    }
                }
            }
        }
        logInButton.setOnClickListener(){
            if (emailEditText.text.isNotEmpty() && passEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passEditText.text.toString()).addOnCompleteListener(){
                    if(it.isSuccessful){
                        showHome()
                    }
                    else{
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showHome() {
        val mainIntent= Intent(this, MainActivity::class.java).apply {  }
        startActivity(mainIntent)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()

    }
}