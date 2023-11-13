package com.nimur.mycoachbasketball.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.viewmodel.RegisterViewModel

class RegisterPage : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var signUp: Button
    private lateinit var signIn: TextView
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        signUp = findViewById(R.id.sign_up)
        signIn = findViewById(R.id.sign_in)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        signIn.setOnClickListener {
            val intent = Intent(this@RegisterPage, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Observa cambios en el resultado del registro.
            registerViewModel.registrationResult.observe(this, Observer { registrationSuccessful ->
                if (registrationSuccessful) {
                    Toast.makeText(this@RegisterPage, "Registration Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterPage, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterPage, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            })

            // Llama al método registerUser en el ViewModel.
            registerViewModel.registerUser(email, password)
        }
    }
}