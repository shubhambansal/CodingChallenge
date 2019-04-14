package com.mobile.codingchallenge.data.converter

import androidx.annotation.VisibleForTesting
import com.mobile.codingchallenge.data.model.VehicleResponse
import com.mobile.codingchallenge.ui.start.VehicleUiModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VehicleResponseConverter @Inject constructor() : Converter<VehicleResponse, VehicleUiModel> {


    override fun convert(input: VehicleResponse): VehicleUiModel {

        val thumbNailList = mutableListOf<String>()
        val posterImageList = mutableListOf<String>()


        input.imageList.forEach { item ->

            thumbNailList.add("$SCHEME${item.uri}$SUFFIX_THUMBNAIL")
            posterImageList.add("$SCHEME${item.uri}$SUFFIX_POSTER_IMAGE")

        }

        return VehicleUiModel(thumbNailList, posterImageList)
    }

    companion object {
        const val SUFFIX_THUMBNAIL = "_2.jpg"
        const val SUFFIX_POSTER_IMAGE = "_27.jpg"
        const val SCHEME = "http://"

    }
}