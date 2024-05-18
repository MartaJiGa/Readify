package com.svalero.readify.service;

import com.svalero.readify.domain.User;
import com.svalero.readify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //region GET
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    //endregion

    //region POST
    public void saveUser(User user){
        userRepository.save(user);
    }
    //endregion

    //region PUT
    public void modifyUser(User newUser, long userId){
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            User existingUser = user.get();

            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setMembershipDate(newUser.getMembershipDate());
            existingUser.setActive(newUser.getActive());
            existingUser.setRole(newUser.getRole());

            userRepository.save(existingUser);
        }
    }
    //endregion

    //region DELETE
    public void removeUser(long userId){
        userRepository.deleteById(userId);
    }
    //endregion
}