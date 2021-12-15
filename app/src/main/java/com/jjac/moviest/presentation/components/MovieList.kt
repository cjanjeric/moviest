package com.jjac.moviest.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jjac.store.domain.model.Movie

@Composable
fun MovieList(
    loading: Boolean,
    movies: List<Movie>,
    visitedMovies: List<Movie>,
    onNavigateToMovieDetailScreen: (Movie) -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && movies.isEmpty()) {
            CircularIndeterminateProgressBar(isDisplayed = true, verticalBias = 0.3f)
        } else if (movies.isEmpty()) {
            NothingHere()
        } else {
            Column(
                modifier = if (visitedMovies.isEmpty()) Modifier.padding(
                    0.dp,
                    10.dp,
                    0.dp,
                    0.dp
                )
                else Modifier.padding(0.dp, 180.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = "Watch Movies",
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h3
                )
                LazyColumn {
                    itemsIndexed(
                        items = movies
                    ) { index, movie ->
                        MovieCard(
                            movie = movie,
                            onClick = {
                                onNavigateToMovieDetailScreen(movie)
                            }
                        )
                    }
                }
            }
        }

        if (visitedMovies.isNotEmpty()) {
            Column {
                Text(
                    text = "Visited Movies",
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h3
                )
                LazyRow(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
                    itemsIndexed(
                        items = visitedMovies
                    ) { index, movie ->
                        MovieCard(
                            movie = movie,
                            onClick = {
                                onNavigateToMovieDetailScreen(movie)
                            }
                        )
                    }
                }
            }
        }
    }
}