package com.apirest.apirest.reposity

import com.apirest.apirest.models.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

//JpaRepository

@Component
interface UserjpaRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User
    fun findByEmailAndPassword(email: String, password: String): User
}
