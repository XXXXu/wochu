package com.wochu.dao;

import java.util.List;

import com.wochu.enity.Cart;
import com.wochu.enity.Goods;

public interface CartDao {
    int insert(Cart record);

    int insertSelective(Cart record);
    
    Cart selectByGoodsId(int userId, int goodsId);
    
    List<Cart> selectByUserId(int userId);
    
    int delete(int userId, int goodsId);
    
    int delete(int userId);
    
    int updata(Cart record);
}