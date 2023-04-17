package SOF003AS3A3002.lopputyo.domain;

import org.springframework.data.repository.CrudRepository;

import SOF003AS3A3002.lopputyo.domain.User;

public interface UserRepositery extends CrudRepository<User, Long> {
	User findByUsername(String username);  //Springin tekemää  sisäänkirjatumistestiä varten
}
