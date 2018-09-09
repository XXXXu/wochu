package com.wochu.servlet;

import com.wochu.dao.CartDao;
import com.wochu.dao.CategoryDao;
import com.wochu.dao.GoodsDao;
import com.wochu.dao.UserDao;
import com.wochu.dao.impl.CartDaoImpl;
import com.wochu.dao.impl.CategoryDaoImpl;
import com.wochu.dao.impl.GoodsDaoImpl;
import com.wochu.dao.impl.UserDaoImpl;
import com.wochu.enity.Cart;
import com.wochu.enity.Category;
import com.wochu.enity.Goods;
import com.wochu.enity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/1.
 */
@WebServlet(urlPatterns = "/User.jsp")
public class UserServlet extends BaseServlet {

    UserDao userDao = new UserDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();
    CategoryDao categoryDaoImpl = new CategoryDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();
    CartDao cartDao = new CartDaoImpl();
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	User user = (User)request.getSession().getAttribute("user");
    	List< List<Goods> > goodsList = new ArrayList< List<Goods> >();
	    List<Category> categoryList = categoryDao.selectAllTitleCategory();
	    List<Category> category_parent = categoryDaoImpl.selectAllParent();
	    for(int i = 0; i < categoryList.size(); i++) {
	    	
	    	goodsList.add(goodsDao.selectGoodsByCategoryId(categoryList.get(i).getCategoryId()));
	    
	    }
	    if(user != null) {
	    	//登陆成功,把购物车内记录存入数据库
	    	saveCart(user.getUserId(), request, response);
	    }
	    request.setAttribute("categoryList", categoryList);
	    request.setAttribute("category_parent", category_parent);
	    request.setAttribute("goodsList", goodsList);
    	
    	request.getRequestDispatcher("WEB-INF/page/index.jsp").forward(request, response);
    }
    
public void saveCart(int userId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	Map<Goods, Integer> cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
    	if(cart != null) {
    		for(Goods key: cart.keySet()) {
    			Cart record = new Cart();
    			if(cartDao.selectByGoodsId(userId, key.getGoodsId()) == null) {
	    			record.setAccountId(userId);
	    			record.setGoodsId(key.getGoodsId());
	    			record.setGoodsCount(cart.get(key));
	    			cartDao.insert(record);
    			}else {
    				record = cartDao.selectByGoodsId(userId, key.getGoodsId());
    				record.setGoodsCount(record.getGoodsCount()+cart.get(key));
    				cartDao.updata(record);
    			}
    		}
    	}
    	request.getSession().removeAttribute("cart");
    }
    
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userName = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String nextPassword = request.getParameter("nextpassword").trim();

        User user = userDao.login(userName);
        if(user != null ){
            response.getWriter().write("该用户已存在");
        }else{
            if(password.equals(nextPassword)){
                if(userDao.register(userName,password)>0){
                	user = userDao.login(userName);
                	request.getSession().setAttribute("user", user);
                	doPost(request, response);
                }else{
                    response.getWriter().write("注册失败");
                }
            }else{
                response.getWriter().write("密码不一致");
            }

        }
    }
    
    public void registerPage(HttpServletRequest request, HttpServletResponse response)
    	throws ServletException, IOException{
    	request.getRequestDispatcher("WEB-INF/page/register.jsp").forward(request, response);
    }
    public void outLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getSession().removeAttribute("user");
    	List<Category> category_parent = new ArrayList<Category>();
		//List<Category> categorySon = new ArrayList<Category>();
		category_parent = categoryDaoImpl.selectAllParent();
		//categorySon = categoryDaoImpl.selectAllSon();
		
		List< List<Goods> > goodsList = new ArrayList< List<Goods> >();
	    List<Category> categoryList = categoryDao.selectAllTitleCategory();
	    
	    for(int i = 0; i < categoryList.size(); i++) {
	    	
	    	goodsList.add(goodsDao.selectGoodsByCategoryId(categoryList.get(i).getCategoryId()));
	    
	    }
	    request.setAttribute("categoryList", categoryList);
	    request.setAttribute("goodsList", goodsList);
		
		request.setAttribute("category_parent", category_parent);
//		request.setAttribute("categorySon", categorySon);
    	request.getRequestDispatcher("WEB-INF/page/index.jsp").forward(request, response);
    }
}
