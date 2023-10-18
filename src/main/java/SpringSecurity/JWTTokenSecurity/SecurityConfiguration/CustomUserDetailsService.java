package SpringSecurity.JWTTokenSecurity.SecurityConfiguration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SpringSecurity.JWTTokenSecurity.Model.Users;
import SpringSecurity.JWTTokenSecurity.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository ur;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users u=ur.findByUsername(username);
		if(u==null)
		{
			System.out.println("*******************");
			throw new UsernameNotFoundException("USER IS NOT FOUND");
			
		}
		return new CustomUserDetails(u);
	}

}
