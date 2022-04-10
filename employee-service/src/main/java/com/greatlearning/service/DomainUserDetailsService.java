package com.greatlearning.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.model.DomainUserDetails;
import com.greatlearning.model.User;
import com.greatlearning.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User validUser=this.userRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
		return new DomainUserDetails(validUser);
	}

}
