package com.example.caffaine2222

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class login : AppCompatActivity() {
    lateinit var go_to_signup: TextView
    lateinit var email_edit_text: EditText
    lateinit var password_edit_text: EditText
    lateinit var go_to_home: Button
    lateinit var wv:Button
    lateinit var gmail_button:Button
    private lateinit var auth: FirebaseAuth
    lateinit var oneTapClient: SignInClient
    lateinit var signInRequest: BeginSignInRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        Firebase.auth.signOut()
        setContentView(R.layout.activity_login)
        go_to_signup = findViewById(R.id.go_to_sign_up_text)
        go_to_home = findViewById(R.id.login_button)
        email_edit_text = findViewById(R.id.email_log)
        password_edit_text = findViewById(R.id.password_log)
        gmail_button = findViewById(R.id.gmail)
        email_edit_text.setText("k@k.com")
        password_edit_text.setText("12345678")

        go_to_signup.setOnClickListener {
            var intent = Intent(this, signUpFire::class.java)
            getResult.launch(intent)
        }

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId("404650031080-7oqk6jp3psa7ohgps5k1uuaj0lj7a6ro.apps.googleusercontent.com")
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)

                    .build())
            .build()
        go_to_home.setOnClickListener {
//            var intent = Intent(this, RvActivity::class.java)
//            //startActivity(intent)
//            if (validate()) {
//                startActivity(intent)
//
//            }
            auth.signInWithEmailAndPassword(email_edit_text.text.toString(), password_edit_text.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("firaya", "signInWithEmail:success")
                        val user = auth.currentUser
                        var intent = Intent(this, RvActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("firaya", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }

        gmail_button.setOnClickListener {
            oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
                try {

                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, 222,
                        null, 0, 0, 0, null
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("Error", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }.addOnFailureListener {
                Log.d("Error", it.localizedMessage)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 222) {
            val googleCredential = oneTapClient.getSignInCredentialFromIntent(data)
            val idToken = googleCredential.googleIdToken
            when {
                idToken != null -> {
                    // Got an ID token from Google. Use it to authenticate
                    // with Firebase.
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("firaya", "signInWithCredential:success")
                                var intent = Intent(this, RvActivity::class.java)
                                startActivity(intent)
                                val user = auth.currentUser
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("firaya", "signInWithCredential:failure", task.exception)
                            }
                        }
                }
                else -> {
                    // Shouldn't happen.
                    Log.d("firaya", "No ID token!")
                }
            }
        }
    }
}