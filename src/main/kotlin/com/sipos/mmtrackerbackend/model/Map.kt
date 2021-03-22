package com.sipos.mmtrackerbackend.model

import javax.persistence.*

@Entity
data class Map(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var fileName: String,
    var name: String
)