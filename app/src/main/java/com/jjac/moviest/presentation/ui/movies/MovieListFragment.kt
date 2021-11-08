package com.jjac.moviest.presentation.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.jjac.moviest.R
import com.jjac.moviest.presentation.BaseApplication
import com.jjac.moviest.presentation.components.MovieList
import com.jjac.moviest.ui.theme.AppTheme
import com.jjac.moviest.util.MOVIE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                var movies = viewModel.movies.value
                var loading = viewModel.loading.value

                val scaffoldState = rememberScaffoldState()
                AppTheme(
                    darkTheme = false,
                    displayProgressBar = false,
                    scaffoldState = scaffoldState
                ) {
                    MovieList(
                        loading = loading,
                        movies = movies,
                        visitedMovies = viewModel.visitedMovies.value,
                        onNavigateToMovieDetailScreen = { movie ->
                            viewModel.isExist(movie).observe(viewLifecycleOwner, Observer {
                                //Set Timestamp
                                movie.timeStamp = Calendar.getInstance().time
                                if (it) {
                                    viewModel.updateMovie(movie)
                                } else {
                                    viewModel.insertMovie(movie)
                                }
                                val bundle = Bundle()
                                bundle.putSerializable(MOVIE, movie)
                                findNavController().navigate(R.id.viewMovie, bundle)
                            })
                        })
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.sourceVisitedMovies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                viewModel.visitedMovies.value = it
            }
        })
    }
}