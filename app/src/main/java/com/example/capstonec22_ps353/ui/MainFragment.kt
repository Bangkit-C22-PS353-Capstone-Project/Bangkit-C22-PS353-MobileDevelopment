package com.example.capstonec22_ps353.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.ActivityMainBinding
import com.example.capstonec22_ps353.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment

        val token = "ada"
//            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        setupWithNavController(bottomNavigationView, navController)

//        bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId) {
//
//                R.id.profileFragment -> {
//                    findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
//                }
//
//            }
//
//            true
//
//        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        when(item.itemId) {
            R.id.profileFragment -> {
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }

        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_nav_menu ,menu)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}