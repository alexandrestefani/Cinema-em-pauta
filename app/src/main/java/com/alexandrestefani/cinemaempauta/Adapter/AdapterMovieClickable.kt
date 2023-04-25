package com.alexandrestefani.cinemaempauta.Adapter



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.cinemaempauta.Model.Movies
import com.alexandrestefani.cinemaempauta.R

class AdapterMovies(movielist: List<Movies>) : RecyclerView.Adapter<AdapterMovies.MyViewHolder>() {

    private val movielist = movielist.toMutableList()
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(pos: Int)
    }
    fun onItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(itemView,mListener)
    }


    override fun getItemCount(): Int {
        return movielist.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentMovie = movielist[position]
        holder.movieName.text = currentMovie.name
        holder.movieDescription.text = currentMovie.description
        holder.imageDescription.setImageResource(currentMovie.picture)
    }


    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.nome_card)
        val movieDescription: TextView = itemView.findViewById(R.id.description_card)
        val imageDescription: ImageView = itemView.findViewById(R.id.image_card)

        init {
                itemView.setOnClickListener {
                    listener.onItemClick(bindingAdapterPosition)
                    Log.i("conferencia da posicao","conferindo  ${bindingAdapterPosition.toInt()}")

                }
        }
    }
}