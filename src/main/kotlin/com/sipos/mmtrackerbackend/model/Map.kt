package com.sipos.mmtrackerbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
class Map {
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "map", fetch = FetchType.LAZY)
    var games: Set<Game>? = null
}