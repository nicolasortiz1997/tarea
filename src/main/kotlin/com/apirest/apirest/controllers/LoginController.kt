package com.apirest.apirest.controllers

import com.apirest.apirest.models.User
import com.apirest.apirest.security.JwtGenerator
import com.apirest.apirest.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/login")
class LoginController(
        /**
         * The JPA repository
         */
        private val userService: UserService) {

    @GetMapping
    fun login(): String {
        return "Login Start"
    }


    /**
     * Used to login a User in the DB
     *
     * @param user refers to the User needs to be saved
     * @return the [User] created
     */
    @PostMapping
    fun login(@RequestBody user: User): User {
        val u = userService.getUserByEmailAndPassword(user.email!!, user.password!!) ?: return noFound()
        u.jwt = JwtGenerator.generate(user)
        println(u.username)
        return u
    }


    fun noFound(): User {
        return User(0, "noFound", "noFound", "noFound", "noFound", "noFound")
    }
}
