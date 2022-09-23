package com.imooc.project.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import com.imooc.project.util.MyQuery;
import com.imooc.project.util.QueryUtil;
import com.imooc.project.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author Jimmy
 * @since 2020-12-31
 */
@Controller
@RequestMapping("/customer")
@SuppressWarnings("deprecation")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // 进入列表页
    @GetMapping("/toList")
    public String toList() {
        return "customer/customerList";
    }

    // // 查询方法
    // @GetMapping("list")
    // @ResponseBody
    // public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
    //     LambdaQueryWrapper<Customer> wrapper = Wrappers.<Customer>lambdaQuery()
    //             .like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
    //             .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
    //             .orderByDesc(Customer::getCustomerId);
    //
    //     Page<Customer> customerPage = customerService.page(new Page<>(page, limit), wrapper);
    //
    //     return ResultUtil.buildPageR(customerPage);
    // }
    // 查询方法
	@GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(@RequestParam Map<String, String> param) {
        MyQuery<Customer> myQuery = QueryUtil.<Customer>buildMyQuery(param);

        Page<Customer> customerPage = customerService.page(myQuery.getPage(), myQuery.getWrapper().orderByDesc("customer_id"));

        return ResultUtil.buildPageR(customerPage);
    }

    // 进入新增页
    @GetMapping("/toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }

    // 新增客户
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.save(customer));
    }


    // 进入更新页
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable Long id, Model model) {
        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerUpdate";
    }

    // 修改客户
    @PutMapping
    @ResponseBody
    public R<Object> updateSubmit(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.updateById(customer));
    }

    // 删除客户
    @DeleteMapping("/{id}")
    @ResponseBody
    public R<Object> delete(@PathVariable Long id) {
        Customer customer = new Customer();
        customer.setCustomerId(id);

        return ResultUtil.buildR(customerService.removeByIdWithFill(customer));
    }

    // 进入详情页
    @GetMapping("/toDetail/{id}")
    public String toDetail(@PathVariable Long id, Model model) {

        Customer customer = customerService.getById(id);
        model.addAttribute("customer", customer);
        return "customer/customerDetail";
    }

}
