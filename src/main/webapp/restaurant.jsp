<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap.model.Restaurant"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Food Gallery | Restaurants</title>
<style>
:root{--leaf:#244632;--leaf-2:#356747;--mint:#e9f4e7;--paper:#fffaf1;--ink:#18251c;--muted:#637066;--tomato:#d94f2f;--gold:#f5b84b;--line:#dde6d6;--shadow:0 18px 45px rgba(31,51,38,.14)}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif}
body{min-height:100vh;background:radial-gradient(circle at top left,rgba(245,184,75,.24),transparent 32%),linear-gradient(135deg,#fffaf1 0%,#eef7e8 55%,#fff6e6 100%);color:var(--ink)}
.navbar{position:sticky;top:0;z-index:10;display:flex;justify-content:space-between;align-items:center;gap:22px;padding:16px 7%;background:rgba(36,70,50,.88);backdrop-filter:blur(14px);box-shadow:0 8px 30px rgba(16,34,23,.18);animation:slideDown .55s ease}
.logo{color:white;font-size:28px;font-weight:800;letter-spacing:.2px}.logo span{color:var(--gold)}
.nav-links{list-style:none;display:flex;gap:10px;align-items:center;flex-wrap:wrap}.nav-links a{text-decoration:none;color:white;font-size:15px;font-weight:700;padding:10px 14px;border-radius:999px;transition:.25s}.nav-links a:hover{background:rgba(255,255,255,.15);transform:translateY(-2px)}.signup{background:white;color:var(--leaf)!important}.signup:hover{background:#fff1d2!important}
.hero{position:relative;overflow:hidden;padding:58px 7% 46px;background:linear-gradient(120deg,rgba(36,70,50,.98),rgba(52,111,69,.92)),url("Images/spiceGarden.jpg");background-size:cover;background-position:center;color:white}
.hero:after{content:"";position:absolute;right:-90px;bottom:-120px;width:330px;height:330px;border-radius:50%;background:rgba(245,184,75,.2);filter:blur(2px);animation:pulse 5s ease-in-out infinite}
.hero-inner{position:relative;z-index:1;max-width:980px}.eyebrow{display:inline-flex;align-items:center;gap:8px;padding:7px 12px;border:1px solid rgba(255,255,255,.32);border-radius:999px;background:rgba(255,255,255,.12);font-size:13px;font-weight:700;animation:fadeUp .6s ease both}
.hero h1{font-size:clamp(36px,6vw,68px);line-height:1.02;margin:18px 0 14px;max-width:780px;animation:fadeUp .7s ease .08s both}.hero p{font-size:18px;max-width:590px;color:#edf7ea;line-height:1.6;animation:fadeUp .75s ease .16s both}
.hero-actions{display:flex;gap:14px;margin-top:26px;flex-wrap:wrap;animation:fadeUp .8s ease .24s both}.hero-btn{display:inline-block;text-decoration:none;border-radius:999px;padding:13px 18px;font-weight:800;transition:.25s}.hero-btn.primary{background:var(--gold);color:#2b2110}.hero-btn.ghost{border:1px solid rgba(255,255,255,.35);color:white}.hero-btn:hover{transform:translateY(-3px);box-shadow:0 12px 25px rgba(0,0,0,.2)}
.section-head{width:min(1140px,90%);margin:34px auto 18px;display:flex;justify-content:space-between;align-items:end;gap:20px}.section-head h2{font-size:28px;color:var(--leaf)}.section-head p{color:var(--muted);margin-top:6px}
.restaurant-container{width:min(1140px,90%);margin:0 auto 46px;display:grid;grid-template-columns:repeat(auto-fit,minmax(280px,1fr));gap:24px}
.restaurant-link{text-decoration:none;color:inherit;animation:fadeUp .65s ease both}.card{height:100%;background:rgba(255,255,255,.88);border:1px solid rgba(221,230,214,.9);border-radius:18px;overflow:hidden;box-shadow:var(--shadow);transition:transform .28s ease,box-shadow .28s ease,border-color .28s ease}.card:hover{transform:translateY(-10px);box-shadow:0 24px 55px rgba(31,51,38,.22);border-color:rgba(245,184,75,.65)}.image-wrap{position:relative;overflow:hidden}.card img{width:100%;height:220px;object-fit:cover;display:block;transition:transform .5s ease}.card:hover img{transform:scale(1.08)}.rating-chip{position:absolute;top:14px;right:14px;background:rgba(255,255,255,.94);border-radius:999px;padding:7px 10px;font-weight:800;color:#3a3d18;box-shadow:0 8px 20px rgba(0,0,0,.14)}
.content{padding:20px}.content h2{color:var(--leaf);font-size:24px;margin-bottom:8px}.type{color:var(--tomato);font-weight:800;margin-bottom:16px}.info{display:flex;justify-content:space-between;gap:12px;margin-bottom:14px;font-weight:800;color:#3a443d}.pill{background:var(--mint);border:1px solid #d4e6cd;border-radius:999px;padding:7px 10px}.address{color:var(--muted);line-height:1.5}
.empty{grid-column:1/-1;text-align:center;background:white;border:1px solid var(--line);border-radius:18px;padding:38px;color:var(--muted)}
footer{background:var(--leaf);color:white;text-align:center;padding:22px;margin-top:40px}
@keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}@keyframes slideDown{from{transform:translateY(-100%)}to{transform:translateY(0)}}@keyframes pulse{0%,100%{transform:scale(1)}50%{transform:scale(1.08)}}
@media(max-width:760px){.navbar{align-items:flex-start;flex-direction:column}.nav-links{width:100%}.nav-links a{padding:9px 11px}.section-head{align-items:flex-start;flex-direction:column}.hero{padding-top:42px}.card img{height:190px}}
</style>
</head>
<body>
<nav class="navbar">
    <div class="logo">Food<span>Gallery</span></div>
    <ul class="nav-links">
        <li><a href="home">Home</a></li>
        <li><a href="home">Restaurants</a></li>
        <li><a href="cart">Cart</a></li>
        <li><a href="login.html">Login</a></li>
        <li><a href="register.html" class="signup">Sign Up</a></li>
    </ul>
</nav>

<header class="hero">
    <div class="hero-inner">
        <div class="eyebrow">Fresh picks near you</div>
        <h1>Order food that feels made for tonight.</h1>
        <p>Browse local favorites, choose from rich menus, and build your cart in a few easy taps.</p>
        <div class="hero-actions">
            <a class="hero-btn primary" href="#restaurants">Explore Restaurants</a>
            <a class="hero-btn ghost" href="cart">View Cart</a>
        </div>
    </div>
</header>

<section class="section-head" id="restaurants">
    <div>
        <h2>Popular Restaurants</h2>
        <p>Fresh kitchens, quick delivery, and cozy favorites.</p>
    </div>
</section>

<section class="restaurant-container">
<%
List<Restaurant> allRestaurants = (List<Restaurant>)request.getAttribute("allRestaurants");
if(allRestaurants != null && !allRestaurants.isEmpty()){
    for(Restaurant restaurant : allRestaurants){
%>
    <a class="restaurant-link" href="menu?restaurantId=<%=restaurant.getRestaurantId()%>">
        <div class="card">
            <div class="image-wrap">
                <img src="<%=restaurant.getImagePath()%>" alt="<%=restaurant.getName()%>">
                <div class="rating-chip">Star <%=restaurant.getRating()%></div>
            </div>
            <div class="content">
                <h2><%=restaurant.getName()%></h2>
                <p class="type"><%=restaurant.getCuisineType()%></p>
                <div class="info">
                    <span class="pill"><%=restaurant.getDeliveryTime()%> mins</span>
                    <span class="pill">Open now</span>
                </div>
                <p class="address"><%=restaurant.getAddress()%></p>
            </div>
        </div>
    </a>
<%
    }
} else {
%>
    <div class="empty">
        <h2>No restaurants available</h2>
        <p>Please check again later.</p>
    </div>
<%
}
%>
</section>

<footer>&copy; 2026 Food Gallery | Fresh food, fast delivery, great taste</footer>
</body>
</html>
