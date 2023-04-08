package com.example.caffaine2222

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {
    lateinit var emailField: EditText
    lateinit var forgotPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        emailField = findViewById(R.id.forgotPassword_email)
        forgotPassword = findViewById(R.id.forgotPassword_button)

        forgotPassword.setOnClickListener {
            Firebase.auth.sendPasswordResetEmail(emailField.text.toString()) .addOnCompleteListener {
                    task -> if (task.isSuccessful) {
                        finish()
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
            }
            }
    }
}