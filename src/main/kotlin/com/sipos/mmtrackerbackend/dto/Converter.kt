package com.sipos.mmtrackerbackend.dto

interface Converter<T, T2, T3> {
    fun convertFromRequest(source: T2, id: Long): T
    fun convertToResponse(source: T): T3
}