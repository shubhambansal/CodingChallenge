package com.mobile.codingchallenge.di

import com.mobile.codingchallenge.data.converter.ConfigConverterTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent {

    fun inject(configConverterTest: ConfigConverterTest)
}