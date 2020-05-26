package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class SettingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View
        val btnLogout: Button

        view= inflater.inflate(R.layout.fragment_setting, container, false)

        btnLogout = view.findViewById(R.id.logout)
        btnLogout?.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intToMain = Intent(getActivity(), LoginActivity::class.java)
            startActivity(intToMain)
        })

        return view


    }



}