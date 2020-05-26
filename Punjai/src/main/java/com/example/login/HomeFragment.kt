package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text
import java.util.*


class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val DonationBTN :Button
        val Childrenbtn:Button
        val view: View
        val host :Int
        val bundle:Bundle
        val mdatabase:DatabaseReference
        val amount: TextView
        val chamount : TextView
        view= inflater.inflate(R.layout.fragment_home, container, false)

        amount= view.findViewById(R.id.CurrentDonate)
        chamount = view.findViewById(R.id.currentDonate2)
        mdatabase = FirebaseDatabase.getInstance().getReference("Donator").child("Cyclone")
        mdatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                var sum:Int=0
                for (ds in p0.children)
                {

                        val amount = ds.getValue(com.example.login.Donator::class.java)?.amount
                        if (amount=="500")
                        {
                            sum+=500
                        }
                        else if (amount == "1000")
                        {
                            sum+=1000
                        }
                        else
                        {
                            sum+=2000
                        }


//                    val map = ds.getValue() as Map<String,String>
//                    val price = map.get("Amount")
//                    val pValue = price.toString().toInt()
//                    sum+= pValue
                }
                amount.setText(sum.toString())
                progressBar.progress=(sum*100)/1000000
            }

        })

        val chdatabase = FirebaseDatabase.getInstance().getReference("Donator").child("Children")
        chdatabase.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                var sum2 :Int=0
                for (ds in p0.children)
                {

                    val amount = ds.getValue(com.example.login.Donator::class.java)?.amount
                    if (amount=="500")
                    {
                        sum2+=500
                    }
                    else if (amount == "1000")
                    {
                        sum2+=1000
                    }
                    else
                    {
                        sum2+=2000
                    }


//                    val map = ds.getValue() as Map<String,String>
//                    val price = map.get("Amount")
//                    val pValue = price.toString().toInt()
//                    sum+= pValue
                }
                chamount.setText(sum2.toString())
                progressBar2.progress=(sum2*100)/1000000
            }


        })

        DonationBTN=view.findViewById(R.id.Donate)
        DonationBTN.setOnClickListener(View.OnClickListener {
            val gotoDonate = Intent(getActivity(), DonationAct::class.java)
            startActivity(gotoDonate)
        })

        Childrenbtn=view.findViewById(R.id.donate2)
        Childrenbtn.setOnClickListener(View.OnClickListener {
            val gotochidren = Intent(getActivity(), chioldrendonation::class.java)
            startActivity(gotochidren)
        })
        return view
    }


}