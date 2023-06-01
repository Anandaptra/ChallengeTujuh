@file:Suppress("RedundantSamConstructor", "ReplaceGetOrSet")

package com.example.challengeenam.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeenam.R
import com.example.challengeenam.adapter.MovieAdapter
import com.example.challengeenam.databinding.FragmentHomeBinding
import com.example.challengeenam.viewmodel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var shredPref : SharedPreferences
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel : MovieViewModel
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(ArrayList())
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        firebaseAuth = FirebaseAuth.getInstance()

        shredPref = requireActivity().getSharedPreferences("dataregistrasi", Context.MODE_PRIVATE)
        val username = shredPref.getString("username", "username")
        binding.etWelcome.text = "Welcome, $username"

        binding.rvList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = movieAdapter

        movieViewModel.getApiMovie()

        movieViewModel.liveDataMovie.observe(viewLifecycleOwner, Observer {
            movieAdapter.setDataMovie(it)
        })

        //Profile
        binding.profile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.favorit.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_homeFragment_to_favoritFragment)
        }


    }
}