package com.imooc.project.dao;

import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class InsertCustomerTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setRealName("王天风");
        customer.setSex("男");
        customer.setAge(41);
        customer.setEmail("wtf@126.com");
        customer.setPhone("17777777777");
        customer.setAddress("广州市白云区");
        customer.setCreateTime(LocalDateTime.now());
        boolean b = this.customerService.save(customer);
        log.info("b={}", b);
    }
}
