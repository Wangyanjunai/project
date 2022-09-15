package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.imooc.project.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author jack
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;
}
