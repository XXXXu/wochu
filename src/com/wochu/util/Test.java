package com.wochu.util;

import java.util.ArrayList;
import java.util.List;

import com.wochu.dao.CategoryDao;
import com.wochu.dao.impl.CategoryDaoImpl;
import com.wochu.enity.Category;

/**
 * Created by Administrator on 2017/6/30.
 */
public class Test {

    public static void main(String[] arg){
//        UserDao userDao = new UserDaoImpl();
//
//        System.out.println(userDao.Register("luo","123"));
    	
    	CategoryDao categoryDaoImpl = new CategoryDaoImpl();
    	
    	List<Category> category_parent = new ArrayList<Category>();
		List<Category> categorySon = new ArrayList<Category>();
		category_parent = categoryDaoImpl.selectAllParent();
//		System.out.println(category_parent);
		categorySon = categoryDaoImpl.selectAllSon();
//		System.out.println(categorySon);
		List<Category> category = new ArrayList<Category>();
		for(int i = 0;i<category_parent.size();i++){
			System.out.println("*");
			for(int k = 0;k<categorySon.size();k++){
				System.out.println("#");
				System.out.println(categorySon.get(k).getParentId() + "and" + category_parent.get(i).getCategoryId());
				if(categorySon.get(k).getParentId() == category_parent.get(i).getCategoryId()){
					System.out.println(categorySon.get(k));
					Category categor = categorySon.get(k);
					category.add(categor);
				}
			}			
		}
		System.out.println(category);

    }
}
