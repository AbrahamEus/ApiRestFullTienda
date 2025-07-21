package com.mx.ApiRestTienda.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth -> auth
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
						.requestMatchers(HttpMethod.POST,"/login").permitAll()
						.requestMatchers("/ApiRestTienda/mostrarD/**",
										 "/ApiRestTienda/editarEstatus/**",
											"/ApiRestTienda/guardar/**",
								"/ApiRestTienda/guardarD/**")
						.hasRole("ADMIN")
	                .anyRequest().authenticated()
	            )
	            .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	    @Bean
	    public UserDetailsService userDetailsService() {
	        return new InMemoryUserDetailsManager(
	            User.withUsername("admin")
	                .password("{noop}123")
	                .roles("ADMIN")
	                .build()
	        );
	    }
}
