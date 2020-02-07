package DaoHelper;

import SystemClass.Student;
import SystemClass.People;

public class FindHelper {
	private Student stuFindHelper() {
		Student newStudent = new Student();
		return newStudent;
	}
	
	public Student getStudent() {
		return stuFindHelper();
	}
	
	private People userFindHelper() {
		People newUser = new People();
		return newUser;
	}
	
	public People getUser() {
		return userFindHelper();
	}
}