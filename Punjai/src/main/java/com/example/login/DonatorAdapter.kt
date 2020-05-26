package com.example.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class DonatorAdapter (val mCtx :Context,val  layoutResId :Int,val Donatorlist :List<Donator>)
    :ArrayAdapter<Donator>(mCtx,layoutResId,Donatorlist)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View = layoutInflater.inflate(layoutResId,null)


        val textView =view.findViewById<TextView>(R.id.Doater_info)
        val textView2=view.findViewById<TextView>(R.id.Doater_am)
        val textView3=view.findViewById<TextView>(R.id.Doater_pm)
        val textView4=view.findViewById<TextView>(R.id.Doater_pj)

        val Donator = Donatorlist[position]
        textView.text=Donator.name
        textView2.text=Donator.amount
        textView3.text=Donator.paymentmet
        textView4.text=Donator.project

        return view

    }

}