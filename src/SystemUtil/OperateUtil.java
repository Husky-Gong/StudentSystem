package SystemUtil;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.BaseDao;
import OperateHelper.InsertHelper;
import OperateHelper.logHelper;
import SystemClass.People;
import SystemClass.Student;

/*
 * This class contains methods which support the operate table to run.
 * Specifically, it has "register", "login", "print all", "search student", "search student by name" methods.
 * 
 * 1. Register: 	Ask for user's information(username and password) and check it with the user table list.
 * 						if not, register successfully. Otherwise, ask again.
 * 
 * 2. Login: 		Ask for user's information(username and password) and check whether the user list has the username
 * 						if cannot find in the user list, ask input again.
 * 
 * 3. Print All: 	After user login successfully, student information will be printed automatically by descending order.
 * 
 * 4. Print result by descending order: After user login successfully, searching system will provide search by name function
 * 										Then user will be asked for specific name which he wants to search.
 * 
 * Preparation:
 * 		1. student hash table: get data from student data base and to assist search student information.
 * 		2. user hash table: get data from people data base and to assist to update user data base after new user registered.
 */
public class OperateUtil<T> {
	Scanner input = new Scanner(System.in);

	public static Map<String, Student> studentData = new Hashtable<>();
	public static Map<String, People> userData = new Hashtable<>();
	public static List<String> orderName = new ArrayList<>();
	public static boolean flag = false;

	
	/*
	 * Initialize function is to help get date from the database and store information in hashtable
	 */
	@SuppressWarnings("unchecked")
	public void initialize() throws Exception {
		BaseDao<T> initialDao = new BaseDao<T>();
		DaoHelper.FindHelper findHelp = new DaoHelper.FindHelper();
		userData = (Map<String, People>) initialDao.findAllEntity((T) findHelp.getUser());
		studentData = (Map<String, Student>) initialDao.findAllEntity((T) findHelp.getStudent());
	}

	
	/*
	 * 1. Ask for new user information 2. Check whether the new userName exists in
	 * the userData(hash table) by key 3. if exists, return back 4. Otherwise, add
	 * the new user into the hash table and utilize the insertEntity to update the
	 * user database
	 */
	@SuppressWarnings("unchecked")
	public void register() throws Exception {
		System.out.println("Welcome our new user!");
		InsertHelper testInsert = new InsertHelper();
		People newUser = testInsert.addNewUser();
		BaseDao<T> testDao = new BaseDao<T>();

		while (userData.containsKey(newUser.getUsername())) {
			System.out.println("This username has existed, please change another one!");
			newUser = testInsert.addNewUser();
		}

		userData.put(newUser.getUsername(), newUser);
		int i = testDao.insertEntity((T) newUser);
		if (i == 1)
			System.out.println("You have registered successfully!");
	}

	/*
	 * This function is used to help user log in After logging in, user can continue
	 * shopping and change its password or name. One flag is needed to identify
	 * whether this user has logged in.
	 */
	public People login() {
		System.out.println("----Log in----");
		logHelper log = new logHelper();
		Map<String, String> map = log.userLog();

		if (userData.containsKey(map.get("username"))
				&& userData.get(map.get("username")).getPassword().equals(map.get("password"))) {
			flag = true;
			System.out.println("You login successfully!");
			return userData.get(map.get("username"));
		} else {
			System.out.println("Wrong username or password. Please try again!");
			return null;
		}
	}
}
