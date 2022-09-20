package com.imooc.project.query;

import lombok.Data;

@Data
public class AccountQuery {

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 时间范围
     */
    private String createTimeRange;

    /**
     * 当前页
     */
    private Long page;

    /**
     * 每页条数
     */
    private Long limit;
}
