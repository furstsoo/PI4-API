package com.controller;

import com.entidade.Order;
import com.entidade.Return;
import com.entidade.User;
import com.exception.PI4Exception;
import com.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class OrderController {
  User user = new User();
  OrderRepository dao = new OrderRepository();
  Return ret = new Return();
  Order order = new Order();

  @GetMapping("/insert-order")
  public ResponseEntity<Object> insertOrder(@RequestBody Order od) throws SQLException {
    if (od != null) {
      if (dao.insertOrder(od)) {
        log.info(">>> Order has been successfully registered. <<<");
        return new ResponseEntity<>(HttpStatus.CREATED);
      } else {
        ret.setAReturn("Error registering order.");
        ret.setId(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(ret, HttpStatus.BAD_REQUEST);
      }
    } else {
      throw new PI4Exception("the user is null...");
    }
  }
}
