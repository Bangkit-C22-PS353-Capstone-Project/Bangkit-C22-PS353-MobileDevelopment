package com.example.capstonec22_ps353.ui.profile

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentProfileBinding
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val prefViewModel: PrefViewModel by activityViewModels()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val img = ResourcesCompat.getDrawable(resources, R.drawable.farm1, null)

        activity?.let {
            Glide.with(it)
                .load(ResourcesCompat.getDrawable(resources, R.drawable.default_profile, null))
                .circleCrop()
                .into(binding.imgAvatarProfile)
        }

        binding.tvLogout.setOnClickListener {
            logOut()
        }

        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->
                    binding.tvUsername.text = user.username
//                    binding.tvEmail.text = user.
                    if (!user.isLogin){
                        navController.navigate(R.id.action_mainFragment_to_loginFragment)
                        Toast.makeText(activity, "Anda Telah Keluar Akun", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

    private fun logOut() {
        AlertDialog.Builder(activity).apply {
            setTitle("Tunggu!!")
            setMessage("Apakah anda yakin untuk keluar dari akun ?")
            setPositiveButton("Yes") { _, _ ->
                prefViewModel.logout()
            }

            setNegativeButton("No") { _, _ ->

            }
            create()
            show()        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}