package com.alexandrestefani.cinemaempauta.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.cinemaempauta.Model.Posters
import com.alexandrestefani.cinemaempauta.R

class AdapterPosters(private val carouselPosters: MutableList<Posters>) : RecyclerView.Adapter<AdapterPosters.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_posters_details, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return carouselPosters.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPoster = carouselPosters[position]
        holder.posterImage.setImageResource(currentPoster.imagePosters)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImage: ImageView = itemView.findViewById(R.id.image_carrocel_poster)
    }
}