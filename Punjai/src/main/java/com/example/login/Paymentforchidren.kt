package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Paymentforchidren : AppCompatActivity() {
    private var submit: Button? = null
    var amount: RadioGroup? = null
    var amountop: RadioButton? = null
    var payment: RadioGroup? = null
    var pamentop: RadioButton? = null
    var fivethb: RadioButton? =null
    var tenthb: RadioButton? =null
    var twenthb: RadioButton? =null
    var inname: EditText? = null
    var inamount: EditText?=null
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var chamount: String? = null
    var chpayment: String? = null
    var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activit_paymentforchild)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        database = FirebaseDatabase.getInstance()
        inname = findViewById<View>(R.id.in_namech) as EditText
        submit = findViewById<View>(R.id.submitch) as Button
        amount = findViewById<View>(R.id.Amointgch) as RadioGroup
        payment = findViewById<View>(R.id.Paymentgch) as RadioGroup
        fivethb =findViewById(R.id.fivethbch) as RadioButton
        tenthb =findViewById(R.id.tenthbch) as RadioButton
        twenthb=findViewById(R.id.twentythbch) as RadioButton


        amount!!.setOnCheckedChangeListener { group, checkedId ->
            amountop = amount!!.findViewById(checkedId)

            when (checkedId) {
                R.id.fivethbch ->{chamount = amountop?.getText().toString()}
                R.id.tenthbch -> {chamount = amountop?.getText().toString()}
                R.id.twentythbch -> {chamount = amountop?.getText().toString()}

                else -> {


                }
            }
        }

        payment!!.setOnCheckedChangeListener { group, j ->
            pamentop = payment!!.findViewById(j)
            when (j) {
                R.id.creditch -> chpayment = pamentop?.getText().toString()
                R.id.Bankch -> chpayment = pamentop?.getText().toString()
                else -> {
                }
            }
        }
        submit!!.setOnClickListener {
            val checkname = inname?.getText().toString()
            if(checkname.isEmpty())
            {
                inname?.setError("Please fill your name")
                inname?.requestFocus()
            }else if(amount?.checkedRadioButtonId==-1)
            {
                Toast.makeText(this, "Please fill the Amount", Toast.LENGTH_SHORT).show()
            }
            else if(payment?.checkedRadioButtonId==-1)
            {
                Toast.makeText(this, "Please fill the payment method", Toast.LENGTH_SHORT).show()
            }
            else if (!(checkname.isEmpty())) {
                addDonator()
                val i = Intent(this, Recipe::class.java)
                val check: String = "Donate to an Education Charity in Thailand"
                name = inname!!.text.toString()
                i.putExtra("Check", check)
                i.putExtra("Name", name)
                i.putExtra("Value", chamount)
                startActivity(i)
            }
        }
    }
    private fun addDonator() {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        val editText = findViewById<View>(R.id.in_namech) as EditText
        val myDb=FirebaseDatabase.getInstance().getReference("Donator").child("Children")
        val project:String = "Donate to an Education Charity in Thailand"
        val donatores= Donator(chamount!!,editText.text.toString(),chpayment!!,project)
        val child = uid.toString()
        myDb.child(child).setValue(donatores).addOnCompleteListener{
            Toast.makeText(this, "Donator added", Toast.LENGTH_LONG).show()
        }

//        reference = database!!.getReference("Donator").child(child)
//        val donath: Any = Donator(chamount!!, child, chpayment!!)
//        reference!!.child("Name").setValue(editText.text.toString())
//        reference!!.child("Amount").setValue(chamount)
//        reference!!.child("Payment Method").setValue(chpayment)
//        Toast.makeText(this, "Donator added", Toast.LENGTH_LONG).show()
    }
}
