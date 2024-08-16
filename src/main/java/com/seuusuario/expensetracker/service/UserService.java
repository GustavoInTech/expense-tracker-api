package com.seuusuario.expensetracker.service;

import com.seuusuario.expensetracker.entity.User;
import com.seuusuario.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User>findById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        return  userRepository.save(user);
    }
    public void delete(Long id ){
        userRepository.deleteById(id);
    }
}
