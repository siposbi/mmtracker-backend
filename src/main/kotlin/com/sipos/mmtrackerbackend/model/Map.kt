package com.sipos.mmtrackerbackend.model

import javax.persistence.*

@Entity
class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    @OneToMany(mappedBy = "map")
    var games: Set<Game>? = null
}