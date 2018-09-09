package com.wochu.dao;

import java.util.List;

import com.wochu.enity.Category;


public interface CategoryDao {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryId);
    
    List<Category> selectAllParent();
    
    List<Category> selectAllSon();
    
    List<Category> selectAllTitleCategory();
    
    public List<Category> selectAllSonByParentId(int parentId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}