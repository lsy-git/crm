package com.crm.settings.dao;

import com.crm.settings.domain.User;

import java.util.Map;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 22:54
 */
public interface UserDao {

    User login(Map<String,String> map);
}
