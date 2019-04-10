package com.mobile.codingchallenge.data.converter

import android.util.Log
import com.mobile.codingchallenge.config.AppRunTimeConfig
import com.mobile.codingchallenge.data.model.MovieResponse
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import com.mobile.codingchallenge.ui.util.DateUtil
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This converts the movie response to ui model
 */

@Singleton
class MovieResponseConverter @Inject constructor(private val appRunTimeConfig: AppRunTimeConfig, private val dateUtil: DateUtil) :
    Converter<MovieResponse, List<MovieUiModel>> {

    override fun convert(input: MovieResponse): List<MovieUiModel> {

        val movieList = arrayListOf<MovieUiModel>()

        input.results.forEach { item ->

            var posterUrl: String = if (item.posterPath.isNullOrEmpty()) {
                "${appRunTimeConfig.baseFallbackUrl}${item.fallbackPosterPath}"
            } else {
                "${appRunTimeConfig.basePosterUrl}${item.posterPath}"
            }

            // In case poster and fallback both are missing than we don't execute the download request.
            if (item.posterPath.isNullOrEmpty() and item.fallbackPosterPath.isNullOrEmpty()) {
                Log.d("MovieResponseConverter", "Image is missing for movie ${item.title} and id is ${item.id}")
                posterUrl = ""
            }

            val movieUiModel = MovieUiModel(
                item.id,
                item.title,
                item.overview,
                posterUrl,
                item.voteAverage.toString(),
                getDateString(item.release_date)
            )
            movieList.add(movieUiModel)
        }

        return movieList
    }

    private fun getDateString(date: String): String {
        var date = dateUtil.fromDateFormat.parse(date)
        return dateUtil.toDateFormat.format(date)
    }
}