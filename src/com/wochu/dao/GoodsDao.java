package com.wochu.dao;

import com.wochu.enity.Goods;

import java.util.List;

public interface GoodsDao {

    public List<Goods> selectGoodsByCategoryId(int categoryId);

    public Goods selectGoodsByPrimeryKey(int goodsId);
    
    public List<Goods> selectGoodsByParentCategoryId(int parentCategoryId);
    
    public List<Goods> selectAllGoods();
    
    //购物车中属于该用户的商品
    public List<Goods> selectGoodsByUserId(int userId);
}