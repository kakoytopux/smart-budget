package com.finance.smartbudget.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    /**
     Проверка, работает ли приложение
     */
    @RequestMapping(value = "/isAlive", method = RequestMethod.GET)
    public ResponseEntity<?> isAlive() {
        return ResponseEntity.ok().body("OK");
    }
}
