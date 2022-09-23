package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;

import java.util.List;

/*** 功能描述: 自定义SQL注入器
* @param:
* @return:
* @auther:
* @date:
*/
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        // 增加根据id逻辑删除数据, 并带字段填充功能
        methodList.add(new LogicDeleteByIdWithFill());

        return methodList;
    }
}
