package com.imooc.project.vo;

import com.imooc.project.entity.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends Resource {
	private static final long serialVersionUID = 1L;
	List<ResourceVO> subs;
}
