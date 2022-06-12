package com.example.capstonec22_ps353.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch


class SplashScreenFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val prefViewModel: PrefViewModel by activityViewModels()

    private val splashTime:Long = 3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                Handler(Looper.getMainLooper()).postDelayed({
                    prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->
                        if (user.isLogin) {
                            navController.navigate(R.id.action_splashScreenFragment_to_mainFragment)
                        } else {
                            navController.navigate(R.id.action_splashScreenFragment_to_loginFragment)
                        }
                    }
                }, splashTime)
            }
        }
    }


}