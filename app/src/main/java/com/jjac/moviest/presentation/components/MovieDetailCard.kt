package com.jjac.moviest.presentation.components

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.jjac.moviest.util.DEFAULT_RECIPE_IMAGE
import com.jjac.moviest.util.loadPicture
import com.jjac.store.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MovieDetailCard(movie: Movie) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
        ) {

            //Play Media
            VideoPlayer(movie = movie)

            Column(
                modifier = Modifier
                    .padding(top = 0.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)
            ) {

                Text(
                    text = movie.trackName.toString(),
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold
                )

                Row() {
                    Text(
                        text = "Price: ",
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.caption
                    )

                    Text(
                        text = "$ ${movie.collectionPrice.toString()}",
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                }

                Row() {
                    Text(
                        text = "Genre: ",
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.caption
                    )

                    Text(
                        text = movie.primaryGenreName.toString(),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row() {
                    val image =
                        loadPicture(
                            url = movie.artworkUrl100.toString(),
                            defaultImage = DEFAULT_RECIPE_IMAGE
                        ).value
                    image?.let { img ->
                        Image(
                            bitmap = img.asImageBitmap(),
                            contentDescription = "Movie Featured Image",
                            modifier = Modifier
                                .width(70.dp)
                                .height(100.dp)
                                .padding(top = 12.dp, bottom = 12.dp, start = 0.dp, end = 8.dp),
                            contentScale = ContentScale.Crop,
                        )
                    }

                    Text(
                        text = movie.longDescription.toString(),
                        modifier = Modifier
                            .fillMaxWidth(.85f),
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}