package com.wochu.servlet;

import com.wochu.dao.CartDao;
import com.wochu.dao.UserDao;
import com.wochu.dao.impl.CartDaoImpl;
import com.wochu.dao.impl.UserDaoImpl;
import com.wochu.enity.Cart;
import com.wochu.enity.Goods;
import com.wochu.enity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.util.JSONStringer;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1.
 */
@WebServlet(urlPatterns = "/userLogin")
public class LoginServlet extends BaseServlet {

    UserDao userDao = new UserDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        
        User user = userDao.login(userName);
        JSONStringer jsonstr = new JSONStringer();
        if(user == null){
        	jsonstr.object().key("status").value("fail");
        	jsonstr.key("message").value("该用户不存在").endObject();
        }else{
            if(user.getUserPassword().equals(password)){
            	jsonstr.object().key("status").value("success");
            	jsonstr.key("url").value("User.jsp").endObject();
            	request.getSession().setAttribute("user", user);
            	
            }else{
            	jsonstr.object().key("status").value("fail");
            	jsonstr.key("message").value("密码错误").endObject();
            }
        }
        response.setContentType("text/json; charset=UTF-8");
        response.getOutputStream().write(jsonstr.toString().getBytes("UTF-8"));
    }
    
    public void pageChange(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException{
    	
    	request.getRequestDispatcher("WEB-INF/page/login.jsp").forward(request, response);
    }
    
    
}
