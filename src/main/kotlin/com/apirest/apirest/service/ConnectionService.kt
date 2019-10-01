package com.apirest.apirest.service


import com.apirest.apirest.models.ConnectionModel
import com.apirest.apirest.reposity.ConnectionRepository
import java.util.ArrayList

import org.springframework.stereotype.Service

import java.util.function.Consumer

@Service
class ConnectionService(private val connectionRepository: ConnectionRepository) {

    val allconnections: List<ConnectionModel>
        get() {
            val connections = ArrayList<ConnectionModel>()
            connectionRepository.findAll().forEach(Consumer<ConnectionModel> { connections.add(it) })
            return connections
        }

    fun getconnectionById(id: Long): ConnectionModel {
        return connectionRepository.findById(id).get()
    }


    fun saveOrUpdate(connection: ConnectionModel) {
        connectionRepository.save(connection)
    }

    fun delete(id: Long) {
        connectionRepository.deleteById(id)
    }

}
