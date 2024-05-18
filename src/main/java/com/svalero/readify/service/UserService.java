package com.svalero.readify.service;

import com.svalero.readify.domain.User;
import com.svalero.readify.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    //region GET
    public List<User> getUsers(){
        logger.info("GET /users -> service getUsers()");
        return userRepository.findAll();
    }
    public List<User> findByName(String name){
        logger.info("GET /users -> service findByName(String name) -> Name: " + name);
        return userRepository.findByName(name);
    }
    public Optional<User> getUserById(Long id) {
        logger.info("GET /user/{userId} -> service getUserById(Long id) -> Id: " + id);
        return userRepository.findById(id);
    }
    //endregion

    //region POST
    public boolean saveUser(User user){
        logger.info("POST /users -> service saveUser(User user) -> " +
                "\n\tId: " + user.getId() +
                "\n\tName: " + user.getName() +
                "\n\tEmail: " + user.getEmail() +
                "\n\tMembership date: " + user.getMembershipDate() +
                "\n\tActive: " + user.getActive() +
                "\n\tRole: " + user.getRole());
        User auxUser = userRepository.save(user);
        logger.info("POST /users -> service saveUser(User user)");

        if(auxUser != null){
            return true;
        } else{
            return false;
        }
    }
    //endregion

    //region PUT
    public void modifyUser(User newUser, long userId){

        logger.info("ini PUT /user{userId} -> service modifyUser(User newUser, long userId) -> findById(userId) -> UserId: " + userId);
        Optional<User> user = userRepository.findById(userId);
        logger.info("end PUT /user{userId} -> service modifyUser(User newUser, long userId) -> findById(userId) -> UserId: " + userId);

        if(user.isPresent()){
            User existingUser = user.get();

            existingUser.setName(newUser.getName());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setMembershipDate(newUser.getMembershipDate());
            existingUser.setActive(newUser.getActive());
            existingUser.setRole(newUser.getRole());

            logger.info("ini PUT /user{userId} -> service modifyUser(User newUser, long userId) -> save(existingUser) -> UserId: " + userId);
            userRepository.save(existingUser);
            logger.info("end PUT /user{userId} -> service modifyUser(User newUser, long userId) -> save(existingUser) -> UserId: " + userId);
        }
    }
    //endregion

    //region DELETE
    public void removeUser(long userId){
        logger.info("ini DELETE /user{userId} -> service removeUser(long userId) -> UserId: " + userId);
        userRepository.deleteById(userId);
        logger.info("end DELETE /user{userId} -> service removeUser(long userId) -> UserId: " + userId);
    }
    //endregion
}