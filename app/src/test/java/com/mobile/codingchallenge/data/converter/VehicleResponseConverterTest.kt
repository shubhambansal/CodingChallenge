package com.mobile.codingchallenge.data.converter

import com.mobile.codingchallenge.data.model.Images
import com.mobile.codingchallenge.data.model.VehicleResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class VehicleResponseConverterTest {

    private lateinit var vehicleResponseConverter: VehicleResponseConverter
    private val FAKE_IMAGE_ATH = "image/path/name"

    @Before
    fun setUp() {
        vehicleResponseConverter = VehicleResponseConverter()
    }

    @Test
    fun testConvert_Should_ReturnEmptyList_When_ProductImagesAreMissing() {

        var response = Mockito.mock(VehicleResponse::class.java)
        Mockito.`when`(response.imageList).thenReturn(listOf())

        var vehicleUiModel = vehicleResponseConverter.convert(response)
        assertEquals(0, vehicleUiModel.thumbnailList.size)
        assertEquals(0, vehicleUiModel.fullScaleImage.size)
    }

    @Test
    fun testConvert_Should_ReturnImageList_When_ProductImagesArePresent() {

        var response = Mockito.mock(VehicleResponse::class.java)
        var image = Mockito.mock(Images::class.java)

        Mockito.`when`(response.imageList).thenReturn(listOf(image))
        Mockito.`when`(image.uri).thenReturn(FAKE_IMAGE_ATH)


        var vehicleUiModel = vehicleResponseConverter.convert(response)
        assertEquals(1, vehicleUiModel.thumbnailList.size)
        assertEquals(1, vehicleUiModel.fullScaleImage.size)


        val urlSmall = "${VehicleResponseConverter.SCHEME}$FAKE_IMAGE_ATH${VehicleResponseConverter.SUFFIX_THUMBNAIL}"
        val urlLarge =
            "${VehicleResponseConverter.SCHEME}$FAKE_IMAGE_ATH${VehicleResponseConverter.SUFFIX_POSTER_IMAGE}"

        assertEquals(urlSmall, vehicleUiModel.thumbnailList.first())
        assertEquals(urlLarge, vehicleUiModel.fullScaleImage.first())
    }
}