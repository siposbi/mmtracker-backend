package com.sipos.mmtrackerbackend.model

import javax.persistence.*

@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var username: String? = null
    var password: String? = null
    @OneToMany(mappedBy = "user")
    var games: Set<Game>? = null
}