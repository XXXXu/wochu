package com.wochu.dao.impl;

import com.mysql.jdbc.Statement;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Administrator on 2017/6/30.
 */
@SuppressWarnings("all")
public class BaseDao {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://123.207.18.20:3306/wochu?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASS = "982479748";

    static {
        try {
            Class.forName(DRIVER);// 加载驱动
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASS);// 连接数据库
        } catch (SQLException e) {
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
        return conn;
    }

    // 更新操作(增删改)
    public int operUpdate(String sql, List<Object> p) {
        Connection conn = null;
        PreparedStatement pste = null;
        int res = 0;
        conn = getConn();
        try {
            pste = conn.prepareStatement(sql);
            if (p != null) {
                for (int i = 0; i < p.size(); i++) {
                    pste.setObject(i + 1, p.get(i));
                }
            }
            res = pste.executeUpdate();
            //执行
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseAll(null, pste, conn);//关闭连接，释放资源
        }
        return res;//
    }

    // 查询操作（查）
    public <T> List<T> operQuery(String sql, List<Object> p, Class<T> cls)
            throws Exception {
        Connection conn = null;
        PreparedStatement pste = null;//预处理语句
        ResultSet rs = null;//结果集
        List<T> list = new ArrayList<T>();
        conn = getConn();
        try {
            pste = conn.prepareStatement(sql);
            if (p != null) {//将条件值装入预处理语句
                for (int i = 0; i < p.size(); i++) {
                    pste.setObject(i + 1, p.get(i));
                }
            }
            rs = pste.executeQuery();//执行
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                T m = cls.newInstance();//反射
                for (int j = 0; j < rsmd.getColumnCount(); j++) {
                    //从数据库中取得字段名
                    String col_name = rsmd.getColumnName(j + 1);
                    Object value = rs.getObject(col_name);

                    //实现数据库字段名到实体类字段名的转换
                    String fildName = "";
                    for(int i = 0; i < col_name.length(); i++) {
                        if(col_name.charAt(i) == '_') {
                            i++;
                            fildName += col_name.substring(i, i+1).toUpperCase();
                        }
                        else {
                            fildName += col_name.charAt(i);
                        }
                    }

                    Field field = cls.getDeclaredField(fildName);
                    field.setAccessible(true);//类中的成员变量为private,故必须进行此操作
                    field.set(m, value);
                }
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            releaseAll(rs, pste, conn);//关闭连接，释放资源
        }
        return list;
    }

 // 有结果集返回的inser操作
 		public int havaRsInsert(String sql, List<Object> p) {
 			Connection conn = null;
 			PreparedStatement pste = null;
 			ResultSet rs = null;
 			int res = 0;
 			conn = getConn();
 			try {
 				pste = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 				//Statement.RETURN_GENERATED_KEYS 以获取自增长的键值
 				if (p != null) {
 					for (int i = 0; i < p.size(); i++) {
 						pste.setObject(i + 1, p.get(i));
 					}
 				}
 				pste.executeUpdate();
 				//执行
 				rs = pste.getGeneratedKeys();//得结果集
 				if(rs.next()) {
 					res = rs.getInt(1);//得键值
 				}
 			} catch (SQLException e) {
 				e.printStackTrace();
 			} finally {
 				releaseAll(null, pste, conn);//关闭连接，释放资源
 			}
 		System.out.println(res);
 			return res;//
 		}
    // 关闭连接，释放资源
    private void releaseAll(ResultSet res, PreparedStatement pste,
                            Connection conn) {
        try {
            if (res != null)
                res.close();
            if (pste != null)
                pste.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
