package com.itacademy.jd2.vvm.parking.web.security;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.service.util.SaltedMD5Example;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private IUserAccountService userAccountService;

	@Autowired
	public CustomAuthenticationProvider(IUserAccountService userAccountService) {
		super();
		this.userAccountService = userAccountService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String username = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		String hash = null;

		try {
			hash = SaltedMD5Example.main(password);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final IUserAccount entity = userAccountService.findByLogin(username);

		if (entity == null) {
			throw new BadCredentialsException("1000");
		}

		// TODO verify password (DB contains hash - not a plain password)
		if (!entity.getPassword().equals(hash)) {
			throw new BadCredentialsException("1000");
		}

		final int userId = entity.getId();

		List<String> userRoles = new ArrayList<>();
		userRoles.add("ROLE_" + entity.getRole().name());

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
		return new ExtendedUsernamePasswordAuthenticationToken(userId, username, password, authorities);

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
