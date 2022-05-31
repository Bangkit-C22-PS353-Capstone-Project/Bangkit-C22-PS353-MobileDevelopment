package com.example.capstonec22_ps353.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentHomeBinding
import com.example.capstonec22_ps353.databinding.FragmentProfileBinding
import com.example.capstonec22_ps353.ui.SharedViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            sharedViewModel.navController.observe(viewLifecycleOwner) {
                navController = it
                binding.btnCart.setOnClickListener {
                    navController.navigate(R.id.action_mainFragment_to_loginFragment)
                }

            }
        }




        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.farm1))
        imageList.add(SlideModel(R.drawable.farm2))
        imageList.add(SlideModel(R.drawable.farm3))

//        val navHostFragment = childFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment


//            sharedViewModel.navController

//        if (navController==null) {
//        }
        
        binding.imgSliderHome.setImageList(imageList, ScaleTypes.FIT)

//        binding.btnCart.setOnClickListener {
//            navController.navigate(R.id.action_mainFragment_to_loginFragment)
////            navController.navigate(R.id.action_mainFragment_to_loginFragment)
////            Navigation.findNavController(activity, R.id.action_mainFragment_to_loginFragment)
////            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_loginFragment)
//        }
    }

    private fun setNav(navController: NavController) {


        Toast.makeText(activity, "ADA", Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}