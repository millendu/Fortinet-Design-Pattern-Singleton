package com.potlocker.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.potlocker.exception.BadPhoneNumber;
import com.potlocker.exception.InvalidEmail;
import com.potlocker.exception.InvalidGender;
import com.potlocker.exception.UserIDDoesNotExist;
import com.potlocker.exception.UserNameAlreadyExists;
import com.potlocker.exception.UserNameDoesNotExists;

public class Validate {

	PreparedStatement ps;

	public void userNameValidation(String userName, Connection con) throws UserNameAlreadyExists {
		try {

			ps = con.prepareStatement("SELECT * from potlocker.user WHERE user_name = ?");
			ps.setString(1, userName);

			ResultSet result = ps.executeQuery();
			if (result.next()) {
				UserNameAlreadyExists badUserName = new UserNameAlreadyExists();

				badUserName.setErrorType("BAD USER Name");
				badUserName.setErrorDescription("User Name already exists!! Try Again...");

				throw badUserName;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void userNameValidationUpdate(String userNameUpdate, Connection con) throws UserNameDoesNotExists
	{
		try {
			ps = con.prepareStatement("SELECT * from potlocker.user where user_name = ?");
			ps.setString(1,userNameUpdate);
			
			boolean result = ps.execute();
			
			if(!result)
			{
				UserNameDoesNotExists noUserName = new UserNameDoesNotExists();
				
				noUserName.setErrorType("User Name does not exist");
				noUserName.setErrorDescription("Please enter a valid user name");
				
				throw noUserName;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void userGengerValidation(char g) throws InvalidGender {
		if (!(g == 'M' || g == 'F' || g == 'm' || g == 'f')) {
			InvalidGender badGender = new InvalidGender();
			badGender.setErrorType("Inalid Gender");
			badGender.setErrorDescription("Please enter a valid gender");

			throw badGender;
		}
	}

	public void userContactValidation(String phone) throws BadPhoneNumber {
		if (phone.length() > 10) {
			BadPhoneNumber badContact = new BadPhoneNumber();
			badContact.setErrorType("Invalid phone number");
			badContact.setErrorDescription("Please enter a valid phone number");

			throw badContact;
		}

	}

	public void userEmailValidation(String email) throws InvalidEmail {
		if (!(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com"))) {
			InvalidEmail badEmail = new InvalidEmail();
			badEmail.setErrorType("Invalid email ID");
			badEmail.setErrorDescription("Please enter a valid email Address!!");

			throw badEmail;
		}

	}

	public void userIDValidation(int id, Connection con) throws UserIDDoesNotExist {
		try {

			ps = con.prepareStatement("SELECT * from potlocker.user WHERE user_id = ?");
			ps.setInt(1, id);

			boolean result = ps.execute();

			if (!result) {
				UserIDDoesNotExist badID = new UserIDDoesNotExist();

				badID.setErrorType("BAD USR ID");
				badID.setErrorDescription("User ID does not exist!! Enter a valid User ID...");

				throw badID;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
