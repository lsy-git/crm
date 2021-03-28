package com.crm.settings.service.impl;

import com.crm.exception.LoginException;
import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import com.crm.utils.DateTimeUtil;
import com.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 22:45
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);
        if (user == null){
            throw new LoginException("账号密码错误");
        }

        //如果程序能够成功的执行带该行，说明账号密码正确
        //需要继续向下验证其他三项信息
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime) < 0){
            throw new LoginException("账号失效");
        }

        //判断锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }

        //判断ip地址
        String allowIp = user.getId();
        if (!allowIp.contains(ip)){
            throw new LoginException("ip地址受限");
        }
        return user;
    }
}
