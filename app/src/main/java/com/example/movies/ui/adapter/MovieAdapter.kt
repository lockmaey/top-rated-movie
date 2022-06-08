package com.example.movies.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movies.R
import com.example.movies.database.entity.Movie


class MovieAdapter(private val context: Context, private var movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.load(movieList[position].poster_path)
        holder.title.text = movieList[position].title
        holder.releaseDate.text = movieList[position].release_date.take(4)
    }

    override fun getItemCount() = movieList.size

    fun updateData(movieListNew: List<Movie>) {
        /*if (text!!.isEmpty() || text.isBlank()) {
            //reset data
            initializeData(context = context)
        } else {
            movieList = initializeData(context = context)
            movieList?.filter { movie ->
                movie.title.contains(text, true)
            } as MutableList<Movie>
        }*/

        val diffCallback =
            MovieDiffCallback(oldMovies = this.movieList, newMovies = movieListNew)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.movieList.clear();
         this.movieList.addAll(movieListNew);
        diffResult.dispatchUpdatesTo(this);
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.thumbnails)
        val title: TextView = itemView.findViewById(R.id.title)
        val releaseDate: TextView = itemView.findViewById(R.id.date_release)
    }
}