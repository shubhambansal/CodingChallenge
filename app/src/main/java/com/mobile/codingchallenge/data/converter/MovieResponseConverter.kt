package com.mobile.codingchallenge.data.converter

import com.mobile.codingchallenge.config.AppRunTimeConfig
import com.mobile.codingchallenge.data.model.MovieResponse
import com.mobile.codingchallenge.ui.movies.MovieUiModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This converts the movie response to ui model
 */

@Singleton
class MovieResponseConverter @Inject constructor(private val appRunTimeConfig: AppRunTimeConfig) :
    Converter<MovieResponse, List<MovieUiModel>> {
    override fun convert(input: MovieResponse): List<MovieUiModel> {

        val movieList = arrayListOf<MovieUiModel>()

        input.results.forEach { item ->

            var posterUrl: String
            if (!appRunTimeConfig.basePosterUrl.isNullOrEmpty()) {
                posterUrl = "${appRunTimeConfig.basePosterUrl}${item.posterPath}"
            } else {
                posterUrl = "${appRunTimeConfig.baseFallbackUrl}${item.fallbackPosterPath}"
            }

            val movieUiModel = MovieUiModel(item.id, item.title, item.overview, posterUrl)
            movieList.add(movieUiModel)
        }

        return movieList
    }
}