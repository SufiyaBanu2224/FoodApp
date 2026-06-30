<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Food Gallery | Menu</title>

<style>
*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:"Segoe UI",Arial,sans-serif;
}
:root{
--green:#264d3b;
--gold:#f5b84b;
--orange:#e85d2a;
--light:#f6fbf5;
--text:#333;
--gray:#666;
}

body{
background:linear-gradient(to right,#fffdf8,#eef8ee);
}

.navbar{
background:#264d3b;
padding:18px 8%;
display:flex;
justify-content:space-between;
align-items:center;
position:sticky;
top:0;
z-index:100;
}

.logo{
font-size:30px;
font-weight:bold;
color:#fff;
}

.logo span{
color:var(--gold);
}

.navbar ul{
display:flex;
list-style:none;
gap:30px;
}

.navbar a{
text-decoration:none;
color:white;
font-weight:600;
}

.hero{
height:260px;
background:linear-gradient(rgba(0,0,0,.55),rgba(0,0,0,.55)),
url("Images/tandoori.jpg");
background-size:cover;
background-position:center;
display:flex;
flex-direction:column;
justify-content:center;
align-items:center;
color:#fff;
text-align:center;
}

.hero h1{
font-size:50px;
margin-bottom:10px;
}

.hero p{
font-size:18px;
}

.menu-container{
width:90%;
max-width:1300px;
margin:40px auto;
display:grid;
grid-template-columns:repeat(auto-fit,minmax(340px,1fr));
gap:30px;
}

.card{
background:#fff;
border-radius:18px;
overflow:hidden;
box-shadow:0 10px 30px rgba(0,0,0,.15);
display:flex;
flex-direction:column;
transition:.3s;
}

.card:hover{
transform:translateY(-8px);
}

.image-wrap{
position:relative;
width:100%;
aspect-ratio:16/9;
overflow:hidden;
background:#f5f5f5;
}

.image-wrap img{
width:100%;
height:100%;
object-fit:cover;
object-position:center;
display:block;
transition:.4s;
}

.card:hover img{
transform:scale(1.05);
}

.price{
position:absolute;
right:15px;
bottom:15px;
background:var(--gold);
padding:10px 18px;
border-radius:30px;
font-weight:bold;
}

.content{
padding:20px;
display:flex;
flex-direction:column;
flex:1;
}

.content h2{
font-size:30px;
color:var(--green);
margin-bottom:10px;
}

.description{
color:var(--gray);
line-height:1.6;
min-height:60px;
margin-bottom:18px;
}

.badge{
display:inline-block;
padding:8px 15px;
border-radius:25px;
background:#dff1da;
color:#21632d;
font-weight:bold;
margin-bottom:20px;
}

.off{
background:#ffe0e0;
color:#b71c1c;
}

.btn{
margin-top:auto;
padding:14px;
border:none;
border-radius:30px;
background:linear-gradient(135deg,var(--orange),var(--gold));
color:white;
font-size:17px;
font-weight:bold;
cursor:pointer;
}

.btn:disabled{
background:gray;
cursor:not-allowed;
}

.empty{
grid-column:1/-1;
text-align:center;
padding:60px;
background:#fff;
border-radius:15px;
}

footer{
background:#264d3b;
color:#fff;
text-align:center;
padding:20px;
margin-top:40px;
}

@media(max-width:768px){
.hero h1{font-size:34px;}
.menu-container{grid-template-columns:1fr;}
}
</style>
</head>

<body>

<nav class="navbar">
<div class="logo">Food<span>Gallery</span></div>
<ul>
<li><a href="home">Home</a></li>
<li><a href="cart">Cart</a></li>
<li><a href="login.html">Login</a></li>
</ul>
</nav>

<section class="hero">
<h1>Choose Your Favourite Food</h1>
<p>Freshly prepared dishes with quick delivery.</p>
</section>

<section class="menu-container">

<%
List<Menu> allMenuByRestaurant=(List<Menu>)request.getAttribute("allMenuByRestaurant");
Integer restaurantId=(Integer)request.getAttribute("restaurantId");

if(allMenuByRestaurant!=null && !allMenuByRestaurant.isEmpty()){
for(Menu menu:allMenuByRestaurant){
%>

<div class="card">

<div class="image-wrap">
<img src="<%=menu.getImagePath()%>" alt="<%=menu.getItemName()%>">
<div class="price">₹ <%=menu.getPrice()%></div>
</div>

<div class="content">

<h2><%=menu.getItemName()%></h2>

<p class="description">
<%=menu.getDescription()%>
</p>

<span class="badge <%=menu.isAvailable() ? "" : "off"%>">
<%=menu.isAvailable() ? "Available Today" : "Unavailable"%>
</span>

<form action="cart" method="post">

<input type="hidden" name="action" value="add">
<input type="hidden" name="menuId" value="<%=menu.getMenuId()%>">
<input type="hidden" name="restaurantId"
value="<%=restaurantId!=null?restaurantId:menu.getRestaurantId()%>">

<button class="btn"
type="submit"
<%=menu.isAvailable() ? "" : "disabled"%>>
Add To Cart
</button>

</form>

</div>

</div>

<%
}
}else{
%>

<div class="empty">
<h2>No Menu Items Available</h2>
<p>Please choose another restaurant.</p>
</div>

<%
}
%>

</section>

<footer>
&copy; 2026 Food Gallery | Delicious Food Delivered Fast
</footer>

</body>
</html>
