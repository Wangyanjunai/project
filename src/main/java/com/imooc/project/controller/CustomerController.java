package com.imooc.project.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import com.imooc.project.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Slf4j
@Controller
@RequestMapping("customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "customer/customerList";
    }


    @GetMapping("list")
    @ResponseBody
    @SuppressWarnings("deprecation")
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
        LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(realName), Customer::getRealName, realName).like(StringUtils.isNotBlank(phone), Customer::getPhone, phone).orderByDesc(Customer::getCustomerId).orderByDesc(Customer::getCreateTime);
        Page<Customer> customerPage = this.customerService.page(new Page<Customer>(page, limit), wrapper);
        return ResultUtil.buildPageR(customerPage);
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    @PostMapping
    @ResponseBody
    @SuppressWarnings("deprecation")
    public R<Object> add(@RequestBody Customer customer) {
        return ResultUtil.buildR(this.customerService.save(customer));
    }
}
