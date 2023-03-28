package com.example.caffaine2222

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class Register : AppCompatActivity() {
    lateinit var back_button: ImageButton
    lateinit var sign_up_button: Button
    lateinit var firstname_edit_text : EditText
    lateinit var lastname_edit_text : EditText
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
        firstname_edit_text = findViewById(R.id.firstName_reg)
        lastname_edit_text = findViewById(R.id.lastName_reg)
        email_edit_text = findViewById(R.id.email_reg)
        password_edit_text = findViewById(R.id.password_reg)
        confirmation_password_edit_text = findViewById(R.id.password_reg_confirm)
        phonenumber_edit_text = findViewById(R.id.phone_number_reg)

        back_button.setOnClickListener {
            finish()
        }



        sign_up_button.setOnClickListener {
            var firstName = firstname_edit_text.text.toString()
            var lastName  = lastname_edit_text.text.toString()
            var email = email_edit_text.text.toString()
            var password = password_edit_text.text.toString()
            var phoneNumber = phonenumber_edit_text.text.toString()
            var confirmationPassword = confirmation_password_edit_text.text.toString()
            var user:user = user(firstName,lastName,email,password,phoneNumber)
            if(validate(user,confirmationPassword)) {
                intent.putExtra("email", email_edit_text.getText().toString())
                intent.putExtra("password", password_edit_text.getText().toString())
                ////////////////////////////
                //
                val values = ContentValues() // class can carry my values

                // fetching text from user
                values.put(MyContentProvider.name, firstName.trim() +" "+lastName.trim())
                values.put(MyContentProvider.email, email)
                values.put(MyContentProvider.password, password)
                values.put(MyContentProvider.phoneNumber, phoneNumber)

                // inserting into database through content URI
                try {
                    contentResolver.insert(MyContentProvider.CONTENT_URI, values)
                    setResult(55, intent)
                    finish()
                }catch (e : Exception){
                        Toast.makeText(this,e.message, Toast.LENGTH_SHORT).show()

                }




            }
        }


    }

    private fun validate(user:user,confirmationPassword:String): Boolean {

        var result = true
        if(user.PhoneNumber.length != 11){
            error = "The phone number is not valid"
            phonenumber_edit_text.setError(error)
            phonenumber_edit_text.requestFocus();
            result = false
        }
        if(!user.Password.equals(confirmationPassword)){
            error = "The confirmation password is not valid"
            confirmation_password_edit_text.setError(error)
            confirmation_password_edit_text.requestFocus();
            result = false
        }
        if(user.Password.length < 8){
            error = "This password is too short"
            password_edit_text.setError(error)
            password_edit_text.requestFocus();
            result = false
        }
        if (!user.Email.contains("@") || !user.Email.contains(".com")){
            error = "Please put valid email"
            email_edit_text.setError(error)
            email_edit_text.requestFocus();
            result = false
        }

        if (user.LastName.isBlank()){
            error = "Please put valid name"
            lastname_edit_text.setError(error)
            lastname_edit_text.requestFocus();
            result = false
        }

        if (user.FirstName.isBlank()){
            error = "Please put valid name"
            firstname_edit_text.setError(error)
            firstname_edit_text.requestFocus();
            result = false
        }

        return result
    }
}