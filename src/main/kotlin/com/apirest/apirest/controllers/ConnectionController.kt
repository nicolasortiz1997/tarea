package com.apirest.apirest.controllers

import com.apirest.apirest.models.ConnectionModel
import com.apirest.apirest.service.ConnectionService
import com.apirest.apirest.service.UserService
import javax.validation.Valid

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
import io.swagger.annotations.Api
 */
@RestController
@RequestMapping("/rest/connections")
class ConnectionController(private val connectionService: ConnectionService,
                           /**
                            * The JPA repository
                            */
                           private val userService: UserService) {

    /**
     * Used to fetch all the connections from DB
     *
     * @return list of [connection]
     */
    @GetMapping
    fun findAll(): List<ConnectionModel> {
        return connectionService.allconnections
    }

    /**
     * Used to find and return a connection by id
     *
     * @param id refers to the name of the connection
     * @return [connection] object
     */
    @GetMapping(value = "/{id}")
    fun findById(@PathVariable id: Long): ConnectionModel {
        return connectionService.getconnectionById(id)
    }


    /**
     * Used to delete a connection by id
     *
     * @param id refers to the name of the connection
     * @return [connection] object
     */
    @DeleteMapping(value = "/{id}")
    fun deleteById(@PathVariable id: Long) {
        connectionService.delete(id)
    }


    /**
     * Used to create a connection in the DB
     *
     * @param user_id   refers to the connection needs to be saved
     * @param connections refers to the connection needs to be saved
     * @return the [connection] created
     */
    @PostMapping(value = "/{user_id}")
    fun saveconnection(@PathVariable(value = "user_id") user_id: Long?,
                       @Valid @RequestBody connections: ConnectionModel): ConnectionModel {

        val currentUser = userService.getUserById(user_id!!)
        connections.user = currentUser
        connectionService.saveOrUpdate(connections)
        return connectionService.getconnectionById(connections.id)
    }

}
