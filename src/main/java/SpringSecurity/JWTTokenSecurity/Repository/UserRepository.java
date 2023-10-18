package SpringSecurity.JWTTokenSecurity.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringSecurity.JWTTokenSecurity.Model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{

	Users findByUsername(String username);

	
}
