package com.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
//    @Bean
//    UserDetailsService userDetailsService(PasswordEncoder encoder) {
//        UserDetails normalUser = User.withUsername("Rahul")
//        		.password(passwordEncoder().encode("1234"))
//        		.roles("USER")
//        		.build();
//        UserDetails adminUser = User.withUsername("Haidar")
//        		.password(passwordEncoder().encode("1234"))
//        		.roles("ADMIN")
//        		.build();
//        return new InMemoryUserDetailsManager(normalUser,adminUser);	
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http.authorizeHttpRequests()
    	.requestMatchers("/admin/**").hasRole("ADMIN")
    	.and()
    	.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
    	.and()
    	.authorizeHttpRequests().requestMatchers("/**").permitAll()
//    	.and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
//    	.defaultSuccessUrl("/user/")
    	.and().csrf().disable()
    	.build();
    	
    }
	@Bean
     PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
