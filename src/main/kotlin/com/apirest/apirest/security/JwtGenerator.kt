package com.apirest.apirest.security


import com.apirest.apirest.models.User
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
object JwtGenerator {

    fun generate(jwtUser: User): String {

        val claims = Jwts.claims()
                .setSubject(jwtUser.username)
        claims["id"] = jwtUser.id.toString()
        claims["email"] = jwtUser.email

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "youtube")
                .compact()
    }
}