package com.controller;

import com.entity.Communication;
import com.entity.Order;
import com.entity.Return;
import com.exception.PI4Exception;
import com.repository.CommunicationRepository;
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
public class CommunicationController {
    CommunicationRepository dao = new CommunicationRepository();

    @PostMapping(value = "/insert-communication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Return> insertCommunit(@RequestBody Communication cm) throws SQLException {
        if (cm != null) {
            if (dao.insertCommunication(cm)) {
                log.info(">>> communication has been successfully registered. <<<");
                Return returns = RetornoUtil.result(HttpStatus.CREATED);
                return ResponseEntity.accepted().body(returns);
            } else {
                Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
                return ResponseEntity.accepted().body(returns);
            }
        } else {
            throw new PI4Exception("the communication is null...");
        }
    }

    @GetMapping(value = "/view-communication", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Communication>> viewOrder() throws SQLException {

        List<Communication> find = new ArrayList<>();
        log.info(">>> View communications <<<");
        find = dao.viewCommunications();
        return ResponseEntity.accepted().body(find);
    }


}
