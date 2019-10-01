package com.apirest.apirest.security

import com.apirest.apirest.models.User
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class JwtValidator {

    internal fun validate(token: String): User? {

        var jwtUser: User? = null
        try {
            val secret = "youtube"
            val body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .body

            jwtUser = User()

            jwtUser.username = body.subject
            jwtUser.id = java.lang.Long.parseLong(body["id"] as String)
            jwtUser.email = body["email"] as String
        } catch (e: Exception) {
            println(e.message)
        }

        return jwtUser
    }
}