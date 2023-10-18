package SpringSecurity.JWTTokenSecurity.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import SpringSecurity.JWTTokenSecurity.Filter.JwtUtil;
import SpringSecurity.JWTTokenSecurity.Model.IncomingRequest;
import SpringSecurity.JWTTokenSecurity.Model.Users;
import SpringSecurity.JWTTokenSecurity.Repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository ur;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@GetMapping("/users")
	public List<Users> getAllUsers()
	{
		return ur.findAll();
	}
	@GetMapping("user/id/{id}")
	public Users getUserById(@PathVariable("id") int id)
	{
		System.out.println("*****************");
		System.out.println("*****************");
		return ur.findById(id).orElse(null);
	}
	
	@GetMapping("/user/username/{username}")
	public Users getUserByUsername(@PathVariable("username") String username)
	{
		System.out.println("*****************");
		System.out.println("*****************");
		Users u=ur.findByUsername(username);
		if(u==null)
			return null;
		else
			return u; 
	}
	@PostMapping("/save")
	public Users saveUser(@RequestBody Users user)
	{
		String password=encoder.encode(user.getPassword());
		user.setPassword(password);
		return ur.save(user);
	}
	@GetMapping("/principal")
	public String admin()
	{
		return "PRINCIPAL COMING FOR ROUNDS";
	}
	@GetMapping("/teacher")
	public String teacher()
	{
		return "TEACHER IS TEACHING";
	}
	@GetMapping("/student")
	public String student()
	{
		return "STUDENT IS STUDYING";
	}
	@PostMapping("/login")
	public String login(@RequestBody IncomingRequest request)
	{
		
		Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		String t=jwtUtil.generateToken(request.getUsername());
		SecurityContextHolder.getContext().setAuthentication(auth);
		return t;
	}
	
}
