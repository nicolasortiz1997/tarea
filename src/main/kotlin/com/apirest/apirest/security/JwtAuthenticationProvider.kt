package com.apirest.apirest.security

import com.apirest.apirest.models.JwtAuthenticationToken
import com.apirest.apirest.models.JwtUserDetails
import javax.naming.AuthenticationException



import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class JwtAuthenticationProvider(private val validator: JwtValidator) : AbstractUserDetailsAuthenticationProvider() {

    @Throws(AuthenticationException::class)
    override fun additionalAuthenticationChecks(userDetails: UserDetails, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken) {
    }

    @Throws(AuthenticationException::class)
    override fun retrieveUser(username: String, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken): UserDetails {

        val jwtAuthenticationToken = usernamePasswordAuthenticationToken as JwtAuthenticationToken
        val token = jwtAuthenticationToken.token

        val jwtUser = validator.validate(token) ?: throw RuntimeException("JWT Token is incorrect")

        val grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.email)
        return JwtUserDetails(jwtUser.username!!, jwtUser.id,
                token!!,
                grantedAuthorities)
    }

    override fun supports(aClass: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java!!.isAssignableFrom(aClass)
    }
}
