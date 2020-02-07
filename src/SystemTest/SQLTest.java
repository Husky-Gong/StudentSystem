package SystemTest;

import org.junit.Test;

import SystemClass.People;
import SystemClass.Student;
import SystemUtil.MakeSqlUtil;

public class SQLTest {
	MakeSqlUtil sql = new MakeSqlUtil();
	
	@Test
	public void testInsertFunction() {
		String insertSQL = sql.createInsertSql(People.class);
		System.out.println(insertSQL);
	}
	
	@Test
	public void testInsertFunction2() {
		String insertSQL = sql.createInsertSql(Student.class);
		System.out.println(insertSQL);
	}
	
	@Test
	public void testFindFunction() {
		String findSql = sql.createFindSQL(Student.class);
		System.out.println(findSql);
	}
	
	@Test
	public void testFindAllFunction() {
		String findSql = sql.createFindAllSQL(Student.class);
		System.out.println(findSql);
	}
	
	@Test
	public void testFindAllFunction2() {
		String findSql = sql.createFindAllSQL(People.class);
		System.out.println(findSql);
	}
}
