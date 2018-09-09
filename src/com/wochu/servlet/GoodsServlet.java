package com.wochu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/Goods")
public class GoodsServlet extends BaseServlet{
	
	GoodsDao goodsDao = new GoodsDaoImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		/*request.getRequestDispatcher("WEB-INF/page/content.jsp").forward(request, response);*/
	}
	
	public void goodsDetail(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int goodId = Integer.parseInt(request.getParameter("goodId"));
		
		Goods good = goodsDao.selectGoodsByPrimeryKey(goodId);
		
		request.setAttribute("good", good);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/page/goods.jsp"); 
		dispatcher.forward(request, response);
	}
	
	public void goodsClassify(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		
		List<Goods> goodsList = goodsDao.selectGoodsByCategoryId(categoryId);
		
		request.setAttribute("goodsList", goodsList);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/page/classify.jsp");
		dispatcher.forward(request, response);
	}
	
	public void goodsByParentId(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int parentCategoryId = Integer.parseInt(request.getParameter("parentCategoryId"));
		
		List<Goods> goodsList = goodsDao.selectGoodsByParentCategoryId(parentCategoryId);
		
		request.setAttribute("goodsList", goodsList);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/page/classify.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void allGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		List<Goods> goodsList = goodsDao.selectAllGoods();
		
		request.setAttribute("goodsList", goodsList);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("WEB-INF/page/classify.jsp");
		dispatcher.forward(request, response);
	}
}
