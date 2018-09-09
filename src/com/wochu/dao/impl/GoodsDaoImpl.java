package com.wochu.dao.impl;

import com.wochu.dao.GoodsDao;
import com.wochu.enity.Goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/2.
 */
public class GoodsDaoImpl implements GoodsDao {

    BaseDao baseDao = new BaseDao();

    public List<Goods> selectGoodsByCategoryId(int categoryId){
        List<Goods> goods = null;
        String sql = "select a.goods_id, goods_name, goods_presentPrice, goods_originalPrice, goods_img, goods_origin, goods_specifications," +
                "goods_shelflife, goods_storageCondition, goods_introduce from t_goods as a, t_goods_category as b where " +
                "a.goods_id = b.goods_id and b.category_id = ?";
        List<Object> list = new ArrayList<Object>();
        list.add(categoryId);
        try {
            goods = baseDao.operQuery(sql, list, Goods.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  goods;
    }

	public Goods selectGoodsByPrimeryKey(int goodsId) {
		String sql = "select * from t_goods where goods_id="+goodsId;
		Goods goods = new Goods();
		try {
			goods = baseDao.operQuery(sql, null, Goods.class).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goods;
	}

	public List<Goods> selectGoodsByUserId(int userId) {
		String sql = "select t_cart.goods_id,goods_name,goods_presentPrice,goods_originalPrice,goods_img,goods_imgnum,goods_origin,goods_specifications,goods_shelflife,goods_storageCondition,goods_introduce from t_goods,t_cart where t_goods.goods_id = t_cart.goods_id and t_cart.account_id = "+userId;
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			goodsList = baseDao.operQuery(sql, null, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

	public List<Goods> selectGoodsByParentCategoryId(int parentCategoryId){
		 List<Goods> goods = null;
	        String sql = "select a.goods_id, goods_name, goods_presentPrice, goods_originalPrice, goods_img, " +
	        		"goods_imgnum, goods_origin, goods_specifications,goods_shelflife, goods_storageCondition," +
	        		" goods_introduce from t_goods as a,t_category as b, t_goods_category as c where " +
	        		"a.goods_id = c.goods_id and c.category_id = b.category_id and b.parent_id = ?";
	        List<Object> list = new ArrayList<Object>();
	        list.add(parentCategoryId);
	        try {
	            goods = baseDao.operQuery(sql, list, Goods.class);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        return  goods;
	}

	public List<Goods> selectAllGoods(){
		String sql = "select * from t_goods";
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			goodsList = baseDao.operQuery(sql, null, Goods.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsList;
	}

}
