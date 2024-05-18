package com.finance.smartbudget.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginPageController {
    /**
    Вход пользователя в систему
     */
    @RequestMapping ("/sign-in-page")
    public String getLoginPage() {
        return "temp-sign-in-page";
    }
}
