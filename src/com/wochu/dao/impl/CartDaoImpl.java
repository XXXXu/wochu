package com.wochu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wochu.dao.CartDao;
import com.wochu.enity.Cart;
import com.wochu.enity.Goods;

public class CartDaoImpl implements CartDao {

	BaseDao baseDao = new BaseDao();
	public int insert(Cart record) {
		String sql = "insert into t_cart values(?,?,?)";
		List<Object> list = new ArrayList<Object>();
		list.add(record.getAccountId());
		list.add(record.getGoodsId());
		list.add(record.getGoodsCount());
		return baseDao.operUpdate(sql, list);
	}

	public int insertSelective(Cart record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Cart> selectByUserId(int userId) {
		String sql = "select * from t_cart where account_id = "+userId;
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			cartList = baseDao.operQuery(sql, null, Cart.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public Cart selectByGoodsId(int userId, int goodsId) {
		String sql = "select * from t_cart where account_id = "+userId+" and goods_id = "+goodsId;
		List<Cart> cartList = new ArrayList<Cart>();
		try {
			cartList = baseDao.operQuery(sql, null, Cart.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList.size() > 0 ? cartList.get(0) : null;
	}

	public int updata(Cart record) {
		String sql = "update t_cart set goods_count = "+record.getGoodsCount()+" where account_id = "+record.getAccountId()
					 +" and goods_id = "+record.getGoodsId();
		return baseDao.operUpdate(sql, null);
	}

	public int delete(int userId, int goodsId) {
		String sql = "delete from t_cart where account_id = "+userId
					 +" and goods_id = "+goodsId;
		return baseDao.operUpdate(sql, null);
	}

	public int delete(int userId) {
		String sql = "delete from t_cart where account_id = "+userId;
		return baseDao.operUpdate(sql, null);
	}

}
