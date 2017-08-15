package com.potlocker.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.potlocker.dao.UserDAO;
import com.potlocker.exception.BadPhoneNumber;
import com.potlocker.exception.InvalidEmail;
import com.potlocker.exception.InvalidGender;
import com.potlocker.exception.UserIDDoesNotExist;
import com.potlocker.exception.UserNameAlreadyExists;
import com.potlocker.exception.UserNameDoesNotExists;
import com.potlocker.util.Validate;

public class UserDAOImpl implements UserDAO {

	Connection myConn;
	PreparedStatement myPreSmt;
	ResultSet myRs;
	Properties props;
	Validate validate;

	public UserDAOImpl() {
		props = new Properties();
		try {
			
			myConn = Singleton.getConnection1();

			System.out.println();
			System.out.println("Connection Succesfull.. :)");
			System.out.println("What do you wnat to do today?");

		} catch (FileNotFoundException e) {
			System.out.println("Specified file is not found!");
		} catch (IOException e) {
			System.out.println("Problem with input and output!!");
		} catch (SQLException e) {
			System.out.println("Connection Error!!");
		}

		validate = new Validate();
	}

	@Override
	public void createUser() {

		String[] userInput;
		String userName;
		char userGender;
		String userAddress;
		String userContact;
		String userEmail;

		Scanner scan = new Scanner(System.in);

		System.out.println("Please enter the following details:");
		System.out.println("Name, gender, Address, Contact Number, Email ID in this order");
		userInput = scan.nextLine().split(",");

		try {
			userName = userInput[0];
			validate.userNameValidation(userName, myConn);

			userGender = userInput[1].charAt(0);
			validate.userGengerValidation(userGender);

			userAddress = userInput[2];

			userContact = userInput[3];
			validate.userContactValidation(userContact);

			userEmail = userInput[4];
			validate.userEmailValidation(userEmail);

			myPreSmt = myConn.prepareStatement(
					"INSERT INTO potlocker.user (user_name, user_gender, user_address, user_contact_number, user_email_address) VALUES (?,?,?,?,?)");

			myPreSmt.setString(1, userName);
			myPreSmt.setString(2, String.valueOf(userGender));
			myPreSmt.setString(3, userAddress);
			myPreSmt.setString(4, userContact);
			myPreSmt.setString(5, userEmail);

			myPreSmt.executeUpdate();

			System.out.println("Welcome to potlocker!!");
			System.out.println("User added :)");

		} catch (UserNameAlreadyExists e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (InvalidGender e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (BadPhoneNumber e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (InvalidEmail e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (SQLException e) {
			System.out.println("Creation Error");
		}
	}

	@Override
	public void updateUser() {

		Scanner scan = new Scanner(System.in);
		int updateID;

		System.out.println("Enter the User ID you wanted to update: ");
		updateID = scan.nextInt();

		try {
			validate.userIDValidation(updateID, myConn);
		} catch (UserIDDoesNotExist e) {
			e.getErrorType();
			e.getErrorDescription();
		}
		System.out.println("what do you want to update??");
		System.out.println("---------UPDATION MENU----------");
		System.out.println("1. User Name");
		System.out.println("2. User Contact Number");
		System.out.println("3. User Email ID");
		System.out.println("4. User Address");
		System.out.println("enter your choice");
		int choice;
		choice = scan.nextInt();

		switch (choice) {
		case 1:
			try {
				System.out.println("Enter the new user name");
				String new_user_name;

				Scanner scan1 = new Scanner(System.in);

				new_user_name = scan1.nextLine();
				validate.userNameValidationUpdate(new_user_name, myConn);
				myPreSmt = myConn.prepareStatement("UPDATE potlocker.user SET user_name = ? WHERE user_id = ?;");
				myPreSmt.setString(1, new_user_name);
				myPreSmt.setInt(2, updateID);

				myPreSmt.executeUpdate();

				System.out.println("User Name succesfully updated!!");
			} catch (UserNameDoesNotExists e) {
				System.out.println(e.getErrorType());
				System.out.println(e.getErrorDescription());
			} catch (SQLException e) {
				System.out.println("User Name updation error!!");
			}
			break;
		case 2:
			try {
				System.out.println("Enter a new Contact Number");
				String new_contact;

				Scanner scan1 = new Scanner(System.in);

				new_contact = scan1.nextLine();
				validate.userContactValidation(new_contact);

				myPreSmt = myConn
						.prepareStatement("UPDATE potlocker.user SET user_contact_number = ? WHERE user_id = ?;");
				myPreSmt.setString(1, new_contact);
				myPreSmt.setInt(2, updateID);

				myPreSmt.executeUpdate();

				System.out.println("User Contact succesfully updated!!");
			} catch (BadPhoneNumber e) {
				System.out.println(e.getErrorType());
				System.out.println(e.getErrorDescription());
			} catch (SQLException e) {
				System.out.println("User Contact Updation error!!");
			}
			break;
		case 3:
			System.out.println("enter a new Email ID");
			String new_email;

			Scanner scan1 = new Scanner(System.in);

			new_email = scan1.nextLine();
			try {
				validate.userEmailValidation(new_email);

				myPreSmt = myConn
						.prepareStatement("UPDATE potlocker.user SET user_email_address = ? WHERE user_id = ?;");
				myPreSmt.setString(1, new_email);
				myPreSmt.setInt(2, updateID);

				myPreSmt.executeUpdate();

				System.out.println("Email ID updated Sucessfully");
			} catch (InvalidEmail e) {
				System.out.println(e.getErrorType());
				System.out.println(e.getErrorDescription());
			} catch (SQLException e) {
				System.out.println("Email ID updation error!!!");
			}
			break;
		case 4:
			System.out.println("enter the new Address");
			String new_address;

			Scanner scan2 = new Scanner(System.in);
			new_address = scan2.nextLine();

			try {
				myPreSmt = myConn.prepareStatement("UPDATE potlocker.user SET user_address = ? WHERE user_id = ?;");
				myPreSmt.setString(1, new_address);
				myPreSmt.setInt(2, updateID);

				myPreSmt.executeUpdate();

				System.out.println("Address updated Sucessfully");
			} catch (SQLException e) {
				System.out.println("user Address updation error!!");
			}
			break;
		default:
			System.out.println("enter a valid choice!!");

		}
	}

	@Override
	public void deleteUser() {

		Scanner scan = new Scanner(System.in);
		int delID;

		System.out.println("Enter the User ID you wanted to delete: ");
		delID = scan.nextInt();

		try {
			validate.userIDValidation(delID, myConn);
			myPreSmt = myConn.prepareStatement("DELETE from potlocker.user WHERE user_id = ?");
			myPreSmt.setInt(1, delID);

			myPreSmt.executeUpdate();

			System.out.println("Successfully deleted the record!!");

		} catch (UserIDDoesNotExist e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (SQLException e) {
			System.out.println("Deletion Error!!");
		}
	}

	@Override
	public void searchUser() {

		try {
			Scanner scan10 = new Scanner(System.in);
			String searchName;

			System.out.println("Enter the name of the user you want to search: ");
			searchName = scan10.nextLine();
			validate.userNameValidationUpdate(searchName, myConn);

			myPreSmt = myConn.prepareStatement("SELECT * from potlocker.user where user_name = ?");
			myPreSmt.setString(1, searchName);

			myPreSmt.setInt(1, 1001);
			ResultSet result = myPreSmt
					.executeQuery("select * from potlocker.user where user_name = " + "'" + searchName + "'");

			System.out.println("Details of the searched User:");
			System.out.println();

			while (result.next()) {
				System.out.println("Phone Number: " + result.getString("user_contact_number"));
				System.out.println("Email ID: " + result.getString("user_email_address"));
			}
			System.out.println();
			System.out.println("Thanks for using Potlocker App :)");

		} catch (UserNameDoesNotExists e) {
			System.out.println(e.getErrorType());
			System.out.println(e.getErrorDescription());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
