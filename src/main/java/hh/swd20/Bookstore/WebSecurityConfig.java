package hh.swd20.Bookstore;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		// Luodaan kaksi käyttäjää, user ja admin
		
		Collection<UserDetails> users = new ArrayList<UserDetails>();
		
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		
		users.add(user);
		users.add(admin);
		
		return new InMemoryUserDetailsManager(users); 
	}
}
