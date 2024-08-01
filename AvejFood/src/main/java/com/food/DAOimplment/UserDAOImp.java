package com.food.DAOimplment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.UserDAO;
import com.food.model.User;

public class UserDAOImp implements UserDAO{
	Connection connection;
	public UserDAOImp() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_food","root","Imjeeva@888");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
/*------------------------------------ADD USER----------------------------------------------*/
	public int  addUser(User user)  {
		String query="INSERT into `user`(`UserName`,`Password`,`Email`,`Address`,`Role`) values(?,?,?,?,?)";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getRole());
			return ps.executeUpdate();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
			
		}
		
		
	}
	/*------------------------------------GET USER----------------------------------------------*/

	public User getUser(String email)  {
		String query="SELECT * from user WHERE email=?";
		User user=null;
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet res=ps.executeQuery();
			
			if(res.next()){
				
				
				user=print(res);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return user;

	}


	/*------------------------------------UPDATE USER----------------------------------------------*/

	public void updateUser(User user) {
		String query="UPDATE `user` set UserName=?,Password=?,Email=?,Address=?,Role=? WHERE UserID=? ";
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getRole());
			ps.setInt(6,user.getUserID());
			
			int x=ps.executeUpdate();
			System.out.println(x);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	

	/*------------------------------------DELETE USER----------------------------------------------*/
	public void deleteUser(int  userID) {
		String query="DELETE from user WHERE userID=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, userID);
			int n=ps.executeUpdate();
			System.out.println(n);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/*------------------------------------GET ALL USERS----------------------------------------------*/


	public List<User> getAllUser() {
		String query="SELECT * from USER";
		ArrayList<User> ll= new ArrayList<User> ();
		try {
			Statement stm=connection.createStatement();
			ResultSet res=stm.executeQuery(query);
			while(res.next()) {
				ll.add(print(res));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	/*------------------------------------CALL THE METHOD-----------------------------------------------*/

	private User print(ResultSet res) throws Exception {
		User user=new User();
		user.setUserID(res.getInt("UserID"));
		user.setUserName(res.getString("UserName"));
		user.setPassword(res.getString("Password"));
		user.setEmail(res.getString("Email"));
		user.setAddress(res.getString("Address"));
		user.setRole(res.getString("Role"));
		return user;
		
		
	}

}
