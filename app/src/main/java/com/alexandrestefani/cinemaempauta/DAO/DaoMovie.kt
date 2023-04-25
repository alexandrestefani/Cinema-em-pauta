package com.alexandrestefani.cinemaempauta.DAO

import com.alexandrestefani.cinemaempauta.Model.Movies

class MoviesDao {

    fun addItem(produto:Movies){
        Companion.movieItem.add(produto)

    }

    fun getAll(): List<Movies> {
        return Companion.movieItem.toList()

    }


    companion object {
        private val movieItem: MutableList<Movies> = mutableListOf()
    }
}