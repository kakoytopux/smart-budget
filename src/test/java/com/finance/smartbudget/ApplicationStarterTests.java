package com.finance.smartbudget;

import com.finance.smartbudget.parsers.BankStatementCSVParser;
import com.finance.smartbudget.parsers.BankStatementParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationStarterTests {

	BankStatementParser csvParser = new BankStatementCSVParser();
	@Test
	void contextLoads() {
	}

}
