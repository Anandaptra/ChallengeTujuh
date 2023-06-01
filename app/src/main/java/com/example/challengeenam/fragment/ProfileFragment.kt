@file:Suppress("CanBeVal")

package com.example.challengeenam.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.challengeenam.R
import com.example.challengeenam.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private lateinit var sharedpref : SharedPreferences
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = requireActivity().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)

        val getUser = sharedpref.getString("username", "")
        binding.updateUsername.setText(getUser)


        binding.btnUpdate.setOnClickListener {
            val username = binding.updateUsername.text.toString()

            var upusername = sharedpref.edit()
            upusername.putString("username", username)
            upusername.apply()
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            Toast.makeText(context, "Success Update Data", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_homeFragment)
        }

        binding.btnLogout.setOnClickListener {
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            val addUser = sharedpref.edit()
            addUser.apply()
            Toast.makeText(context, "Logout Success", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.root).navigate(R.id.action_profileFragment_to_loginFragment)
        }

    }

}