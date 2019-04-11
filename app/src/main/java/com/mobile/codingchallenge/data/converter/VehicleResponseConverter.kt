package com.mobile.codingchallenge.data.converter

import com.mobile.codingchallenge.data.model.VehicleResponse
import com.mobile.codingchallenge.ui.start.VehicleUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleResponseConverter @Inject constructor() : Converter<VehicleResponse, VehicleUiModel> {

    private val SUFFIX_THUMBNAIL = "_2.jpg"
    private val SUFFIX_POSTER_IMAGE = "_27.jpg"

    override fun convert(input: VehicleResponse): VehicleUiModel {

        val thumbNailList = mutableListOf<String>()
        val posterImageList = mutableListOf<String>()


        input.imageList.forEach { item ->

            thumbNailList.add("$item$SUFFIX_THUMBNAIL")
            posterImageList.add("$item$SUFFIX_POSTER_IMAGE")

        }

        return VehicleUiModel(thumbNailList, posterImageList)
    }
}