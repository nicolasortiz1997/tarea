package com.apirest.apirest.config



import com.apirest.apirest.security.JwtAuthenticationEntryPoint
import com.apirest.apirest.security.JwtAuthenticationProvider
import com.apirest.apirest.security.JwtSuccessHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.lang.Exception

import java.util.Collections

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
class JwtSecurityConfig(private val authenticationProvider: JwtAuthenticationProvider, private val entryPoint: JwtAuthenticationEntryPoint) : WebSecurityConfigurerAdapter() {

    @Bean
    public override fun authenticationManager(): AuthenticationManager {
        return ProviderManager(listOf<AuthenticationProvider>(authenticationProvider))
    }

    @Bean
    fun authenticationTokenFilter(): JwtAuthenticationTokenFilter {
        val filter = JwtAuthenticationTokenFilter()
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(JwtSuccessHandler())
        return filter
    }


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        // testing deploy to app engine

        http.headers().frameOptions().disable()
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/greeting").permitAll()
                .antMatchers("/rest/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
        http.headers().cacheControl()

    }
}
