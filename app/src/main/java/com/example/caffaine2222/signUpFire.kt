package com.example.caffaine2222

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class signUpFire: AppCompatActivity(){
    lateinit var email_editText : EditText
    lateinit var password_editText : EditText
    lateinit var signup_button : Button
    private lateinit var auth: FirebaseAuth
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null){
                var intent = Intent(this, RvActivity::class.java)
                startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.signup_firebase)
        email_editText = findViewById(R.id.email_reg_fire)
        password_editText = findViewById(R.id.password_reg_fire)
        signup_button = findViewById(R.id.sign_up_button_fire)
        signup_button.setOnClickListener {
            var TAG = "firaya"
            if (validate(email_editText.text.toString(), password_editText.text.toString())){
                auth.createUserWithEmailAndPassword(email_editText.text.toString(), password_editText.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            var intent = Intent(this, RvActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun validate(Email:String,Password:String): Boolean {

        var result = true
        var error = ""
        if(Password.length < 8){
            error = "This password is too short"
            password_editText.setError(error)
            password_editText.requestFocus();
            result = false
        }
        if (!Email.contains("@") || !Email.contains(".com")){
            error = "Please put valid email"
            email_editText.setError(error)
            email_editText.requestFocus();
            result = false
        }

        return result
    }
}