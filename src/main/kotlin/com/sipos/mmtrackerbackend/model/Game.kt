package com.sipos.mmtrackerbackend.model

import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
class Game {
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var roundsWon: Int? = null
    var roundsLost: Int? = null
    var kills: Int? = null
    var assists: Int? = null
    var deaths: Int? = null

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "map_id")
    var map: Map? = null
}
