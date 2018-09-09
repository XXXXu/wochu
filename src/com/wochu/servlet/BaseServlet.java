package com.wochu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/6/30.
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");//

        String name = req.getParameter("method");// 获取方法名
        Class c = this.getClass();// 获得当前类的Class对象
        Method method = null;
        if (name == null || name.isEmpty()) {
            try {
                method = c.getMethod("doPost", HttpServletRequest.class,
                        HttpServletResponse.class);
                method.invoke(this, req, resp);// 反射调用方法
                return;
            } catch (SecurityException e) {
                throw new RuntimeException("没有找到doPost");
            }  catch (Exception e) {
                System.out.println("你调用的方法doPost,内部发生了异常");
                throw new RuntimeException(e);
            }
        }

        try {
            // 获得Method对象
            method = c.getMethod(name, HttpServletRequest.class,
                    HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("没有找到" + name
                    + "带有Request，Response的方法，请检查该方法是否存在");
        }

        try {
            method.invoke(this, req, resp);// 反射调用方法
        } catch (Exception e) {
            System.out.println("你调用的方法" + name + ",内部发生了异常");
            throw new RuntimeException(e);
        }

    }
}
