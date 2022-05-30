package com.example.capstonec22_ps353.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.ActivityMainBinding
import com.example.capstonec22_ps353.databinding.FragmentProfileBinding
import com.example.capstonec22_ps353.ui.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

//        setupWithNavController(navController)

//        NavigationBarView.OnItemSelectedListener { item ->
//            when(item.itemId) {
//                R.id.homeFragment -> {
//                    // Respond to navigation item 1 click
//                    true
//                }
//                R.id.priceFragment -> {
//                    // Respond to navigation item 2 click
//                    true
//                }
//                R.id.profileFragment -> {
//                    Navigation.createNavigateOnClickListener(
//                        R.id.action_profileFragment_to_loginFragment
//                    )
//                    // Respond to navigation item 2 click
//                    true
//                }
//                else -> false
//            }
//        }

    }

    private fun moveIntent() {
        startActivity(Intent(this, LoginActivity::class.java))

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}