package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.imooc.project.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自动填充类
 */
@Component
public class MyMetaObjectHander implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
        Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
        if (!Objects.isNull(account)) {
            Long accountId = ((Account) account).getAccountId();
            if (metaObject.hasSetter("createAccountId")) {
                this.strictInsertFill(metaObject, "createAccountId", Long.class, accountId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifiedTime")) {
            this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
        }
        Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
        if (!Objects.isNull(account)) {
            Long modifiedAccountId = ((Account) account).getAccountId();
            if (metaObject.hasSetter("modifiedAccountId")) {
                this.strictUpdateFill(metaObject, "modifiedAccountId", Long.class, modifiedAccountId);
            }
        }
    }
}
