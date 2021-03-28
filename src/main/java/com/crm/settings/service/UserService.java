package com.crm.settings.service;

import com.crm.exception.LoginException;
import com.crm.settings.domain.User;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 22:44
 */
public interface UserService {

    User login(String loginAct,String loginPwd,String ip) throws LoginException;
}
