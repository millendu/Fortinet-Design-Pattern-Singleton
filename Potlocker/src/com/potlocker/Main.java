package com.potlocker;

import java.util.Scanner;

import com.potlocker.dao.UserDAO;
import com.potlocker.dao.impl.UserDAOImpl;

public class Main {
	
	public static void main(String[] args) {
		
		int choice;
		Scanner scan = new Scanner(System.in);
		
		UserDAO userDAO = new UserDAOImpl();
		
		System.out.println("=============MENU===========");
		System.out.println("1) Create");
		System.out.println("2) Update");
		System.out.println("3) Delete");
		System.out.println("4) Search");
		System.out.println("Enter your choice: ");
		choice=scan.nextInt();
		
		switch(choice)
		{
		case 1:
			userDAO.createUser();
			break;
		case 2:
			userDAO.updateUser();
			break;
		case 3:
			userDAO.deleteUser();
			break;
		case 4:
			userDAO.searchUser();
			break;
		default:
			System.out.println("Enter a valid choice!!!");
		}
		
	}

}
