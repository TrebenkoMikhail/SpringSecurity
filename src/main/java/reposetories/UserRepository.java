package reposetories;


import entities.Users;

import java.util.Optional;

public interface UserRepository {


    Optional<Users> findByUsername(String username);
    String getPassword();
}
