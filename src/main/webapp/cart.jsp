<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Cart,com.tap.model.CartItem"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Your Cart | Food Gallery</title>
<style>
:root{--leaf:#244632;--leaf-2:#356747;--mint:#ecf7e9;--paper:#fffaf1;--ink:#18251c;--muted:#657065;--tomato:#d94f2f;--gold:#f5b84b;--line:#dde6d6;--shadow:0 18px 45px rgba(31,51,38,.14)}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif}
body{min-height:100vh;background:radial-gradient(circle at top right,rgba(217,79,47,.16),transparent 34%),linear-gradient(135deg,#fffaf1,#edf7e9);color:var(--ink)}
.navbar{position:sticky;top:0;z-index:10;display:flex;justify-content:space-between;align-items:center;gap:18px;padding:16px 7%;background:rgba(36,70,50,.9);backdrop-filter:blur(14px);box-shadow:0 8px 30px rgba(16,34,23,.16);animation:slideDown .5s ease}.logo{font-size:28px;font-weight:850;color:white}.logo span{color:var(--gold)}.nav a{color:white;text-decoration:none;margin-left:12px;font-weight:800;padding:9px 12px;border-radius:999px;transition:.25s}.nav a:hover{background:rgba(255,255,255,.14);transform:translateY(-2px)}
.container{width:min(1120px,92%);margin:38px auto 52px}.title{display:flex;justify-content:space-between;align-items:center;gap:20px;margin-bottom:24px;animation:fadeUp .55s ease}.title h1{font-size:clamp(32px,5vw,52px);color:var(--leaf);line-height:1.05}.title p{color:var(--muted);margin-top:8px}
.cart-layout{display:grid;grid-template-columns:1fr 330px;gap:24px;align-items:start}.items{display:grid;gap:16px}.item,.summary,.empty{background:rgba(255,255,255,.92);border:1px solid rgba(221,230,214,.95);border-radius:18px;box-shadow:var(--shadow)}.item{display:grid;grid-template-columns:124px 1fr auto;gap:18px;padding:16px;align-items:center;animation:fadeUp .65s ease both;transition:.25s}.item:hover{transform:translateY(-5px);box-shadow:0 24px 55px rgba(31,51,38,.2)}.item img{width:124px;height:98px;object-fit:cover;border-radius:14px}.item h2{font-size:21px;color:var(--leaf);margin-bottom:7px}.item p{color:var(--muted);line-height:1.45}.price{font-weight:900;margin-top:8px;color:var(--tomato)}
.controls{display:flex;align-items:center;gap:9px;justify-content:flex-end}.icon-btn{width:36px;height:36px;border:1px solid #cfe1c8;background:var(--mint);color:var(--leaf);border-radius:999px;font-size:20px;font-weight:900;cursor:pointer;transition:.2s}.icon-btn:hover{transform:scale(1.08);background:#dff1da}.remove{border-color:#f0c0b5;background:#fff0eb;color:var(--tomato)}.qty{min-width:30px;text-align:center;font-weight:900}
.summary{padding:24px;position:sticky;top:92px;animation:fadeUp .7s ease .08s both}.summary h2{color:var(--leaf);margin-bottom:18px}.summary-row{display:flex;justify-content:space-between;margin:14px 0;color:#414c43}.summary-total{font-size:21px;font-weight:900;border-top:1px solid var(--line);padding-top:17px;margin-top:17px;color:var(--leaf)}
.btn{display:block;width:100%;text-align:center;text-decoration:none;border:none;border-radius:999px;padding:13px 18px;font-weight:900;cursor:pointer;margin-top:14px;transition:.25s}.primary{background:linear-gradient(135deg,var(--tomato),#e78a3f);color:white;box-shadow:0 12px 24px rgba(217,79,47,.22)}.secondary{background:#e8f3e5;color:var(--leaf)}.btn:hover{transform:translateY(-3px);box-shadow:0 16px 30px rgba(31,51,38,.18)}
.empty{padding:52px 34px;text-align:center;animation:fadeUp .6s ease}.empty h2{color:var(--leaf);font-size:30px;margin-bottom:12px}.empty p{color:var(--muted);margin-bottom:16px}
@keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}@keyframes slideDown{from{transform:translateY(-100%)}to{transform:translateY(0)}}
@media(max-width:850px){.navbar{align-items:flex-start;flex-direction:column}.nav a{margin-left:0;margin-right:8px}.cart-layout{grid-template-columns:1fr}.summary{position:static}.item{grid-template-columns:92px 1fr}.item img{width:92px;height:82px}.controls{grid-column:1/-1;justify-content:flex-start}.title{align-items:flex-start;flex-direction:column}}
</style>
</head>
<body>
<nav class="navbar"><div class="logo">Food<span>Gallery</span></div><div class="nav"><a href="home">Home</a><a href="cart">Cart</a><a href="login.html">Login</a></div></nav>
<main class="container">
<%
Cart cart = (Cart)request.getAttribute("cart");
if(cart == null || cart.isEmpty()){
%>
    <div class="empty">
        <h2>Your cart is waiting</h2>
        <p>Pick a restaurant and add something delicious.</p>
        <a class="btn primary" href="home">Browse Restaurants</a>
    </div>
<% } else { %>
    <div class="title">
        <div><h1>Your Cart</h1><p>Adjust quantities, then glide into checkout.</p></div>
        <a class="btn secondary" style="width:auto;margin:0;" href="home">Add More Food</a>
    </div>
    <div class="cart-layout">
        <section class="items">
        <% for(CartItem item : cart.getItems()){ %>
            <article class="item">
                <img src="<%= item.getImagePath() %>" alt="<%= item.getName() %>">
                <div>
                    <h2><%= item.getName() %></h2>
                    <p><%= item.getDescription() %></p>
                    <div class="price">Rs. <%= String.format("%.2f", item.getPrice()) %></div>
                </div>
                <div class="controls">
                    <form action="cart" method="post"><input type="hidden" name="action" value="decrease"><input type="hidden" name="itemId" value="<%= item.getItemId() %>"><button class="icon-btn" type="submit" title="Decrease">-</button></form>
                    <span class="qty"><%= item.getQuantity() %></span>
                    <form action="cart" method="post"><input type="hidden" name="action" value="increase"><input type="hidden" name="itemId" value="<%= item.getItemId() %>"><button class="icon-btn" type="submit" title="Increase">+</button></form>
                    <form action="cart" method="post"><input type="hidden" name="action" value="remove"><input type="hidden" name="itemId" value="<%= item.getItemId() %>"><button class="icon-btn remove" type="submit" title="Remove">x</button></form>
                </div>
            </article>
        <% } %>
        </section>
        <aside class="summary">
            <h2>Order Summary</h2>
            <div class="summary-row"><span>Subtotal</span><strong>Rs. <%= String.format("%.2f", cart.getTotalPrice()) %></strong></div>
            <div class="summary-row"><span>Delivery</span><strong>Free</strong></div>
            <div class="summary-row summary-total"><span>Total</span><span>Rs. <%= String.format("%.2f", cart.getTotalPrice()) %></span></div>
            <a class="btn primary" href="checkout">Checkout</a>
        </aside>
    </div>
<% } %>
</main>
</body>
</html>
