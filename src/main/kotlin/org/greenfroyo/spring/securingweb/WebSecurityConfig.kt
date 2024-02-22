package org.greenfroyo.spring.securingweb

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { requests ->
            requests.requestMatchers("/", "/home")
                .permitAll()
                .anyRequest()
                .authenticated()
        }.formLogin { form ->
            form
                .loginPage("/login")
                .permitAll()
        }.logout { logout ->
            logout
                .permitAll()
        }
        return http.build()
    }

    @Bean
    fun userDetailService(): UserDetailsService{
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}