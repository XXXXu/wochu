package com.wochu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wochu.dao.CategoryDao;
import com.wochu.dao.GoodsDao;
import com.wochu.dao.impl.CategoryDaoImpl;
import com.wochu.dao.impl.GoodsDaoImpl;
import com.wochu.enity.Category;
import com.wochu.enity.Goods;

@WebServlet("/Main.jsp")
public class MainServlet extends BaseServlet{
	
	CategoryDao categoryDaoImpl = new CategoryDaoImpl();
	CategoryDao categoryDao = new CategoryDaoImpl();
	GoodsDao goodsDao = new GoodsDaoImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		List<Category> category_parent = new ArrayList<Category>();
		List<Category> categorySon = new ArrayList<Category>();
		category_parent = categoryDaoImpl.selectAllParent();
		categorySon = categoryDaoImpl.selectAllSon();
		
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
