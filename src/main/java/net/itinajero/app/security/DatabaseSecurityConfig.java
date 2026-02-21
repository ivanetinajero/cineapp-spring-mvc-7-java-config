package net.itinajero.app.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/", "/resources/**", "/search", "/about").permitAll()
				.requestMatchers("/peliculas/**", "/horarios/**", "/noticias/**").hasAnyAuthority("EDITOR", "GERENTE")
				.requestMatchers("/usuarios/**", "/banners/**").hasAnyAuthority("GERENTE")
				.anyRequest().authenticated()
			);

			// Configuracion del formulario de login personalizado
			http.formLogin(form -> form.loginPage("/formLogin").loginProcessingUrl("/ingresar").defaultSuccessUrl("/admin/index", true).permitAll());				
			
			// Configuracion de la URL personalizada para cerrar la sesión
			http.logout(logout -> logout.logoutUrl("/salir").permitAll());

		return http.build();
	}

    @Bean
	public UserDetailsManager users(DataSource dataSource) {
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);				
		users.setUsersByUsernameQuery("select cuenta, pwd, activo from Usuarios where cuenta = ?");
		users.setAuthoritiesByUsernameQuery("select cuenta, perfil from Perfiles where cuenta = ?");		
		return users;
	}

    /**
	 *  Implementacion de Spring Security que encripta passwords con el algoritmo Bcrypt
	 * @return
	 */	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}	

}
