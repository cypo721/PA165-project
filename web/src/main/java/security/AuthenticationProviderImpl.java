package security;

import dto.UserDTO;
import facade.UserFacade;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	@Inject
	private UserFacade userFacade;

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String email = auth.getName();

		UserDTO user;

		try {
			user = userFacade.findByEmail(email);
		}
		catch(Exception e)
		{
			throw new UsernameNotFoundException("Provide valid email: " + email);
		}

		String password = (String) auth.getCredentials();

		if (!userFacade.authenticate(user, password)) {
			throw new BadCredentialsException("Provide valid email or password");
		}


		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole().toString());
		return new UsernamePasswordAuthenticationToken(email, password, authorities);
	}

	public boolean supports(Class<?> auth) {
		return true;
	}

}
