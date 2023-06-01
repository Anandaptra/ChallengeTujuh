@file:Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "RemoveEmptyClassBody", "RemoveEmptyClassBody", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "CanBeVal", "CanBeVal", "CanBeVal", "CanBeVal", "CanBeVal"
)

package com.example.challengeenam.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeenam.MainActivity
import com.example.challengeenam.R
import com.example.challengeenam.databinding.ItemFavoritBinding
import com.example.challengeenam.model.Movie
import com.example.challengeenam.room.FavDatabase
import com.example.challengeenam.room.FavNote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Suppress("unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused", "unused",
    "unused", "RemoveRedundantQualifierName", "RemoveEmptyClassBody", "RemoveEmptyClassBody",
    "RemoveEmptyClassBody", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate",
    "MemberVisibilityCanBePrivate", "CanBeVal", "CanBeVal", "CanBeVal", "CanBeVal",
    "DeferredResultUnused"
)
class FavoritAdapter(private var context : Context, private var listFav: List <FavNote>): RecyclerView.Adapter<FavoritAdapter.ViewHolder>() {
    private var filmFavDB: FavDatabase? = null

    class ViewHolder ( val binding: ItemFavoritBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFavoritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritAdapter.ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.etJudul.text = listFav[position].title
        holder.binding.etTanggal.text = listFav[position].tanggal

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w780${listFav[position].poster_path}")
            .into(holder.binding.imgMovie)

        holder.binding.cvFavoritMoview.setOnClickListener {
            val title = listFav[position].title
            val date = listFav[position].tanggal
            val overview = listFav[position].overview
            val image = listFav[position].poster_path
            val id = listFav[position].id

            val detail = Movie(id,title,overview,date,image)
            val bundle = Bundle()
            bundle.putParcelable("data_movie", detail)
            Navigation.findNavController(it).navigate(R.id.action_favoritFragment_to_detailFragment, bundle)
        }

        holder.binding.etCheck.setOnClickListener {
            var favorit = holder.binding.etCheck.isChecked
            if (favorit){
                filmFavDB = FavDatabase.getInstance(it.context)

                AlertDialog.Builder(it.context)
                    .setTitle("Hapus Data")
                    .setMessage("Apakah Anda Ingin Menghapus Item")
                    .setPositiveButton("Iya") { _: DialogInterface, _: Int ->
                        GlobalScope.async {
                            val result = filmFavDB?.favoritDao()?.deleteFilmFavorit(
                                listFav[position])

                            (holder.itemView.context as MainActivity).runOnUiThread {
                                if (result != 0) {
                                    Toast.makeText(it.context, "Item ${listFav[position].title} Terhapus", Toast.LENGTH_LONG).show()
                                } else {
                                    Toast.makeText(it.context, "Item ${listFav[position].title} Gagal terhapus", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    .setNegativeButton("Tidak") { dialogInterface: DialogInterface, _: Int ->
                        dialogInterface.dismiss()
                        favorit = holder.binding.etCheck.isChecked
                    }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listFav.size
    }
    fun setMovie(itemMovie: ArrayList<FavNote>) {
        this.listFav = itemMovie
    }
}