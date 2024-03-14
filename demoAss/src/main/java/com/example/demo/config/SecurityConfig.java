package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.example.demo.config.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
	
//	private final JwtAuthenticationFilter jwtAuthFilter =new JwtAuthenticationFilter() ;
	@Autowired
	CustomUserDetailsService customUserDetailsService ;
	
	@Autowired
	JwtAuthenticationFilter jwtAuthFilter ;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf((csrf) -> csrf.disable()).cors(cors->cors.disable())
				.authorizeHttpRequests(
				(authorizeHttpRequests) -> authorizeHttpRequests
//				các đường dẫn k cần đặp nhập
				.requestMatchers( "/public/**","/home/**","/login","/authenticate", "/signup")
				.permitAll()
//				các đường dẫn k cần đặp nhập với role = user
				.requestMatchers("/user/**")
				.hasAnyRole("DOCTOR", "ADMIN")
				
				.requestMatchers("/admin/**")
				.hasRole("ADMIN")
				
				.requestMatchers("/doctor/**")
				.hasAnyRole("DOCTOR", "ADMIN")
				
//				.anyRequest()
//				.authenticated()
				)
				.sessionManagement(
						(session) -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

//				.formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
				;
		return http.build();

	}
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	  }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		new PlainTextPassEncoder();
		return PlainTextPassEncoder.getInstance();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		System.out.println(authenticationProvider.getUserCache().toString());
		System.out.println(passwordEncoder().toString());
		return authenticationProvider;
	}
}
