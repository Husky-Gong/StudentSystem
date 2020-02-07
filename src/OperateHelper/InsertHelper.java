package OperateHelper;

import java.util.Scanner;

import SystemClass.People;
import SystemClass.Student;
import SystemUtil.OperateUtil;

/*
 * This class will act as a helper to generate insert sql.
 * In this class, it has:
 * 
 * 1. One helper to create flower object which will be insert into the table
 * 	Specifically, this the insert function will only be called when the 
 * 	flower does NOT exist in the table. In other cases, the manager should call
 * 	the modify method in stead.
 * 
 * 2. One helper to create user object and insert it into the user table
 * 		this is used when a NEW user registers.
 */
public class InsertHelper {
	@SuppressWarnings("resource")
	private People userInsertHelper() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input your username:");
		String userName = input.next();
		System.out.println("Input your password:");
		String passWord = input.next();
		System.out.println("What's your name:");
		String name = input.next();
		People newUser = new People(userName, passWord, name);
		return newUser;
	}
	
	public People addNewUser() {
		return userInsertHelper();
	}
	
	@SuppressWarnings("resource")
	private Student stuInsertHelper() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input student's name:");
		String name = input.next();
		System.out.println("Input student's sex:");
		String sex = input.next();
		System.out.println("Input student's age:");
		int age = input.nextInt();
		System.out.println("Input student's score:");
		double score = input.nextDouble();
		Student newStu = new Student(0, name, sex, age, score);
		//Update student Hashtable
		OperateUtil.studentData.put(name, newStu);
		return newStu;
	}
	
	public Student addNewStu() {
		return stuInsertHelper();
	}
}
