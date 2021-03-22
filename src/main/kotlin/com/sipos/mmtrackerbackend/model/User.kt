package com.sipos.mmtrackerbackend.model

import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String,
    var password: String,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    var games: Set<Game>
)