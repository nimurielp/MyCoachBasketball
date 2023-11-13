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
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var signIn: Button
    private lateinit var signup: TextView
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        signIn = findViewById(R.id.btn_signin)
        signup = findViewById(R.id.sign_up)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        signup.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterPage::class.java)
            startActivity(intent)
            finish()
        }

        signIn.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            // Observa cambios en el resultado del login.
            loginViewModel.loginResult.observe(this, Observer { loginSuccessful ->
                if (loginSuccessful) {
                    Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity, HomePage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            })

            // Llama al método loginUser en el ViewModel.
            loginViewModel.loginUser(email, password)
        }
    }
}