package com.imooc.project.service.impl;

import com.imooc.project.entity.Customer;
import com.imooc.project.dao.CustomerMapper;
import com.imooc.project.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
