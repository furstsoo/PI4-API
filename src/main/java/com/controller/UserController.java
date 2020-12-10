package com.controller;

import com.entity.User;
import com.repository.UserRepository;
import com.entity.Return;
import com.exception.PI4Exception;
import com.util.RetornoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RestController
public class UserController {

    User user = new User();
    UserRepository dao = new UserRepository();
    Return ret = new Return();

    @PostMapping(value = "/insert-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@RequestBody User us) throws SQLException {
        if (us != null) {
            user = dao.insertUser(us);
            if (user != null) {
                log.info(">>> User has been successfully registered. <<<");
                return ResponseEntity.accepted().body(user);
            } else {
                return ResponseEntity.accepted().body(null);
            }
        } else {
            throw new PI4Exception("the user is null...");
        }
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> login(@RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) throws SQLException {
        user = dao.login(email, password);
        if (email.toLowerCase().equals(user.getEmail().toLowerCase()) && password.equals(user.getPassword())) {
            log.info(">>> Login successfully <<<");
            return ResponseEntity.accepted().body(user);
        } else {
            return ResponseEntity.accepted().body(null);
        }
    }

    @PostMapping(value = "/update-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Return> updateUser(@RequestBody User us) throws SQLException {
        if (us != null) {
            if (dao.updateUser(us)) {
                log.info("User has been successfully edited.");
                Return returns = RetornoUtil.result(HttpStatus.OK);
                return ResponseEntity.accepted().body(returns);
            } else {
                Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
                return ResponseEntity.accepted().body(returns);
            }
        } else {
            throw new PI4Exception("the user is null...");
        }
    }

    @GetMapping("/view-user")
    public ResponseEntity<Object> viewUser(@RequestParam(value = "id") int id) throws SQLException {
        log.info(">>> View User <<<");
        user = dao.findUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/delete-user")
    public ResponseEntity<Object> deleteUser(@RequestParam(value = "id") int id) throws SQLException {
        if (id != 0) {
            if (dao.deleteUser(id)) {
                log.info("User successfully deleted.");
                Return returns = RetornoUtil.result(HttpStatus.OK);
                return ResponseEntity.accepted().body(returns);
            } else {
                Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
                return ResponseEntity.accepted().body(returns);
            }
        } else {
            throw new PI4Exception("the user is null...");
        }
    }
}
