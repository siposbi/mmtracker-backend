package com.sipos.mmtrackerbackend.dto

interface ResponseConverter<T, T2> {
    fun convertToResponse(source: T): T2
}