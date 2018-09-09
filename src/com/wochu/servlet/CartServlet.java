package com.wochu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONStringer;


import com.wochu.dao.CartDao;
import com.wochu.dao.GoodsDao;
import com.wochu.dao.impl.CartDaoImpl;
import com.wochu.dao.impl.GoodsDaoImpl;
import com.wochu.enity.Cart;
import com.wochu.enity.Goods;
import com.wochu.enity.User;

@WebServlet("/goodsCart.jsp")
public class CartServlet extends BaseServlet{
	
	GoodsDao goodsDao = new GoodsDaoImpl();
	CartDao cartDao = new CartDaoImpl();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			List<Goods> goodsList = goodsDao.selectGoodsByUserId(user.getUserId());
			List<Cart> cartList = cartDao.selectByUserId(user.getUserId());
			Map<Goods, Integer> cartMap = new HashMap<Goods, Integer>();
			for(int i = 0; i < cartList.size(); i++) {
				for(int j = 0; j < goodsList.size(); j++) {
					if(cartList.get(i).getGoodsId() == goodsList.get(j).getGoodsId()) {
						cartMap.put(goodsList.get(j), cartList.get(i).getGoodsCount());
					}
				}
			}
			request.setAttribute("cart", cartMap);
		}
		request.getRequestDispatcher("WEB-INF/page/cart.jsp").forward(request, response);
	}
	
	public void caculate(HttpServletRequest request, HttpServletResponse response) {
		
		User user = (User)request.getSession().getAttribute("user");
		JSONStringer stringer = new JSONStringer();
		if(user == null) {
			stringer.object().key("msg").value("fail").endObject();
		}else {
			stringer.object().key("msg").value("success").endObject();
		}
		try {
			response.setContentType("text/json, charset=UTF-8");
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");
		int res = cartDao.delete(user.getUserId());
		JSONStringer stringer = new JSONStringer();
		if(res > 0) {
			stringer.object().key("msg").value("success").endObject();
		} else {
			stringer.object().key("msg").value("fail").endObject();
		}
		try {
			response.setContentType("text/json, charset=UTF-8");
			response.getOutputStream().write(stringer.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void caculatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Goods, Integer> cartMap = new HashMap<Goods, Integer>();
		User user = (User)request.getSession().getAttribute("user");
		Double tempPrice = 0.0;
		
		List<Goods> goodsList = goodsDao.selectGoodsByUserId(user.getUserId());
		List<Cart> cartList = cartDao.selectByUserId(user.getUserId());
		for(int i = 0; i < goodsList.size(); i++) {
			for(int j = 0; j < cartList.size(); j++) {
				if(goodsList.get(i).getGoodsId() == cartList.get(j).getGoodsId()) {
					tempPrice += goodsList.get(i).getGoodsPresentPrice().doubleValue()*cartList.get(j).getGoodsCount();
					cartMap.put(goodsList.get(j), cartList.get(i).getGoodsCount());
				}
			}
		}
		request.setAttribute("totalPrice", tempPrice);
		request.setAttribute("cart", cartMap);
		request.getRequestDispatcher("WEB-INF/page/caculate.jsp").forward(request, response);
	}
	
	public String addGoods(HttpServletRequest request, HttpServletResponse response) {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		int num = 1;
		if(request.getParameter("num") != null) {
			num = Integer.parseInt(request.getParameter("num"));
		}
		Goods goods = goodsDao.selectGoodsByPrimeryKey(goodsId);
		User user = (User)request.getSession().getAttribute("user");
		Cart record = new Cart();
		if(user != null) {
			if(cartDao.selectByGoodsId(user.getUserId(), goodsId) == null) {
				record.setGoodsCount(num);
				record.setAccountId(user.getUserId());
				record.setGoodsId(goodsId);
				cartDao.insert(record);
			}else {
				record = cartDao.selectByGoodsId(user.getUserId(), goodsId);
				record.setAccountId(user.getUserId());
				record.setGoodsId(goodsId);
				record.setGoodsCount(record.getGoodsCount()+num);
				cartDao.updata(record);
			}
			
		} else {
			Map<Goods, Integer> cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
			//若session中无购物车cart，则创建一个
			if(cart == null) {
				cart = new HashMap<Goods, Integer>();
				cart.put(goods, num);
			} else {
				boolean flag = false;
				int count = 0;
				for(Goods key : cart.keySet()) {
					if(key.getGoodsId() == goods.getGoodsId() && key.getGoodsName().equals(goods.getGoodsName())) {
						flag = true;
						count = cart.get(key);
						cart.remove(key);
						break;
					}
				}
				if(flag) {
					cart.put(goods, count+num);
				} else {
					cart.put(goods, num);
				}
			}
			request.getSession().setAttribute("cart", cart);
		}
		return "success";
	}
	
	public String dismunis(HttpServletRequest request, HttpServletResponse response) {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			Cart record = cartDao.selectByGoodsId(user.getUserId(),goodsId);
			
			record.setGoodsCount(record.getGoodsCount()-1);
			record.setAccountId(user.getUserId());
			record.setGoodsId(goodsId);
			cartDao.updata(record);
		} else {
			Map<Goods, Integer> cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
			for(Goods key : cart.keySet()) {
				if(key.getGoodsId() == goodsId) {
					int count = cart.get(key)-1;
					cart.remove(key);
					cart.put(key, count);
					break;
				}
			}
		}
		return "success";
	}
	
	public String plus(HttpServletRequest request, HttpServletResponse response) {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		User user = (User)request.getSession().getAttribute("user");
		if(user != null) {
			Cart record = cartDao.selectByGoodsId(user.getUserId(), goodsId);
			record.setGoodsCount(record.getGoodsCount()+1);
			record.setAccountId(user.getUserId());
			record.setGoodsId(goodsId);
			cartDao.updata(record);
		} else {
			Map<Goods, Integer> cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
			for(Goods key : cart.keySet()) {
				if(key.getGoodsId() == goodsId) {
					int count = cart.get(key)+1;
					cart.remove(key);
					cart.put(key, count);
					break;
				}
			}
		}
		return "success";
	}
	public void deleteGoods(HttpServletRequest request, HttpServletResponse response) {
		int goodsId = Integer.parseInt(request.getParameter("goodsId"));
		User user = (User)request.getSession().getAttribute("user");
		Map<Goods, Integer> cart = new HashMap<Goods, Integer>();
		if(user != null) {
			cartDao.delete(user.getUserId(), goodsId);
			List<Goods> goodsList = goodsDao.selectGoodsByUserId(user.getUserId());
			List<Cart> cartList = cartDao.selectByUserId(user.getUserId());
			for(int i = 0; i < cartList.size(); i++) {
				for(int j = 0; j < goodsList.size(); j++) {
					if(cartList.get(i).getGoodsId() == goodsList.get(j).getGoodsId()) {
						cart.put(goodsList.get(j), cartList.get(i).getGoodsCount());
					}
				}
			}
		}else {
			cart = (Map<Goods, Integer>)request.getSession().getAttribute("cart");
			for(Goods key : cart.keySet()) {
				if(key.getGoodsId() == goodsId) {
					cart.remove(key);
					break;
				}
			}
		}
		JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
            stringer.array();    
            for(Goods key : cart.keySet()) {     
                stringer.object()
                .key("goodsId").value(key.getGoodsId())
                .key("goodsName").value(key.getGoodsName())
                .key("goodsImg").value(key.getGoodsImg())
                .key("goodsPresentPrice").value(key.getGoodsPresentPrice())
                .key("goodsOriginalPrice").value(key.getGoodsOriginalPrice())
                .key("goodsCount").value(cart.get(key))
                .endObject();    
            }    
            stringer.endArray();  
            object.element("cartjson", stringer.toString());  
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
        try {
			response.getOutputStream().write(object.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        response.setContentType("text/json; charset=UTF-8"); 
		
		
		/*String jsonstr = "[";
		int keycount = 0;
		for(Goods key : cart.keySet()) {
			keycount++;
			jsonstr += "{goodsId:'"+key.getGoodsId() + "',goodsName:'" + key.getGoodsName() + "'," + "goodsImg:'" + key.getGoodsImg() + "',"
						+"goodsPresentPrice:'" + key.getGoodsPresentPrice() + "'," + "goodsOriginalPrice:'" + key.getGoodsOriginalPrice()+"',"
						+"goodsCount:'" + cart.get(key) + "'}";
			if(keycount != cart.size()) {
				jsonstr += ",";
			}
		}
		jsonstr += "]";
		System.out.println(jsonstr);
		try {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(jsonstr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}
