package com.food.DAOimplment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.DAO.RestaurantDAO;
import com.food.model.Restaurant;

public class RestaurantDAOImp implements RestaurantDAO {
	Connection connection=null;
	public RestaurantDAOImp(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tap_food","root","Imjeeva@888");
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public void addRestaurant(Restaurant restaurant) {
		String query="INSERT into `restaurant` (`Name`,`CuisineType`,`DeliveryTime`,`Address`,`Rating`,`IsActive`) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, restaurant.getName());
			ps.setString(2, restaurant.getCuisineType());
			ps.setString(3, restaurant.getDeliveryTime());
			ps.setString(4, restaurant.getAddress());
			ps.setDouble(5, restaurant.getRating());
			ps.setBoolean(6,restaurant.getisActive());
			int a=ps.executeUpdate();
			System.out.println(a+"Yes the data stored.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	
	public Restaurant getRestaurant(int RestaurantId) {
		String query="SELECT * from restaurant WHERE restaurantID=? ";
		Restaurant rest=null;
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, RestaurantId);
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				 rest=print(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rest;
	}
	
	




	public void updateRestaurant(Restaurant restaurant) {
		String query="UPDATE `restaurant` set `name`=?,`cuisineType`=?,`deliveryTime`=?,`address`=?,`rating`=?,`isActive`=? WHERE `restaurantId`=?  ";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			
			ps.setString(1,restaurant.getName());
			ps.setString(2, restaurant.getCuisineType());
			ps.setString(3,restaurant.getDeliveryTime());
			ps.setString(4,restaurant.getAddress());
			ps.setDouble(5,restaurant.getRating());
			ps.setBoolean(6,restaurant.getisActive());
			ps.setInt(7,restaurant.getRestaurantId());
			int x=ps.executeUpdate();
			System.out.println(x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteRestaurant(int restaurantId) {
		String query="DELETE from `restaurant` WHERE `restaurantId`=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, restaurantId);
			int n=ps.executeUpdate();
			System.out.println(n);
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

	public List<Restaurant> getAllRestaurants() {
		String query="SELECT * from `restaurant`";
		 ArrayList <Restaurant> al=new ArrayList<Restaurant>();
		 Restaurant restaurant=null;
		try {
			Statement stm=connection.createStatement();
			ResultSet res=stm.executeQuery(query);
			
			while(res.next()) {
				restaurant=print(res);
				al.add(restaurant);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
	


	private Restaurant print(ResultSet res) throws Exception {
		Restaurant rest=new Restaurant();
		rest.setRestaurantId(res.getInt("restaurantId"));
		rest.setName(res.getString("name"));
		rest.setCuisineType(res.getString("cuisineType"));
		rest.setDeliveryTime(res.getString("deliveryTime"));
		rest.setAddress(res.getString("address"));
		//rest.setAdminUserId(res.getInt("adminUserId"));
		rest.setRating(res.getDouble("rating"));
		rest.setActive(res.getBoolean("rating"));
		rest.setImgPath(res.getString("ImagePath")); 
		return rest;
		
		
	}

}
