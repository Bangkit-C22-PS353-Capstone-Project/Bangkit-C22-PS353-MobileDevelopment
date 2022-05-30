package com.example.capstonec22_ps353.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.capstonec22_ps353.R
import com.example.capstonec22_ps353.databinding.FragmentHomeBinding
import com.example.capstonec22_ps353.databinding.FragmentProfileBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

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

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.farm1))
        imageList.add(SlideModel(R.drawable.farm2))
        imageList.add(SlideModel(R.drawable.farm3))

        binding.imgSliderHome.setImageList(imageList, ScaleTypes.FIT)

//        binding.tvHome.setOnClickListener {
////            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}