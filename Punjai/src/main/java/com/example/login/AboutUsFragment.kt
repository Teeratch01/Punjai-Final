package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class AboutUsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val contactusBTN :Button
        val view: View
        view= inflater.inflate(R.layout.fragment_aboutus, container, false)

        contactusBTN=view.findViewById(R.id.contactus)

        contactusBTN.setOnClickListener(View.OnClickListener {
                val gotocontact = Intent(getActivity(), Contact_us::class.java)
                startActivity(gotocontact)
        })
        return view

    }
}