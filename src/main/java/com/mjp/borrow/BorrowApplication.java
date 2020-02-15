package com.mjp.borrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 开始事务
@EnableTransactionManagement

public class BorrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class, args);
    }

}
