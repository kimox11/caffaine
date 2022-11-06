package com.example.caffaine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class login : AppCompatActivity() {
    lateinit var go_to_signup : TextView
    lateinit var email_edit_text : EditText
    lateinit var password_edit_text : EditText
    lateinit var go_to_home : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        go_to_signup = findViewById(R.id.go_to_sign_up_text)
        go_to_home = findViewById(R.id.login_button)
        email_edit_text = findViewById(R.id.email_log)
        password_edit_text = findViewById(R.id.password_log)


        go_to_signup.setOnClickListener {
            var intent = Intent(this,Register::class.java)
            getResult.launch(intent)
        }

        go_to_home.setOnClickListener {
            var intent = Intent(this,HomeScreen::class.java)
            if(validate()) {
                getResult.launch(intent)

            }
        }
    }

    private fun validate(): Boolean {
        var email = email_edit_text.getText().toString()
        var password = password_edit_text.getText().toString()
        var result = true
        var error = ""


        if(password.length < 8){
            error = "This password is too short"
            password_edit_text.setError(error)
            password_edit_text.requestFocus();
            result = false
        }
        if (!email.contains("@") || !email.contains(".com")){
            error = "Please put valid email"
            email_edit_text.setError(error)
            email_edit_text.requestFocus();
            result = false
        }



        return result
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {

            }else if(it.resultCode == 55){
                val value = it.data
                email_edit_text.setText(value?.getStringExtra("email"))
                password_edit_text.setText(value?.getStringExtra("password"))

            }
        }
}






















