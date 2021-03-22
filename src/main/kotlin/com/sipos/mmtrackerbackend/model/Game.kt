package com.sipos.mmtrackerbackend.model

import javax.persistence.*

@Entity
data class Game(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var roundsWon: Int,
    var roundsLost: Int,
    var kills: Int,
    var assists: Int,
    var deaths: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "map_id")
    var map: Map
)