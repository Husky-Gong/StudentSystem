package SystemTest;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Test;

import SystemClass.People;
import SystemClass.Student;
import SystemUtil.OperateUtil;

public class OperateTest {
	@Test
	public <T> void initialTest() throws Exception {
		System.out.println("You are now testing .....");
		OperateUtil<T> opUtil = new OperateUtil<T>();
		opUtil.initialize();
		System.out.println("Your are now testing intialize function.....");
		Map<String, People> studentMap = new Hashtable<>();
		studentMap = OperateUtil.userData;
		for(String name:OperateUtil.orderName) {
			System.out.println(studentMap.get(name));
		}
	}
}