package eu.solvemath.solvemath.services;


import eu.solvemath.solvemath.domain.User;
import eu.solvemath.solvemath.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Set<User> findAll(){
        Set<User> users = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    public void add(User user){
        repository.save(user);
    }
}
