package com.svalero.readify.controller;

import com.svalero.readify.domain.ErrorResponse;
import com.svalero.readify.domain.User;
import com.svalero.readify.exception.UserNotFoundException;
import com.svalero.readify.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //region GET
    @GetMapping("/users")
    public List<User> findAll(@RequestParam(defaultValue = "")String name){
        if(!name.isEmpty()){
            logger.info("GET /users?name=" + name);
            return userService.findByName(name);
        }
        logger.info("GET /users");
        return userService.getUsers();
    }
    @GetMapping("/user/{userId}")
    public Optional<User> getBook(@PathVariable long userId) {
        logger.info("ini GET /user" + userId);
        Optional<User> user = userService.getUserById(userId);
        logger.info("end GET /user" + userId);
        return user;
    }
    //endregion

    //region POST
    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        logger.info("ini POST /users");
        Boolean isCreated = userService.saveUser(user);
        logger.info("end POST /users");

        if(isCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    //endregion

    //region PUT
    @PutMapping("/user/{userId}")
    public void modifyUser(@RequestBody User user, @PathVariable long userId) {
        logger.info("ini PUT /user/" + userId);
        userService.modifyUser(user, userId);
        logger.info("end PUT /user/" + userId);
    }
    //endregion

    //region DELETE
    @DeleteMapping("/user/{userId}")
    public void removeUser(@PathVariable long userId) {
        logger.info("ini DELETE /user/" + userId);
        userService.removeUser(userId);
        logger.info("end DELETE /user/" + userId);
    }
    //endregion

    //region EXCEPTION HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> badRequestException(MethodArgumentNotValidException nve) {
        ErrorResponse errorResponse = new ErrorResponse(400, nve.getMessage());
        logger.error(nve.getMessage(), nve);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(MethodArgumentNotValidException unfe){
        ErrorResponse errorResponse = new ErrorResponse(404, unfe.getMessage());
        logger.error(unfe.getMessage(), unfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> internalServerError(HttpServerErrorException.InternalServerError ise){
        ErrorResponse errorResponse = new ErrorResponse(500, ise.getMessage());
        logger.error(ise.getMessage(), ise);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //endregion
}
