package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


class HomeActivity : AppCompatActivity() {
    var btnLogout: Button? = null
    var mFirebaseAuth: FirebaseAuth? = null
    var bottomNav : BottomNavigationView?=null
    private val mAuthtateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    HomeFragment()).commit()
        }
//        btnLogout = findViewById(R.id.logout)
//        btnLogout?.setOnClickListener(View.OnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            val intToMain = Intent(this@HomeActivity, MainActivity::class.java)
//            startActivity(intToMain)
//        })
    }
    private val navListener= object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var selectedFragment: Fragment? = null
            when (item.getItemId()) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_history -> selectedFragment = HistoryFragment()
                R.id.nav_aboutus -> selectedFragment = AboutUsFragment()
                R.id.nav_setting -> selectedFragment = SettingFragment()
            }
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit()
            }
            return true
        }

    }

}