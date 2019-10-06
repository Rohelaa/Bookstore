package hh.swd20.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.swd20.Bookstore.domain.User;
import hh.swd20.Bookstore.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// etsitään repositorysta käyttäjää 
		// etsiminen tapahtuu metodille annetun parametrin mukaan
		// findByUserName-metodi määriteltiin userRepossa, jotta voidaan hakea haluttua käyttäjää
		
		User currUser = userRepository.findByUsername(username);
		
		// ????
		// Luodaan uusi UserDetails olio
		// oliolle annetaan parametrina kolme arvoa: username, password, role
		
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPassword(), AuthorityUtils.createAuthorityList(currUser.getRole()));
		
		return user;
		
	}
}
