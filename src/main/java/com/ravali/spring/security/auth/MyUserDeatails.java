package com.ravali.spring.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ravali.spring.security.entity.Role;
import com.ravali.spring.security.entity.User;

public class MyUserDeatails implements UserDetails{
	
	private User user;

	public MyUserDeatails(User user) {
		this.user=user;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
		List<Role> roles=user.getRoles();
		for(Role role:roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
