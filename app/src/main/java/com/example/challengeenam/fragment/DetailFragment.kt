@file:Suppress("unused", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "ReplaceGetOrSet", "ReplaceGetOrSet", "DeferredResultUnused"
)

package com.example.challengeenam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challengeenam.databinding.FragmentDetailBinding
import com.example.challengeenam.model.Movie
import com.example.challengeenam.room.FavDao
import com.example.challengeenam.room.FavDatabase
import com.example.challengeenam.room.FavNote
import com.example.challengeenam.viewmodel.FavoritViewModel
import com.example.challengeenam.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("unused", "unused", "unused", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "ReplaceGetOrSet",
    "ReplaceGetOrSet", "ReplaceGetOrSet", "ReplaceGetOrSet", "KotlinConstantConditions",
    "KotlinConstantConditions", "DeferredResultUnused"
)
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    lateinit var filmViewModel: MovieViewModel
    lateinit var favoritViewModel: FavoritViewModel
    private var favDao: FavDao? = null
    private var favDatabase: FavDatabase? = null
    private var id :Int?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        favoritViewModel = ViewModelProvider(this).get(FavoritViewModel::class.java)

        favDatabase = FavDatabase.getInstance(requireContext())
        favDao = favDatabase?.favoritDao()

        // pengambilan data
        val list = arguments?.getParcelable<Movie>("data_movie") as Movie
        val title = list.title
        val date = list.releaseDate
        val overview = list.overview
        val imagepath = list.imagePath

        binding.tvTitle.text = title
        binding.tvTglRelease.text = date
        binding.tvDesc.text = overview

        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${imagepath}")
            .into(binding.imgMovie)

        binding.addFav.setOnClickListener {
            GlobalScope.async {

                val getFavMov = arguments?.getParcelable<Movie>("data_movie") as Movie
                val id = getFavMov.id
                val title = getFavMov.title
                val date = getFavMov.releaseDate
                val gambar = getFavMov.imagePath
                val overview = getFavMov.overview

                val favDetail = favDatabase?.favoritDao()?.addToFavorit(FavNote(id, title, date, gambar, overview))

                activity?.runOnUiThread {
                    if (favDetail != 0.toLong()){
                        Toast.makeText(context, "Add to Favorite Success", Toast.LENGTH_LONG).show()
                        var check = false
                        check = !check
                        binding.addFav.isChecked = check

                    }else{
                        Toast.makeText(context, "Add to Favorite Failed", Toast.LENGTH_LONG).show()
                    }
                }

            }

        }
    }
}