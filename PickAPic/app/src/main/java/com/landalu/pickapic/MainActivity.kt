package com.landalu.pickapic

import android.Manifest
import android.R.attr
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.landalu.pickapic.fragments.AddFragment
import com.landalu.pickapic.fragments.HomeFragment
import com.landalu.pickapic.fragments.TrashFragment
import com.landalu.pickapic.fragments.UserFragment

class MainActivity : AppCompatActivity() {
    private val addFragment = AddFragment()
    private val userFragment = UserFragment()
    private val homeFragment= HomeFragment()
    private val trashFragment = TrashFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_add -> replaceFragment(addFragment)
                R.id.ic_trash -> replaceFragment(trashFragment)
                R.id.ic_user -> replaceFragment(userFragment)
            }
            true

        }

    }
    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()

        }
    }
}