package com.example.domain.mappers

interface Mapper<in I, out O> {
    fun map(input: I): O
}