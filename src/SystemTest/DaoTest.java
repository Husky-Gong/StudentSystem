package SystemTest;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

import DAO.BaseDao;
import OperateHelper.InsertHelper;
import SystemClass.People;
import SystemClass.Student;
import SystemUtil.OperateUtil;

public class DaoTest {
	/*
	 * ------------INSERT-------------
	 * People works!
	 */
	@SuppressWarnings("unchecked")
	@Test
	public <T> void testInsertEntity() throws Exception{
		System.out.println("You are now testing insert People entity......");
		
		InsertHelper testInsert = new InsertHelper();
		People newUser = testInsert.addNewUser();
		
		BaseDao<T> testDao = new BaseDao<T>();
		int i = testDao.insertEntity((T) newUser);
		
		System.out.println(i);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void testInsertEntity2() throws Exception {
		System.out.println("You are now adding new student......");
		
		InsertHelper testInsert = new InsertHelper();
		Student newStu = testInsert.addNewStu();
		
		BaseDao<T> testDao = new BaseDao<T>();
		int i = testDao.insertEntity((T) newStu);
		
		System.out.println(i);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public <T> void testFindSpecificEntity() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		System.out.println("You are now testing find specific students function......");
		
		BaseDao<T> testDao = new BaseDao<T>();
		Map<String, Student> map = new Hashtable<>();
		Student stu = new Student();
		map = (Map<String, Student>) testDao.findSpecificEntity((T) stu);
		for(String name: OperateUtil.orderName) {
			System.out.println(map.get(name));
		}
	}
}
