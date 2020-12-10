package com.controller;

import com.entity.Register;
import com.entity.Return;
import com.exception.PI4Exception;
import com.repository.RegisterRepository;
import com.util.RetornoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

import static com.util.configDateUtil.configuraData;

@Slf4j
@RestController
public class RegisterController {
    RegisterRepository dao = new RegisterRepository();

    @PostMapping(value = "/add-register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Return> addRegister(@RequestBody Register register) throws SQLException {
        if (register != null) {
            if (dao.insertRegister(register)) {
                log.info(">>> Register has been successfully registered. <<<");
                Return returns = RetornoUtil.result(HttpStatus.CREATED);
                return ResponseEntity.accepted().body(returns);
            } else {
                Return returns = RetornoUtil.result(HttpStatus.BAD_REQUEST);
                return ResponseEntity.accepted().body(returns);
            }
        } else {
            throw new PI4Exception("the register is null...");
        }
    }

    @GetMapping(value = "/view-register", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> addRegister(@RequestParam(value = "date") String data) throws SQLException {
        return ResponseEntity.accepted().body(String.valueOf(dao.viewRegister(configuraData(data))));
    }

}
