package com.example.capstonec22_ps353.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val sharedViewModel by viewModels<SharedViewModel>()

    private var _binding: ActivityMainBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController


        sharedViewModel.setNavController(navController)

//        sharedViewModel.navController = navController



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

    

    override fun onDestroy() {
        super.onDestroy()
    }
}