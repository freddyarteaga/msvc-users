package com.farteaga.arkam.msvc_users.service;

import com.farteaga.arkam.msvc_users.model.User;
import com.farteaga.arkam.msvc_users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    //@Autowired
    private final UserRepository userRepository;


    public List<User> allUsers() {
        return  userRepository.findAll();
    }

    public User newUser(User user){
        return userRepository.save(user);
    }


    public User getUser(Long id){
      return  userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user){
        Optional<User> userExist = userRepository.findById(id);
        if (userExist.isPresent()){
            user.setId(id);
            return userRepository.save(user);
        }

        return null;
    }

    public boolean deleteUser(Long id){
        Optional<User> userExist = userRepository.findById(id);
        if (userExist.isPresent()){
            userRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<User> findAllOrder (){
        return userRepository.findAllByOrderByFirstNameAsc();
    }

    public List<User> searchUsersByName (String firstName){
        return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    }



}