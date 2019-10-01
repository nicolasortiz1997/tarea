package com.apirest.apirest.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape
import com.fasterxml.jackson.annotation.JsonIgnore

import io.swagger.annotations.ApiModelProperty

@Entity
@Table(name = "connections")
class ConnectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long = 0

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @ApiModelProperty(notes = "connection date of the User")
    @Column(name = "date_connection")
    lateinit var date_connection: String


    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm:ss")
    @ApiModelProperty(notes = "connection time of the User")
    @Column(name = "time_connection")
    lateinit var time_connection: String

    @ApiModelProperty(notes = "id of the User")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    var user: User? = null


}
