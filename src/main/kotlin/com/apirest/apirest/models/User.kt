package com.apirest.apirest.models


import javax.persistence.*

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "username")
    var username: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "full_name")
    var full_name: String? = null

    @Column(name = "jwt", columnDefinition = "varchar(500) default 'used to temporarily store jwt'")
    var jwt: String? = null

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Location> locations;*/


    constructor() {}

    constructor(id: Long, username: String, email: String, password: String, full_name: String, jwt: String) {

        this.id = id
        this.username = username
        this.email = email
        this.password = password
        this.full_name = full_name
        this.jwt = jwt
    }

    /*public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }*/
}
