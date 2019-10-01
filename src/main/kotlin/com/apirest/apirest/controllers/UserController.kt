package com.apirest.apirest.controllers

import com.apirest.apirest.models.User
import com.apirest.apirest.security.JwtGenerator
import com.apirest.apirest.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/users")
class UserController(
        /**
         * The JPA repository
         */
        private val userService: UserService) {


    /**
     * Used to fetch all the users from DB
     *
     * @return list of [User]
     */
    @GetMapping
    fun findAll(): List<User> {
        return userService.allUsers
    }

    /**
     * Used to find and return a user by id
     *
     * @param id refers to the name of the user
     * @return [User] object
     */
    @GetMapping(value = "/byId/{id}")
    fun findById(@PathVariable(value = "id") id: Long): User {
        println("llega al end point a obtener usuario $id")
        return userService.getUserById(id)
    }

    /**
     * Used to find and return a user by name
     *
     * @param username refers to the name of the user
     * @return [User] object
     */
    @GetMapping(value = "/byName/{username}")
    fun findByName(@PathVariable username: String): User {
        return userService.getUserByUsername(username)
    }

    /**
     * Used to delete a user by id
     *
     * @param id refers to the name of the user
     * @return [User] object
     */
    @DeleteMapping(value = "/delete/{id}")
    fun deleteById(@PathVariable id: Long) {
        userService.delete(id)
    }


    /**
     * Used to create a User in the DB
     *
     * @param users refers to the User needs to be saved
     * @return the [User] created
     */
    @PostMapping(value = "/save")
    fun saveUser(@RequestBody users: User): User {
        userService.saveOrUpdate(users)
        return userService.getUserByUsername(users.username!!)
    }

    /**
     * Used to login a User in the DB
     *
     * @param users refers to the User needs to be saved
     * @return the [User] created
     */

    @PostMapping(value = "/login")
    fun login(@RequestBody user: User): User {
        val u = userService.getUserByEmailAndPassword(user.email!!, user.password!!) ?: return noFound()
        u.jwt = JwtGenerator.generate(user)
        return u
    }


    fun noFound(): User {
        return User(0, "noFound", "noFound", "noFound", "noFound", "noFound")
    }

}