package com.foxlink.spc.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class USER_DATA implements UserDetails {
    private String password;
    private String username;
    private List<Role> roles;

	@Override
	public String getPassword() {return password;}

	@Override
	public String getUsername() {return username;}


	@Override
	public boolean isAccountNonExpired() {return false;}

	@Override
	public boolean isAccountNonLocked() {return false;}

	@Override
	public boolean isCredentialsNonExpired() {return false;}

	@Override
	public boolean isEnabled() {return true;}
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
