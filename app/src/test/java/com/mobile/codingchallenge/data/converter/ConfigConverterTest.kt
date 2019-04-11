package com.mobile.codingchallenge.data.converter

import com.mobile.codingchallenge.config.AppRunTimeConfig
import com.mobile.codingchallenge.data.model.ConfigResponse
import com.mobile.codingchallenge.data.model.Images
import com.mobile.codingchallenge.di.DaggerTestComponent
import com.mobile.codingchallenge.di.TestComponent
import com.mobile.codingchallenge.di.TestModule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.lang.Exception
import javax.inject.Inject


class ConfigConverterTest {

    @Inject
    lateinit var appRunTimeConfig: AppRunTimeConfig

    lateinit var configConverter: ConfigConverter

    private val BASE_URL_IMAGES = "http://gapochi.de/"

    @Before
    fun setUp() {

        DaggerTestComponent.builder().testModule(TestModule()).build().inject(this)
        configConverter = ConfigConverter(appRunTimeConfig)
    }

    @Test
    fun testConvert_Should_ThrowException_When_InputDoNotContainImages() {

        val configResponse = Mockito.mock(ConfigResponse::class.java)

        Mockito.`when`(configResponse.images).thenReturn(Mockito.mock(Images::class.java))
        Mockito.`when`(configResponse.images.posterSizes).thenReturn(listOf("w200"))
        Mockito.`when`(configResponse.images.backdropSizes).thenReturn(listOf("w200"))

        try {
            configConverter.convert(configResponse)
        } catch (e: Exception) {
            assertEquals("Cannot Load configuration!", e.message)
        }
    }

    @Test
    fun testConvert_Should_ReturnBaseUrl_When_InputContainsMobileImage() {

        val configResponse = Mockito.mock(ConfigResponse::class.java)

        Mockito.`when`(configResponse.images).thenReturn(Mockito.mock(Images::class.java))
        Mockito.`when`(configResponse.images.posterSizes).thenReturn(listOf("w342", "w200", "original"))
        Mockito.`when`(configResponse.images.baseUrl).thenReturn(BASE_URL_IMAGES)

        configConverter.convert(configResponse)
        val expectedPosterUrl = "${BASE_URL_IMAGES}w342"
        assertEquals(expectedPosterUrl, appRunTimeConfig.basePosterUrl)
    }

    @Test
    fun testConvert_Should_ReturnBaseUrl_When_InputContainsOriginalImage() {

        val configResponse = Mockito.mock(ConfigResponse::class.java)

        Mockito.`when`(configResponse.images).thenReturn(Mockito.mock(Images::class.java))
        Mockito.`when`(configResponse.images.posterSizes).thenReturn(listOf("w200", "original"))
        Mockito.`when`(configResponse.images.baseUrl).thenReturn(BASE_URL_IMAGES)

        configConverter.convert(configResponse)
        val expectedPosterUrl = "${BASE_URL_IMAGES}original"
        assertEquals(expectedPosterUrl, appRunTimeConfig.basePosterUrl)
    }

    @Test
    fun testConvert_Should_ReturnFallbackUrl_When_InputMissingPosterImage() {

        val configResponse = Mockito.mock(ConfigResponse::class.java)

        Mockito.`when`(configResponse.images).thenReturn(Mockito.mock(Images::class.java))
        Mockito.`when`(configResponse.images.posterSizes).thenReturn(listOf("w200"))
        Mockito.`when`(configResponse.images.backdropSizes).thenReturn(listOf("w300","original"))
        Mockito.`when`(configResponse.images.baseUrl).thenReturn(BASE_URL_IMAGES)

        configConverter.convert(configResponse)
        val expectedPosterUrl = "${BASE_URL_IMAGES}w300"

        assertNull(appRunTimeConfig.basePosterUrl)
        assertEquals(expectedPosterUrl, appRunTimeConfig.baseFallbackUrl)
    }

}