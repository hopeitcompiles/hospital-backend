package com.utpl.hospital.authentication;

import com.utpl.hospital.model.User;
import com.utpl.hospital.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService{

	@Autowired
	private IUserRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
        if (user != null) {
            return new ApplicationUser(
            		user.getEmail(),
            		user.getPassword(),user.getRole().getRole().getGrantedAuthorities(), true,true,true
					,true);


        } else {
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        }
		
	}

}
