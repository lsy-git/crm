package com.crm.settings.controller;

import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import com.crm.settings.service.impl.UserServiceImpl;
import com.crm.utils.MD5Util;
import com.crm.utils.PrintJson;
import com.crm.utils.ServiceFactory;
import jdk.nashorn.internal.runtime.Debug;
import org.apache.log4j.spi.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author:刘守业
 * @Date: 2021/3/22 19:14
 */
public class UserController extends HttpServlet {

    private Logger logger = Logger.getLogger("UserController");


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("进入到用户控制器");

        String servletPath = req.getServletPath();
        if ("/settings/user/login.do".equals(servletPath)){
            login(req,resp);
        }else if("/settings/user/XXX.do".equals(servletPath)){

        }

    }

    private void login(HttpServletRequest request,HttpServletResponse response){
        logger.info("进入到验证操作");

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的明文形式转换为密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接收ip地址
        String ip = request.getRemoteAddr();
        logger.info("ip" + ip);

        //未来业务层开发，同一使用代理类形态的接口对象
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{

            User user = userService.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            //如果程序执行到此处，说明业务层没有任何问题
            //表示登录成功
            /*
            {"success":true}
             */
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            e.printStackTrace();
            //业务层验证失败，  表示登录失败
            /*
            {"success":true,"msg":"描述哪里出错"}
             */
            String msg = e.getMessage();
            /*
            作为controller，需要ajax请求提供多信息
            可以有两种手段处理：
            将多项信息打包成map，将map解析成json串
            创建一个Vo
                    priavte boolean success
                    private String msg

             如果展示的信息将来还会使用，就创建一个vo类
             如果将来的信息只在一个需求中使用，使用map就可以了
             */
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",map);
            PrintJson.printJsonObj(response,map);

        }

    }

}
