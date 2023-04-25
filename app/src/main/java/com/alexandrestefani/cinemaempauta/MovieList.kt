package com.alexandrestefani.cinemaempauta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.cinemaempauta.Adapter.AdapterMovies
import com.alexandrestefani.cinemaempauta.DAO.MoviesDao
import com.alexandrestefani.cinemaempauta.Model.Movies

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MovieList : Fragment() {
    private val dao= MoviesDao()

    private  var movieList: MutableList<Movies> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        criateDatas()
        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_fragment)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val itemadapter = AdapterMovies(dao.getAll())
        recyclerView.adapter = itemadapter

        itemadapter.onItemClickListener(object: AdapterMovies.onItemClickListener {
            override fun onItemClick(pos: Int) {
                val movie = dao.getAll()[pos]
                var movieName = movie.name.toString()
                var positionItem = pos.toString()


                val direction = MovieListDirections.actionMovieListToDetails2(positionItem)
                direction.position = positionItem
                findNavController().navigate(direction)


            }
        })

    }



    private fun criateDatas(){

        var movie1 = Movies(
            getString(R.string.movie_name_01),
            getString(R.string.movie_description_01),
            R.drawable.aventureirosdobairroproibido,
            getString(R.string.movie_synopsis_01),
            R.drawable.poster_movie_1_1,
            R.drawable.poster_movie_1_2,
            R.drawable.poster_movie_1_3,
            getString(R.string.movie_avaliation_01)
        )
        dao.addItem(movie1)

        var movie2 = Movies(
            getString(R.string.movie_name_02),
            getString(R.string.movie_description_02),
            R.drawable.madmaxestradadafuria,
            getString(R.string.movie_synopsis_02),
            R.drawable.poster_movie_2_1,
            R.drawable.poster_movie_2_2,
            R.drawable.poster_movie_2_3,
            getString(R.string.movie_avaliation_02)
        )
        dao.addItem(movie2)

        var movie3 = Movies(
            getString(R.string.movie_name_03),
            getString(R.string.movie_description_03),
            R.drawable.o_parasita_n2,
            getString(R.string.movie_synopsis_03),
            R.drawable.poster_movie_3_1,
            R.drawable.poster_movie_3_2,
            R.drawable.poster_movie_3_3,
            getString(R.string.movie_avaliation_03)
        )
        dao.addItem(movie3)

        var movie4 = Movies(
            getString(R.string.movie_name_04),
            getString(R.string.movie_description_04),
            R.drawable.sol_todos,
            getString(R.string.movie_synopsis_04),
            R.drawable.poster_movie_4_1,
            R.drawable.poster_movie_4_2,
            R.drawable.poster_movie_4_3,
            getString(R.string.movie_avaliation_04)
        )
        dao.addItem(movie4)

    }
}