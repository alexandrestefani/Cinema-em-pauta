package com.alexandrestefani.cinemaempauta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.cinemaempauta.Adapter.AdapterPosters
import com.alexandrestefani.cinemaempauta.DAO.MoviesDao
import com.alexandrestefani.cinemaempauta.Model.Movies
import com.alexandrestefani.cinemaempauta.Model.Posters

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Details : Fragment() {

    private val args by navArgs<DetailsArgs>()
    private  val dao = MoviesDao()
    lateinit var imageDetails: ImageView
    lateinit var titleDetails: TextView
    lateinit var textDetails: TextView
    lateinit var avaliationDetails: TextView
    var carousel: MutableList<Posters> = mutableListOf()
    lateinit var recyclerViewCarousel: RecyclerView
    var adapter = AdapterPosters(carousel)
    lateinit var buttonGoBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        buttonGoBack = view.findViewById(R.id.button_go_back)
        buttonGoBack.setOnClickListener {
            findNavController().navigate(DetailsDirections.actionDetailsToMovieList())
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var position_movie = args.position
        var position: Int = position_movie.toInt()

        //Fit the view components combined item selected:
        var movieSelected = fitDetailsView(view, position)

        //Put datas into posters carousel
        putDatasCarousel(movieSelected)

        //Configurate Carrousel of posters
        adapterCarouselConfig(view)
    }

    private fun adapterCarouselConfig(view: View) {
        recyclerViewCarousel = view.findViewById(R.id.recycler_carrocel_posters)
        recyclerViewCarousel.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCarousel.adapter = adapter
    }

    private fun fitDetailsView(
        view: View,
        position: Int
    ): Movies {
        titleDetails = view.findViewById(R.id.title_details)
        textDetails = view.findViewById(R.id.details_text)
        avaliationDetails = view.findViewById(R.id.avaliation_details)
        imageDetails = view.findViewById(R.id.image_details)

        var movieSelected = dao.getAll()[position]
        var nameMovieSelected = movieSelected.name
        var pictureMovieSelected = movieSelected.picture
        var synopsisMovieSelected = movieSelected.synopsis
        var avaliationMovieSelected = movieSelected.avaliation

        textDetails.text = synopsisMovieSelected.toString()
        titleDetails.text = nameMovieSelected.toString()
        imageDetails.setImageResource(pictureMovieSelected)
        avaliationDetails.text = avaliationMovieSelected
        return movieSelected
    }

    private fun putDatasCarousel(movieSelected: Movies) {
        var imageCarousel01 = movieSelected.poster01
        var imageCarousel02 = movieSelected.poster02
        var imageCarousel03 = movieSelected.poster03

        carousel.add(Posters(imageCarousel01))
        carousel.add(Posters(imageCarousel02))
        carousel.add(Posters(imageCarousel03))
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Details().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}