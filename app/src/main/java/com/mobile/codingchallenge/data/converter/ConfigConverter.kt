package com.mobile.codingchallenge.data.converter

import com.mobile.codingchallenge.config.AppRunTimeConfig
import com.mobile.codingchallenge.data.model.ConfigResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This converts the config response and set the important properties to AppRunTimeConfig
 */

private const val SIZE_ORIGINAL = "original"
private const val SIZE_300_W = "w300"
private const val SIZE_342_W = "w342"

@Singleton
class ConfigConverter @Inject constructor(private val appRunTimeConfig: AppRunTimeConfig) :
    Converter<ConfigResponse, Unit> {

    override fun convert(input: ConfigResponse) {

        //First we need to look for w300 image, which is good for apps
        var fallbackSize = input.images.backdropSizes.find { it == SIZE_300_W }

        // looking for original image
        if (fallbackSize.isNullOrEmpty()) {
            fallbackSize = input.images.backdropSizes.find { it == SIZE_ORIGINAL }
        }

        if (fallbackSize.isNullOrEmpty().not()) {
            appRunTimeConfig.baseFallbackUrl = " $input.images.secureBaseUrl$fallbackSize"
        }


        var posterSize = input.images.posterSizes.find { it == SIZE_342_W }
        // looking for original image
        if (posterSize.isNullOrEmpty()) {
            posterSize = input.images.posterSizes.find { it == SIZE_ORIGINAL }
        }
        if (posterSize.isNullOrEmpty().not()) {
            appRunTimeConfig.basePosterUrl = " $input.images.secureBaseUrl$posterSize"
        }

        if (isRuntimeConfigValid()) {
            throw Exception("Cannot Load configuration!")
        }
    }


    /**
     * @return true if not valid, else false
     */
    private fun isRuntimeConfigValid(): Boolean {
        return appRunTimeConfig.basePosterUrl.isNullOrEmpty() and appRunTimeConfig.baseFallbackUrl.isNullOrEmpty()
    }

}