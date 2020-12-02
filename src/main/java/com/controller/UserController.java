package com.controller;

import com.entity.User;
import com.repository.UserRepository;
import com.entity.Return;
import com.exception.PI4Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class UserController {

  User user = new User();
  UserRepository dao = new UserRepository();
  Return ret = new Return();

  @GetMapping("/insert-user")
  public ResponseEntity<Object> insertUser(@RequestBody User us) throws SQLException {
    if (us != null) {
      if (dao.insertUser(us)) {
        log.info(">>> User has been successfully registered. <<<");
        return new ResponseEntity<>(HttpStatus.CREATED);
      } else {
        ret.setAReturn("Error registering user.");
        ret.setId(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
      }
    } else {
      throw new PI4Exception("the user is null...");
    }
  }

  @GetMapping("/login")
  public ResponseEntity<Object> login(@RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) throws SQLException {
    user = dao.login(email, password);

    if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
      log.info(">>> Login successfully <<<");
      return new ResponseEntity<>(user.getId(), HttpStatus.OK);

    } else {
      ret.setAReturn("Username or password is wrong, check.");
      return new ResponseEntity<>(ret, HttpStatus.OK);
    }

  }

  @GetMapping("/update-user")
  public ResponseEntity<Object> updateUser(@RequestBody User us) throws SQLException {
    if (us != null) {
      if (dao.updateUser(us)) {
        log.info("User has been successfully edited.");
        return new ResponseEntity<>(user, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
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
        ret.setAReturn("User successfully deleted.");
        return new ResponseEntity<>(ret, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } else {
      throw new PI4Exception("the user is null...");
    }
  }
}
