package com.finance.smartbudget.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {
    @RequestMapping ("/sign-in-page")
    public String getLoginPage() {
        return "temp-sign-in-page";
    }
}
