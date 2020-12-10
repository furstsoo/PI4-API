package com.controller;

import com.entity.Order;
import com.entity.Return;
import com.exception.PI4Exception;
import com.repository.OrderRepository;
import com.util.RetornoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class OrderController {
  OrderRepository dao = new OrderRepository();
  Order order = new Order();

  @PostMapping(value = "/insert-order", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Return> insertOrder(@RequestBody Order od) throws SQLException {
    if (od != null) {
      if (dao.insertOrder(od)) {
        log.info(">>> Order has been successfully registered. <<<");
        Return returns = RetornoUtil.result(HttpStatus.CREATED);
        return ResponseEntity.accepted().body(returns);
      } else {
        Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
        return ResponseEntity.accepted().body(returns);
      }
    } else {
      throw new PI4Exception("the order is null...");
    }
  }

  /* editar a encomenda - encomenda já foi entregue e com isso pode ser desativada
   * Quando recebe requisição automaticamente recebe a data de pickup e muda o status pra I = inativo */
  @PostMapping(value = "/edit-order", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Return> editOrder(@RequestBody Order od) throws SQLException {
    if (od != null) {
      if (dao.updateOrder(od)) {
        Return returns = RetornoUtil.result(HttpStatus.OK);
        return ResponseEntity.accepted().body(returns);
      } else {
        Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
        return ResponseEntity.accepted().body(returns);
      }
    } else {
      throw new PI4Exception("the order is null...");
    }
  }

  /* busca encomenda por usuario - no app visao condomino -> manda o id do usuario devolve todas as encomendas -> Visão do condomino */
  @GetMapping(value = "/view-order", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Order>> viewOrder(@RequestParam(value = "name", required = false) String name,
                                               @RequestParam(value = "block", required = false) String block,
                                               @RequestParam(value = "apartment", required = false) String apartment) throws SQLException {

    List<Order> find = new ArrayList<>();
    log.info(">>> View order <<<");
    if (name == null) {
      find = dao.findOrdersCondomino(block, apartment);
    } else {
      find = dao.findOrdersOperador(name, block, apartment);
    }
    return ResponseEntity.accepted().body(find);
  }



}