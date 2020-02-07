package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

import SystemUtil.ConnectUtil;
import SystemUtil.MakeSqlUtil;
import SystemUtil.OperateUtil;
import SystemUtil.SystemAnnotation;

/*
 * T represents it supports all styles of entity
 * 
 * Here we don't need to pay attention to whether there are multiple connections,
 * because we have checked it when we create connUtil object.
 */

public class BaseDao<T> {
	
	public int insertEntity(T type) throws Exception {
		ConnectUtil connUtil = new ConnectUtil();
		Connection conn = connUtil.getConn();
		Class<?> clz = type.getClass();
		MakeSqlUtil mkSql = new MakeSqlUtil();
		String sql = mkSql.createInsertSql(clz);
		PreparedStatement ps = conn.prepareStatement(sql);
		int parameterIndex = 1;
		for(Field field: clz.getDeclaredFields()) {
			SystemAnnotation an = field.getAnnotation(SystemAnnotation.class);
			if(an.readOnly()) continue;
			Method method = clz.getDeclaredMethod(an.getMethod());
			Object obj = method.invoke(type);
			ps.setObject(parameterIndex++, obj);
		}
		return ps.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,T> findSpecificEntity(T type) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		ConnectUtil connUtil = new ConnectUtil();
		Connection conn = connUtil.getConn();
		Map<String,T> map = new Hashtable<>();
		Class<?> clz = type.getClass();
		MakeSqlUtil mkSql = new MakeSqlUtil();
		System.out.println(clz);
		String sql = mkSql.createFindSQL(clz);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		System.out.println(sql);
		OperateUtil.orderName.clear();
		
		while(rs.next()) {
			T obj = (T) clz.getConstructor().newInstance();
			Object keyName = null;
			for(Field field : clz.getDeclaredFields()) {
				SystemAnnotation an = field.getAnnotation(SystemAnnotation.class);
				String columnName = an.columnName();
				Object objVar = rs.getObject(columnName);
				if(columnName.equals("student_name")) {
					keyName = objVar;
					OperateUtil.orderName.add((String) keyName);
				}
				Method method = clz.getDeclaredMethod(an.setMethod(),Object.class);
				method.invoke(obj, objVar);
			}
			
			map.put((String) keyName, obj);
		}
		
		return map;
	}
	
	/*
	 * This method get one object and with this input object information
	 * 	1. we can get its field. With the annotation, we can get each field's corresponding column name and
	 * get each column's information.
	 *  2. Initialize one new object same as the input object's class
	 *  	!! USING 'clz.getConstructor().newInstance();'
	 * 	3. Using set method, which is also got by the annotation, to set each field in the new instance.
	 * 		!! When we use 'getDeclaredMethod' to get methods having parameters, 
	 * 			we have to put those parameters' classes after its method's name
	 * 
	 * 	4. In the while loop:
	 * 			a. To create a new object, which will be put into the result list.
	 * 			b. get information from each 'type'(the object)
	 * 			c. get each column name and by this column name to search the table
				d. column name --> set method --> complete new object
	 */
	@SuppressWarnings("unchecked")
	public Map<String,T> findAllEntity(T type) throws Exception{
		ConnectUtil connUtil = new ConnectUtil();
		Connection conn = connUtil.getConn();
		Map<String,T> map = new Hashtable<>();
		Class<?> clz = type.getClass();
		MakeSqlUtil mkSql = new MakeSqlUtil();
		System.out.println(clz);
		String sql = mkSql.createFindAllSQL(clz);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		System.out.println(sql);
		if(sql.contains("student"))
			OperateUtil.orderName.clear();
		while(rs.next()) {
			T obj = (T) clz.getConstructor().newInstance();
			Object keyName = null;
			for(Field field : clz.getDeclaredFields()) {
				SystemAnnotation an = field.getAnnotation(SystemAnnotation.class);
				String columnName = an.columnName();
				Object objVar = rs.getObject(columnName);
				if(columnName.equals("student_name")||columnName.equals("username")) {
					keyName = objVar;
				}
				if(columnName.equals("student_name")) OperateUtil.orderName.add((String) keyName);
				Method method = clz.getDeclaredMethod(an.setMethod(),Object.class);
				method.invoke(obj, objVar);
			}
			
			map.put((String) keyName, obj);
		}
		
		return map;
	}
}
