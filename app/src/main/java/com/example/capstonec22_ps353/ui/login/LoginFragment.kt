package com.example.capstonec22_ps353.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.capstonec22_ps353.databinding.FragmentLoginBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Please wait")
        progressDialog.setTitle("Loggin In...")
        progressDialog.setCanceledOnTouchOutside(false)

        checkUser()

        binding.btnLogin.setOnClickListener {
//            firebaseLogin()
        }

        lifecycleScope.launch {
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                val navController = it
                val callback = object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        navController.popBackStack()
                    }
                }

//                 navController.previousBackStackEntry

                requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
                binding.btnBack.setOnClickListener {
                    navController.popBackStack()
//                    navController.navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }
//       binding.btnBack.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
//       }
    }

    private fun firebaseLogin() {
        progressDialog.show()

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val emailfire = firebaseUser?.email

                Toast.makeText(activity, "Login Success as $emailfire", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(activity, "Login Failed", Toast.LENGTH_SHORT).show()
            }

    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            Toast.makeText(activity, "Berhasil", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, P))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}