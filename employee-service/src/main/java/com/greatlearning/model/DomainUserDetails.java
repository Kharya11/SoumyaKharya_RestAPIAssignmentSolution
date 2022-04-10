package com.greatlearning.model;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DomainUserDetails implements UserDetails {
	
	private final User user;
	
	public DomainUserDetails(User user) {
		this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		/*
		 * Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); Set<Role> roles =
		 * user.getRoles(); for (Role role : roles) {
		 * System.out.println(role.getRoleName()); grantedAuthorities.add(new
		 * SimpleGrantedAuthority("ROLE_" + role.getRoleName())); 
		 */
		
		  Set<Role> roles= user.getRoles(); Set<SimpleGrantedAuthority> authorities=
		  new HashSet<>(); 
		  roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName() ) ) ); 
		   return authorities;
	      
	      }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
