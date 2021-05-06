package com.sipos.mmtrackerbackend.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "maps")
data class Map(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(unique = true)
    var fileName: String,
    @Column(unique = true)
    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "map", cascade = [CascadeType.REMOVE])
    var games: List<Game> = emptyList()
)