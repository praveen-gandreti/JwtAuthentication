package SpringSecurity.JWTTokenSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import SpringSecurity.JWTTokenSecurity.Model.Users;
import SpringSecurity.JWTTokenSecurity.Repository.UserRepository;

@SpringBootApplication
public class JwtTokenSecurityApplication implements CommandLineRunner	{

	@Autowired
	UserRepository ur;
	@Autowired
	BCryptPasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(JwtTokenSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String password=encoder.encode("aa");
		ur.save(new Users("praveen",password,"principal"));
		ur.save(new Users("ajay",password,"teacher"));
		ur.save(new Users("surya",password,"student"));
	}

}
