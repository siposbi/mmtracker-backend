package com.sipos.mmtrackerbackend.dto

interface Converter<T, T2>{
    fun convertToDTO(source: T): T2
    fun convertFromDTO(source: T2): T
}