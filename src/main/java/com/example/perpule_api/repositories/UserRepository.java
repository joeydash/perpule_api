package com.example.perpule_api.repositories;

import com.example.perpule_api.helpers.RandomString;
import com.example.perpule_api.modals.UserDBModal;
import com.example.perpule_api.modals.UserInputDataModal;
import com.example.perpule_api.modals.UserOutputDataModal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
	private Connection connection = null;
	
	public UserRepository() {
		String url = "jdbc:mysql://localhost:3306/perpule";
		String username = "root";
		String password = "joeydash";
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> getAllUsers() {
		List<String> list = new ArrayList<>();
		String sql_query = "SELECT * FROM users";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql_query);
			while(resultSet.next()){
				list.add(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public UserOutputDataModal createUser(UserInputDataModal userInputputDataModal) {
		UserOutputDataModal userOutputDataModal = new UserOutputDataModal();
		UserDBModal userDBModal = new UserDBModal();
		userDBModal.set_id(RandomString.hashString(userInputputDataModal.getUsername()));
		userDBModal.setUsername(userInputputDataModal.getUsername());
		userDBModal.setRandom_string(RandomString.randomString(8));
		userDBModal.setHashed_password(RandomString.hash_password(userInputputDataModal.getPassword(),userDBModal.getRandom_string()));
		userDBModal.setAuth_token(RandomString.randomString(16));
		
		String sql_query = "INSERT INTO `users` (`_id`, `username`, `random_string`, `hashed_password`, `auth_token`) VALUES ('"+userDBModal.get_id()+"', '"+userDBModal.getUsername()+"', '"+userDBModal.getRandom_string()+"', '"+userDBModal.getHashed_password()+"', '"+userDBModal.getAuth_token()+"');";
		try {
			PreparedStatement statement = connection.prepareStatement(sql_query);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userOutputDataModal.setUser_name(userDBModal.getUsername());
		userOutputDataModal.setAuth_token(userDBModal.getAuth_token());
		return userOutputDataModal;
	}
	public UserOutputDataModal checkUser(UserInputDataModal userInputputDataModal) {
		UserOutputDataModal userOutputDataModal = new UserOutputDataModal();
		String sql_query = "SELECT * FROM users WHERE username= '"+userInputputDataModal.getUsername()+"'";
		try { 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql_query);
			if (!resultSet.next()){
					System.out.println("No username found");
				}else {
					if(RandomString.hash_password(userInputputDataModal.getPassword(),resultSet.getString(3)).equals(resultSet.getString(4))) {
						userOutputDataModal.setUser_name(resultSet.getString(2));
						userOutputDataModal.setAuth_token(resultSet.getString(5));
					}else {
						System.out.println("password not matched");
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userOutputDataModal;
	}
	public boolean isAuthOK(String auth_key) {
		boolean isUserAuthCorrect = false;
		String sql_query = "SELECT * FROM users WHERE auth_key= '"+auth_key+"'";
		try { 
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql_query);
			if (!resultSet.next()){
					System.out.println("No auth_key found");
				}else {
					isUserAuthCorrect = true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isUserAuthCorrect;
	}
}
