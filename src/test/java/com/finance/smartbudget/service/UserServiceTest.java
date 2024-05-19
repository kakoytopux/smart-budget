package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

/**
 * Тесты для сервиса пользователя
 * @author puxichhh
 */

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  private final UserDto userDto = new UserDto();

  @Test
  public void encryptPasswordAndSaveNewUser() {
    userDto.setUsername("Taaaaa");
    userDto.setPassword("Test01");
    userDto.setAccountBalance(new BigDecimal(100));

    final String result = userService.encryptPasswordAndSaveNewUser(userDto);

    Assertions.assertEquals("", result);
  }

  @Test
  public void calculateMyBalance() {

    final BigDecimal result = userService.getStatementAnalyzerByAllMyTransactions().calculateTotalAmount();

    Assertions.assertEquals(100, result);
  }
}
