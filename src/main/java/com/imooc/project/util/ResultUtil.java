package com.imooc.project.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class ResultUtil {

    /**
     * 构建分页查询的返回结果
     *
     * @param page
     * @return
     */
    public static R<Map<String, Object>> buildPageR(IPage<?> page) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("count", page.getTotal());
        data.put("records", page.getRecords());
        return R.ok(data);
    }

    /**
     * 成功或者失败的响应信息
     * @param success
     * @return
     */
    public static R<Object> buildR(boolean success) {
        if (success) {
            return R.ok(null);
        }
        return R.failed("操作失败");
    }
}
