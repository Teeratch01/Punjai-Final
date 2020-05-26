package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    var EmailId: EditText? = null
    var Password: EditText? = null
    var btnSignUp: Button? = null
    var tvSignIn: TextView? = null
    var mFirebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAuth = FirebaseAuth.getInstance()
        EmailId = findViewById(R.id.editText)
        Password = findViewById(R.id.editText2)
        btnSignUp = findViewById(R.id.button)
        tvSignIn = findViewById(R.id.textView)
        btnSignUp?.setOnClickListener(View.OnClickListener {
            val email = EmailId?.text.toString()
            val pwd = Password?.text.toString()
            if (email.isEmpty()) {
                EmailId?.setError("Please enter your email")
                EmailId?.requestFocus()
            } else if (pwd.isEmpty()) {
                Password?.setError("Please enter your password")
                Password?.requestFocus()
            } else if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(this@MainActivity, "Fields Are Empty!", Toast.LENGTH_SHORT).show()
            } else if (!(email.isEmpty() && pwd.isEmpty())) {
                mFirebaseAuth!!.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this@MainActivity) { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(this@MainActivity, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show()
                    } else {
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    }
                }
            } else {
                Toast.makeText(this@MainActivity, "Error Occurred!", Toast.LENGTH_SHORT).show()
            }
        })
        tvSignIn?.setOnClickListener(View.OnClickListener {
            val i = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(i)
        })
    }
}