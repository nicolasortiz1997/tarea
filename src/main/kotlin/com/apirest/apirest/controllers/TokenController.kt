package com.apirest.apirest.controllers


import com.apirest.apirest.models.User
import com.apirest.apirest.security.JwtGenerator
import com.apirest.apirest.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class TokenController(
        /**
         * The JPA repository
         */
        private val userService: UserService) {

    @PostMapping
    fun generate(@RequestBody jwtUser: User): User {

        val token = JwtGenerator.generate(jwtUser)
        jwtUser.jwt = "used to temporarily store jwt"
        userService.saveOrUpdate(jwtUser)
        return userService.getUserByUsername(jwtUser.username!!)

        // Guardar en la base de datos la información y el token del usuario
    }
}