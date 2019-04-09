package com.mobile.codingchallenge.data.converter

/**
 * Base interface to convert data from data layer to domain or ui layer
 */
interface Converter<Input, Return> {
    fun convert(input: Input): Return
}