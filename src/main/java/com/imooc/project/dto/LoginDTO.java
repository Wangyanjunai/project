package com.imooc.project.dto;

import com.imooc.project.entity.Account;
import lombok.Data;

@Data
public class LoginDTO {
	
    private String path;
    
    private String error;

    private Account account;
}
