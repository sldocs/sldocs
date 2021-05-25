package com.dddd.SLDocs.auth.servImpls;

import com.dddd.SLDocs.auth.entities.User;
import com.dddd.SLDocs.auth.repositories.RoleRepository;
import com.dddd.SLDocs.auth.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getUserByUsername(String email){
        return userRepository.getUserByUsername(email);
    }

    public void addUser(User user){
        user.getRoles().add(roleRepository.getRoleByName("ADMIN"));
        userRepository.save(user);
    }
}
