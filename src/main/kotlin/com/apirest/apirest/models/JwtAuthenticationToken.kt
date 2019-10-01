package com.apirest.apirest.models


class JwtAuthenticationToken(var token: String?) : UsernamePasswordAuthenticationToken(null, null) {

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): Any? {
        return null
    }
}