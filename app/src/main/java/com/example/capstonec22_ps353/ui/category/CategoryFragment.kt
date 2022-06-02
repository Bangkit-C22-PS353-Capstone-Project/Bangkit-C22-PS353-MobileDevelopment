package com.example.capstonec22_ps353.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentCategoryBinding
import com.example.capstonec22_ps353.utils.SharedViewModel
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    val args: CategoryFragmentArgs by navArgs()

    private var _binding: FragmentCategoryBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val category = args.category

        binding.tvCategory.setText(category.toString())

//        val args = CategoryFragmentArgs.fromBundle(category)


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
                binding.tvCategory.setOnClickListener {
                    navController.navigate(R.id.action_categoryFragment_to_loginFragment)
//                    navController.navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}