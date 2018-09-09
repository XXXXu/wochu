package com.wochu.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.wochu.dao.CategoryDao;
import com.wochu.enity.Category;

public class CategoryDaoImpl implements CategoryDao {

	BaseDao basedao = new BaseDao();
	public int deleteByPrimaryKey(Integer categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Category record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Category record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Category selectByPrimaryKey(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> selectAllParent() {
		String sql = "select * from t_category where level = 1";
		List<Category> list = new ArrayList<Category>();
		try {
			list = basedao.operQuery(sql, null, Category.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Category> selectAllSon() {
		String sql = "select * from t_category where level = 2";
		List<Category> list = new ArrayList<Category>();
		try {
			list = basedao.operQuery(sql, null, Category.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Category> selectAllTitleCategory(){
		String sql = "select * from t_category where level = 0";
		List<Category> list = new ArrayList<Category>();
		try {
			list = basedao.operQuery(sql, null, Category.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Category> selectAllSonByParentId(int parentId) {
		List<Category> categoryList = null;
		String sql = "select * from t_category where level = 2 and parent_id = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(parentId);
		try {
			categoryList = basedao.operQuery(sql, list, Category.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public int updateByPrimaryKeySelective(Category record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Category record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
