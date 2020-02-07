package OperateSystem;

import java.util.Scanner;

import org.junit.Test;

import SystemClass.People;
import SystemTest.DaoTest;
import SystemUtil.OperateUtil;

public class OperateTable {
	static {
		
	}
	
	@SuppressWarnings({ "resource", "rawtypes","unused" })
	@Test
	public <T> void table() throws Exception {
		Scanner input = new Scanner(System.in);
		OperateUtil opUtil = new OperateUtil();
		People user = null;
		
		while(true) {
			// First initialize the database.
			opUtil.initialize();
			
			System.out.println("\t\t------Welcome to Flower System------\n Please make your choice:"
					+ "\t\t\t\t1. Regist"
					+ "\t\t\t\t2. Login"
					+ "\t\t\t\t3. Exit");
			int choice = input.nextInt();
			switch(choice) {
				case 1:
					opUtil.register();
					break;
				case 2:
					user = opUtil.login();
					break;
				case 3:
					OperateUtil.flag=false;
					System.out.println("See you!");
					System.exit(0);
					break;
			}
			
			while(OperateUtil.flag) {
				opUtil.initialize();
				System.out.println("\t\t------Welcome to Flower System------\n Please make your choice:"
						+ "\t\t\t1. Get All Student."
						+ "\t\t\t2. Add New Student Information."
						+ "\t\t\t3. Search Students with Specific Name."
						+ "\t\t\t4. Exit.");
				//
				int decide = input.nextInt();
				
				switch(decide) {
					/*
					 * Case 1 is to Print All Student Information. 
					 */
					case 1:
						if(OperateUtil.studentData.isEmpty()) {
							System.out.println("Student Table is Empty!");
							continue;
						}
						else {
							for(String name:OperateUtil.orderName) {
								System.out.println(name);
								System.out.println(OperateUtil.studentData.get(name));
							}
						}
						continue;
					/*
					 * Case 2 is to add new student information
					 * after adding a new student information, console will print all student information again
					 */
					case 2:
						DaoTest addStu = new DaoTest();
						addStu.testInsertEntity2();
						opUtil.initialize();
						for(String name:OperateUtil.orderName) {
							System.out.println(OperateUtil.studentData.get(name));
						}
						continue;
					
						/*
						 * In this case, user can search students with specific name(full or part of it)
						 */
					case 3:
						DaoTest searchStu = new DaoTest();
						searchStu.testFindSpecificEntity();
						continue;
					
						/*
						 * In case 4, user can exit program.
						 */
					case 4:
						OperateUtil.flag=false;
						System.out.println("See you!");
						System.exit(0);
						break;
				}
			}
			
		}
	}
}
