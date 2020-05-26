package com.example.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_reeipe.*
import kotlinx.android.synthetic.main.donator_info.*
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : Fragment() {
    private lateinit var re:RecyclerView
    private lateinit var v:View
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    lateinit var Donatorlist:MutableList<Donator>
    lateinit var Donatorlistch:MutableList<Donator>
    lateinit var listView2: ListView
    lateinit var listView1: ListView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View

        view=inflater.inflate(R.layout.fragment_history, container, false)
        Donatorlist= mutableListOf()
        Donatorlistch = mutableListOf()
        listView2= view.findViewById(R.id.donatorlist)
        val user = FirebaseAuth.getInstance().currentUser
        val uid= user?.uid
//        listView1 = view.findViewById(R.id.childrendonator)
        reference =FirebaseDatabase.getInstance().getReference("Donator")

        reference.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
               if (p0.exists())
               {
                   for (h in p0.children){

                       for (f in h.children)
                       {
                           val Donator= f.getValue(Donator::class.java)
                           Donatorlist.add(Donator!!)
                       }
                   }
                   val adapter=DonatorAdapter(activity!!.applicationContext,R.layout.donator_info,Donatorlist)
                   listView2.adapter=adapter
               }
            }

        })

//        reference2=FirebaseDatabase.getInstance().getReference("Donator").child("Children")
//
//        reference2.addValueEventListener(object : ValueEventListener{
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0.exists())
//                {
//                    for (h in p0.children){
//                        val Donator= h.getValue(Donator::class.java)
////                       val name = h.getValue(com.example.login.Donator::class.java)?.name
//
//                        Donatorlistch.add(Donator!!)
//                    }
//                    val adapter=DonatorAdapter(activity!!.applicationContext,R.layout.donator_info,Donatorlistch)
//                    listView1.adapter=adapter
//                }
//
//
//        }
//
//        })


        return view;
    }




}