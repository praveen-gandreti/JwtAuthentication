package SpringSecurity.JWTTokenSecurity.Filter;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil{
	
	
	private long l=30000;
	public String generateToken(String username)
	{
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+l))
				.signWith(SignatureAlgorithm.HS512,"abcdefghi").compact();
				
	}

}
