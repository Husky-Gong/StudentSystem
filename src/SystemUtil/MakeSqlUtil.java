package SystemUtil;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/*
 * This class will help user to login and register and search student information, functions will contain:
 * 		1. Create SQL statement to register
 * 		2. Find all SQL statement to print all information from database tables
 * 		3. Search student SQL statement using 'like' of student_name
 */
public class MakeSqlUtil {
	/*
	 * 1. create new user/student SQL statement
	 */
	public String createInsertSql(Class<?> clz) {
		Map<String,String> paraMap = getParams(clz);
		StringBuilder res = new StringBuilder(100);
		res.append("insert into ")
			.append(paraMap.get("tableName"))
			.append(paraMap.get("params"))
			.append(" VALUES ")
			.append(paraMap.get("preParams"));
		return res.toString();
	}
	
	public String createFindSQL(Class<?> clz) {
		Map<String,String> paraMap = getParams(clz);
		StringBuilder res = new StringBuilder(100);
		String params = paraMap.get("params");
		String likeName = findSqlHelper();
		int endIndex = params.length()-1;
		res.append("select ")
				.append(params.substring(1, endIndex))
					.append(" from ")
						.append(paraMap.get("tableName"))
							.append(" where student_name like '%")
								.append(likeName)
									.append("%' order by student_score desc");
		return res.toString();
	}
	
	@SuppressWarnings("resource")
	private String findSqlHelper() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input student's full name or part of it to search:");
		String likeName = input.next();
		return likeName;
	}
	
	public String createFindAllSQL(Class<?> clz) {
		Map<String,String> paraMap = getParams(clz);
		StringBuilder res = new StringBuilder(100);
		String params = paraMap.get("params");
		int endIndex = params.length()-1;
		System.out.println(clz);
		res.append("select ")
				.append(params.substring(1, endIndex))
					.append(" from ")
						.append(paraMap.get("tableName"));
		if(!clz.toString().contains("People")) {
			res.append(" order by student_score desc");
		}		
		return res.toString();
	}
	
	/* This is a helper method, which will be used in SQL find method 
	 * and SQL insert method.
	 * 
	 * 1. Initialize the annotation and get parameter's table name
	 * if there is no default table name, 
	 * then use the class name as table name
	 * 
	 * 2. Insert variables in table
	 * 		- use getDeclaredFileds 
	 * 		- get variables from different class by its annotation
	 * 		- ignore variables which are read-only
	 * 		- connect 2 StringBuilder
	 */
	private Map<String,String> getParams(Class<?> clz) {
		Map<String,String> res = new Hashtable<>();
		
		StringBuilder params = new StringBuilder(50);
		StringBuilder preParams = new StringBuilder(50);
		String tableName;
		
		SystemAnnotation clzAnno = clz.getAnnotation(SystemAnnotation.class);
		tableName = clzAnno.tableName();
		if(tableName == null) tableName = clz.getSimpleName();
		
		String prefix = "";
		params.append("(");
		preParams.append("(");
		Field[] fields = clz.getDeclaredFields();
		for(Field var:fields) {
			
			
			
			params.append(prefix);
			preParams.append(prefix);
			prefix = ",";
			
			SystemAnnotation fieldAnno = var.getAnnotation(SystemAnnotation.class);
			String paramName = fieldAnno.columnName();
			if(paramName == null) paramName = var.getName();
			
			params.append(paramName);
			preParams.append("?");
		}
		params.append(")");
		preParams.append(")");
		
		res.put("params", params.toString());
		res.put("preParams", preParams.toString());
		res.put("tableName",tableName);
		
		return res;
	}
}
