<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.List, com.food.model.User, com.food.model.Restaurant, com.food.DAOimplment.RestaurantDAOImp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Restaurant Page</title>
<link rel="stylesheet" href="CSS/restaurant.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
</head>
<body>
	<header class="header">
		<nav>
			<a href="index.jsp"> <!-- <div class="logo animate__animated animate__bounce"> -->
				
				</div>
			</a>
			<ul>
				<%
				User user = (User) session.getAttribute("loggedInUser");
				if (user != null) {
				%>
				<li><a>Welcome, <%=user.getUserName()%>!
				</a></li>
				<li><a href="restaurant">Restaurants</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="orderHistory">Order History</a></li>
				<li><a href="cart" id="cart-button" class="cart"> <i
						class="fas fa-shopping-cart"></i> Cart <span id="cart-count">0</span>
						<li><a href="logout" class="login-btn">Logout</a></li> <%
 } else {
 %>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="#">About Us</a></li>
				<li><a href="aboutme.html">Contact</a></li>
				<li><a href="login.jsp" class="login-btn">Login</a></li>
				<li><a href="signup.jsp" class="signup-btn">SignUp</a></li> <%
 }
 %>
			</ul>
		</nav>
	</header>
	<div class="sticky-bar">
		<label for="sort">Sort by:</label> <select id="sort"
			class="form-control d-inline w-auto">
			<option value="relevance">Relevance</option>
			<option value="rating">Rating</option>
			<option value="delivery-time">Delivery Time</option>
			<option value="price">Price</option>
		</select> <label for="filter">Filter by:</label> <input type="checkbox"
			id="vegetarian" name="vegetarian" value="vegetarian">
		Vegetarian <input type="checkbox" id="vegan" name="vegan"
			value="vegan"> Non Vegetarian <input type="checkbox" id="gluten-free"
			name="gluten-free" value="gluten-free"> Gluten Free
	</div>

	<h2 class="name">Restaurants Near to You</h2>
	<section class="restaurants"
		style="background-color: rgb(255, 253, 253)">


		<div class="restaurant-container">
			<%
			@SuppressWarnings("unchecked")
			List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");
			System.out.println(restaurantList);

			if (restaurantList != null) {
				for (Restaurant restaurant : restaurantList) {
			%>
			<a href="menu?restaurantId=<%=restaurant.getRestaurantId()%>">
				<div class="restaurant-item">
					<img class="im" src=images/<%=restaurant.getImgPath()%>>
					<h3><%=restaurant.getName()%></h3>
					<p><%=restaurant.getCuisineType()%></p>
					<span><%=restaurant.getDeliveryTime()%> mins</span>

				</div>
			</a>
			<%
			}
			} else {
			%>
			<p>No restaurants available at the moment.</p>
			<%
			}
			%>
		</div>




		<!-- Repeat for other restaurants -->

	</section>
	<!-- Footer -->
	<footer>
		<div class="footer-content">
			<p>&copy; 2024 Tap Foods.</p>
			<ul>
				<li><a href="#">Privacy Policy</a></li>
				<li><a href="#">Terms of Service</a></li>
				<li><a href="#">Contact Us</a></li>
			</ul>
		</div>
	</footer>
</body>
</html>