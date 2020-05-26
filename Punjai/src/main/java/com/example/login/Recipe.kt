package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Recipe : AppCompatActivity() {
    var shname: TextView? = null
    var shamount: TextView? = null
    var shproject: TextView? = null
    var name: String? = null
    var amount: String? = null
    var check: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reeipe)
        shname = findViewById(R.id.Name_do)
        shamount = findViewById(R.id.Amount)
        shproject = findViewById(R.id.project)
        check = intent.extras!!.getString("Check")
        name = intent.extras!!.getString("Name")
        amount = intent.extras!!.getString("Value")
        shname?.setText(name)
        shamount?.setText(amount)
        shproject?.setText(check)
        val backhome = findViewById<Button>(R.id.backhome)
        backhome.setOnClickListener {
            val intent = Intent(this@Recipe, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}