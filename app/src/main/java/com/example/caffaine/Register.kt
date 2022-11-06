package com.example.caffaine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class Register : AppCompatActivity() {
    lateinit var back_button: ImageButton
    lateinit var sign_up_button: Button
    lateinit var firstname_edit_text : TextView
    lateinit var email_edit_text: EditText
    lateinit var password_edit_text: EditText
    lateinit var phonenumber_edit_text: EditText
    lateinit var confirmation_password_edit_text: EditText
    var error = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        back_button = findViewById(R.id.b_button)
        sign_up_button = findViewById(R.id.sign_up_button)
//        firstname_edit_text = findViewById(R.id.)
        email_edit_text = findViewById(R.id.email_reg)
        password_edit_text = findViewById(R.id.password_reg)
        confirmation_password_edit_text = findViewById(R.id.password_reg_confirm)
        phonenumber_edit_text = findViewById(R.id.phone_number_reg)

        back_button.setOnClickListener {
            var intent = Intent(this,login::class.java)
            startActivity(intent)
        }



        sign_up_button.setOnClickListener {
            if(validate()) {
                intent.putExtra("email", email_edit_text.getText().toString())
                intent.putExtra("password", password_edit_text.getText().toString())
//                intent.putExtra("password", password_edit_text.getText().toString())
                setResult(55, intent)
                finish()
            }else{

            }
        }


    }

    private fun validate(): Boolean {
        var email = email_edit_text.getText().toString()
        var password = password_edit_text.getText().toString()
        var phoneNumber = phonenumber_edit_text.getText().toString()
        var confirmationPassword = confirmation_password_edit_text.getText().toString()
        var result = true
        if(phoneNumber.length != 12){
            error = "The phone number is not valid"
            phonenumber_edit_text.setError(error)
            phonenumber_edit_text.requestFocus();
            result = false
        }
        if(!password.equals(confirmationPassword)){
            error = "The confirmation password is not valid"
            confirmation_password_edit_text.setError(error)
            confirmation_password_edit_text.requestFocus();
            result = false
        }
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
}