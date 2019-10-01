package com.apirest.apirest.service

import com.apirest.apirest.models.User
import com.apirest.apirest.reposity.UserjpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.DriverManager.println

import java.util.ArrayList
import java.util.function.Consumer

@Service
class UserService(private val userRepository: UserjpaRepository) {

    val allUsers: List<User>
        get() {
            val users = ArrayList<User>()
            userRepository.findAll().forEach(Consumer<User> { users.add(it) })
            return users
        }

    fun getUserById(id: Long): User {
        println("llega al servicio a obtener usuario $id")
        return userRepository.findById(id).get()
    }

    fun getUserByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }

    fun saveOrUpdate(user: User) {
        userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserByEmailAndPassword(username: String, password: String): User {
        return userRepository.findByEmailAndPassword(username, password)
    }
}
