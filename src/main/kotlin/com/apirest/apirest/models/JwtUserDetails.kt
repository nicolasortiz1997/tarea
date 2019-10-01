package com.apirest.apirest.models



class JwtUserDetails// User details nuevamente ya probando pagando minutos
(val userName: String, val id: Long?, val token: String, grantedAuthorities: List<GrantedAuthority>) : UserDetails {
    private val authorities: Collection<GrantedAuthority>

    init {
        this.authorities = grantedAuthorities
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
