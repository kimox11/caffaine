package com.example.caffaine

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
            var intent = Intent(this,DownloadPhoto::class.java)
            startActivity(intent)
            if(validate()) {
                startActivity(intent)

            }
        }
    }

    private fun validate(): Boolean {
        var email = email_edit_text.text.toString()
        var password = password_edit_text.text.toString()
        var result = false
        var error = "Email or password not valid"

        userManager.users.forEach{
            if(it.Email.equals(email) && it.Password.equals(password)){
                userManager.currentUser = it
                result = true
                return result
            }
        }
        email_edit_text.setError("")
        password_edit_text.setError("")
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()


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






















