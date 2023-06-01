package com.example.challengeenam.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challengeenam.R
import com.example.challengeenam.databinding.FragmentSpalshBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpalshFragment : Fragment() {
    lateinit var binding: FragmentSpalshBinding
    private lateinit var splash: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSpalshBinding.inflate(layoutInflater, container, false)
        Handler(Looper.myLooper()!!).postDelayed({

            splash = requireContext().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
            if (splash.getString("email", "").equals("")) {
                findNavController().navigate(R.id.action_spalshFragment_to_loginFragment)
            }else if (splash.getString("email", "")!!.isNotEmpty()) {
                findNavController().navigate(R.id.action_spalshFragment_to_homeFragment)
            }
        }, 2500)
        return binding.root
    }


}