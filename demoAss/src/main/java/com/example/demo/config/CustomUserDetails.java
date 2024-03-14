package com.example.demo.config;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails  implements UserDetails {

		private String name;
		private String password;
		private List<GrantedAuthority> authorities =new ArrayList<>() ;
		public CustomUserDetails(User user) {
			name = user.getEmail();
			password = user.getPassword();
			System.out.println(name + "day la  "+ password );
			System.out.println(user.toString() );
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.geteRole().name());
			authorities.add(simpleGrantedAuthority);
			System.out.println(authorities.get(0)+"--");
		}
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return name;
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
