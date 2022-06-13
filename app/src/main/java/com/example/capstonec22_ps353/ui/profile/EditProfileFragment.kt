package com.example.capstonec22_ps353.ui.profile

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentCartBinding
import com.example.capstonec22_ps353.databinding.FragmentEditProfileBinding
import com.example.capstonec22_ps353.model.UserModel
import com.example.capstonec22_ps353.preferences.PrefViewModel
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val prefViewModel: PrefViewModel by activityViewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->
            if (user.address.isEmpty()) {
                binding.usernameEditProfile.setText(user.username)
                binding.emailEditProfile.setText(user.email)
                binding.passwordEditProfile.isEnabled = false
                binding.passwordEditProfile.setText("default")
                binding.noHpEditProfile.isEnabled = false
                binding.noHpEditProfile.setText(user.noHp)
                binding.addressEditProfile.setText("")
            } else {
                binding.usernameEditProfile.setText(user.username)
                binding.emailEditProfile.setText(user.email)
                binding.passwordEditProfile.isEnabled = false
                binding.passwordEditProfile.setText("default")
                binding.noHpEditProfile.isEnabled = false
                binding.noHpEditProfile.setText(user.noHp)
                binding.addressEditProfile.setText(user.address)
            }


        }


        sharedViewModel.navController.observe(viewLifecycleOwner) {
            val navController = it

            val callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.popBackStack()
                }
            }
            binding.apply {
                imgEditPhoto.setOnClickListener {
                    navController.navigate(R.id.action_editProfileFragment_to_onDevelopFragment)
                }

                prefViewModel.getInfo().observe(viewLifecycleOwner) { user ->

                    btnSave.setOnClickListener {
                        val username = usernameEditProfile.text.toString()
                        val address = addressEditProfile.text.toString()
                        val email = emailEditProfile.text.toString()

                        when {
                            username.isEmpty() -> {
                                textInputUsername.error = "Username tidak boleh kosong"
                            }
                            address.isEmpty() -> {
                                textInputAddress.error = "Alamat tidak boleh kosong"
                            }
                            email.isEmpty() -> {
                                textInputEmail.error = "Email tidak boleh kosong"
                            }
                            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                                textInputEmail.error = "Masukkan format Email yang benar"
                            }
                            else -> {
                                prefViewModel.saveInfo(
                                    UserModel(
                                        user.userId,
                                        email,
                                        username,
                                        user.noHp,
                                        address,
                                        true,
                                    )
                                )

                                navController.popBackStack()

                            }
                        }
                    }
                }
            }



            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
            binding.btnBack.setOnClickListener {
                navController.popBackStack()
            }

            binding.btnCancel.setOnClickListener {
                navController.popBackStack()
            }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}