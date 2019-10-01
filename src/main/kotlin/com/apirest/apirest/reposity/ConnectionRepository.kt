package com.apirest.apirest.reposity

import com.apirest.apirest.models.ConnectionModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component


@Component
interface ConnectionRepository : CrudRepository<ConnectionModel, Long>
