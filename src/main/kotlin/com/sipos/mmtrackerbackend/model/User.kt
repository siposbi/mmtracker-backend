package com.sipos.mmtrackerbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*

@Entity
class User {
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var username: String? = null
    var password: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    var games: Set<Game>? = null
}