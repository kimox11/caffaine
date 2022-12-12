package com.example.caffaine

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
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
    lateinit var go_to_signup: TextView
    lateinit var email_edit_text: EditText
    lateinit var password_edit_text: EditText
    lateinit var go_to_home: Button
    lateinit var wv:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        go_to_signup = findViewById(R.id.go_to_sign_up_text)
        go_to_home = findViewById(R.id.login_button)
        email_edit_text = findViewById(R.id.email_log)
        password_edit_text = findViewById(R.id.password_log)
        wv = findViewById(R.id.Wv_button)
        wv.setOnClickListener {
            intent = Intent(this,Web_View::class.java)
            startActivity(intent)
        }

        go_to_signup.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            getResult.launch(intent)
        }

        go_to_home.setOnClickListener {
            var intent = Intent(this, Web_View::class.java)
            //startActivity(intent)
            if (validate()) {
                startActivity(intent)

            }
        }
    }

    @SuppressLint("Range")
    private fun validate(): Boolean {
        var email = email_edit_text.text.toString()
        var password = password_edit_text.text.toString()
        var result = false

        // creating a cursor object of the
        // content URI
        val cursor = contentResolver.query(
           MyContentProvider.CONTENT_URI,
            null,
            "(${MyContentProvider.email} = \'$email\') AND " +
                    "(${MyContentProvider.password} = \'$password\')",
            null,
            null
        )

        if (cursor!!.moveToFirst()) {
            var user = user(
                cursor.getString(cursor.getColumnIndex(MyContentProvider.name))
                    .split(" ")[0],
                cursor.getString(cursor.getColumnIndex(MyContentProvider.name))
                    .split(" ")[1],
                cursor.getString(cursor.getColumnIndex(MyContentProvider.email)),
                cursor.getString(cursor.getColumnIndex(MyContentProvider.password)),
                cursor.getString(cursor.getColumnIndex(MyContentProvider.phoneNumber))
                )
            userManager.currentUser = user
            result = true;

        } else {
            Toast.makeText(this,"Email or password wrong",Toast.LENGTH_SHORT).show()
        }

        return result
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {

            } else if (it.resultCode == 55) {
                val value = it.data
                email_edit_text.setText(value?.getStringExtra("email"))
                password_edit_text.setText(value?.getStringExtra("password"))

            }
        }
}