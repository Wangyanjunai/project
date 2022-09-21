package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Role;
import com.imooc.project.service.ResourceService;
import com.imooc.project.service.RoleService;
import com.imooc.project.util.ResultUtil;
import com.imooc.project.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Controller
@RequestMapping("role")
@SuppressWarnings("deprecation")
public class RoleController {

    private RoleService roleService;

    private ResourceService resourceService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("toList")
    public String toList() {
        return "role/roleList";
    }

    @GetMapping("list")
    @ResponseBody
    public R<Map<String, Object>> list(String roleName, Long page, Long limit) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery();
        wrapper.like(StringUtils.isNotBlank(roleName), Role::getRoleName, roleName).orderByAsc(Role::getRoleId);
        return ResultUtil.buildPageR(this.roleService.page(new Page<Role>(page, limit), wrapper));
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("toAdd")
    public String toAdd() {
        return "role/roleAdd";
    }

    /**
     * 添加角色
     *
     * @return
     */
    @PostMapping
    @ResponseBody
    public R<Object> add(@RequestBody Role role) {
        return ResultUtil.buildR(this.roleService.saveRole(role));
    }

    /**
     * 获取资源列表
     *
     * @return
     */
    @GetMapping("listResource")
    @ResponseBody
    public R<List<TreeVO>> listResource() {
        return R.ok(resourceService.listResource());
    }
}
