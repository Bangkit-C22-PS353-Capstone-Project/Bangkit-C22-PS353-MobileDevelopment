package com.example.capstonec22_ps353.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
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

        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        setupWithNavController(bottomNavigationView, navController)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}