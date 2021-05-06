package com.sipos.mmtrackerbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(unique = true)
    var username: String,
    var password: String,

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = [CascadeType.REMOVE])
    var games: List<Game> = emptyList()
)