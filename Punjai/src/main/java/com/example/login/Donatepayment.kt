package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Donatepayment : AppCompatActivity() {
    private var submit: Button? = null
    var amount: RadioGroup? = null
    var amountop: RadioButton? = null
    var payment: RadioGroup? = null
    var pamentop: RadioButton? = null
    var fivethb: RadioButton? =null
    var tenthb: RadioButton? =null
    var twenthb: RadioButton? =null
    var inname: EditText? = null
    var inamount:EditText?=null
    var database: FirebaseDatabase? = null
    var reference: DatabaseReference? = null
    var chamount: String? = null
    var chpayment: String? = null
    var name: String? = null
    var amountsum: Int? = null
    //    Intent new_intent = new Intent(this,HomeFragment.class);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donatepayment)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        database = FirebaseDatabase.getInstance()
        inname = findViewById<View>(R.id.in_name) as EditText
        submit = findViewById<View>(R.id.submit) as Button
        amount = findViewById<View>(R.id.Amointg) as RadioGroup
        payment = findViewById<View>(R.id.Paymentg) as RadioGroup
        fivethb =findViewById(R.id.fivethb) as RadioButton
        tenthb =findViewById(R.id.tenthb) as RadioButton
        twenthb=findViewById(R.id.twentythb) as RadioButton


            amount!!.setOnCheckedChangeListener { group, checkedId ->
                amountop = amount!!.findViewById(checkedId)

                when (checkedId) {
                    R.id.fivethb ->{chamount = amountop?.getText().toString()}
                    R.id.tenthb -> {chamount = amountop?.getText().toString()}
                    R.id.twentythb -> {chamount = amountop?.getText().toString()}

                    else -> {


                }
            }
        }

        payment!!.setOnCheckedChangeListener { group, j ->
            pamentop = payment!!.findViewById(j)
            when (j) {
                R.id.credit -> chpayment = pamentop?.getText().toString()
                R.id.Bank -> chpayment = pamentop?.getText().toString()
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
            else if (!(checkname.isEmpty()))
            {
                addDonator()
                val i = Intent(this@Donatepayment, Recipe::class.java)
                val check:String = "Help children affected by cyclone"
                name = inname!!.text.toString()
                i.putExtra("Check",check)
                i.putExtra("Name", name)
                i.putExtra("Value", chamount)
                startActivity(i)
            }

        }
    }

    private fun addDonator() {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        val editText = findViewById<View>(R.id.in_name) as EditText
        val myDb=FirebaseDatabase.getInstance().getReference("Donator").child("Cyclone")
        val project:String = "Help children affected by cyclone"
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