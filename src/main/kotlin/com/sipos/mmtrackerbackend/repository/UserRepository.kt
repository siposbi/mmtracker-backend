package com.sipos.mmtrackerbackend.repository

import com.sipos.mmtrackerbackend.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByUsernameAndPassword(username: String, password: String): Boolean
    fun existsByUsername(username: String): Boolean
}