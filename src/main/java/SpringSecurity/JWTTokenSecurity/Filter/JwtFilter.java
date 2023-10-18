package SpringSecurity.JWTTokenSecurity.Filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import SpringSecurity.JWTTokenSecurity.SecurityConfiguration.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("1000");
		try
		{
			System.out.println("3000");
			String token=request.getHeader("Authorization");
			if(token==null)
			{
				System.out.println("NO TOKEN PRESENT----"+token);
			}
			System.out.println("TOKEN = "+token);
			System.out.println("2000");
			if(!token.startsWith("Bearer "))
			{
				System.out.println("TOKEN NOT START WITH BEARER");
			}
			System.out.println("4000");
			token= token.substring(7);
			System.out.println("---> "+token);
			Claims claims=Jwts.parser().setSigningKey("abcdefghi").parseClaimsJws(token).getBody();
			if(claims.getExpiration().before(new Date()))
			{
				System.out.println("Token Expired please login again");
			}
			UserDetails userDetails=userDetailsService.loadUserByUsername(claims.getSubject());
			if(userDetails==null)
			{
				System.out.println("NO USER FOUND IN DB WITH TOKEN SUBJECT");
			}
			Authentication auth=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		catch(Exception e)
		{
			System.out.println(e.getClass());
		}
		filterChain.doFilter(request, response);
		
	}
	
}