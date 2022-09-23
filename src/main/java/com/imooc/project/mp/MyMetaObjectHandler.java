package com.imooc.project.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.imooc.project.entity.Account;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter("createAccountId")) {
            // 自动填充创建人
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null && account instanceof Account) {
                Long accountId = ((Account) account).getAccountId();
                this.strictInsertFill(metaObject, "createAccountId", Long.class, accountId);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("modifiedTime")) {
            this.strictUpdateFill(metaObject, "modifiedTime", LocalDateTime.class, LocalDateTime.now());
        }
        if (metaObject.hasSetter("modifiedAccountId")) {
            // 自动填充修改人
            Object account = RequestContextHolder.getRequestAttributes().getAttribute("account", RequestAttributes.SCOPE_SESSION);
            if (account != null && account instanceof Account) {
                Long accountId = ((Account) account).getAccountId();
                this.strictUpdateFill(metaObject, "modifiedAccountId", Long.class, accountId);
            }
        }

    }
}
