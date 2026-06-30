<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Cart,com.tap.model.CartItem,com.tap.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout | Food Gallery</title>
<style>
:root{--leaf:#244632;--leaf-2:#356747;--mint:#ecf7e9;--paper:#fffaf1;--ink:#18251c;--muted:#657065;--tomato:#d94f2f;--gold:#f5b84b;--line:#dde6d6;--shadow:0 18px 45px rgba(31,51,38,.14)}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif}
body{min-height:100vh;background:radial-gradient(circle at bottom left,rgba(245,184,75,.22),transparent 32%),linear-gradient(135deg,#fffaf1,#edf7e9);color:var(--ink)}
.navbar{position:sticky;top:0;z-index:10;display:flex;justify-content:space-between;align-items:center;padding:16px 7%;background:rgba(36,70,50,.9);backdrop-filter:blur(14px);box-shadow:0 8px 30px rgba(16,34,23,.16);animation:slideDown .5s ease}.navbar strong{color:white;font-size:26px}.navbar strong span{color:var(--gold)}.navbar a{color:white;text-decoration:none;font-weight:900;padding:9px 13px;border-radius:999px;transition:.25s}.navbar a:hover{background:rgba(255,255,255,.14);transform:translateY(-2px)}
.container{width:min(1020px,92%);margin:38px auto 52px;display:grid;grid-template-columns:1fr 360px;gap:24px}.panel{background:rgba(255,255,255,.93);border:1px solid rgba(221,230,214,.95);border-radius:18px;padding:26px;box-shadow:var(--shadow);animation:fadeUp .65s ease both}.summary-panel{animation-delay:.08s}
h1{font-size:clamp(32px,5vw,50px);line-height:1.05;margin-bottom:10px;color:var(--leaf)}h2{color:var(--leaf);margin-bottom:16px}.lead{color:var(--muted);margin-bottom:22px;line-height:1.55}.form-group{margin-bottom:18px}label{display:block;margin-bottom:8px;font-weight:900;color:#3d493f}textarea,select{width:100%;padding:14px;border:1px solid #c9d8c2;border-radius:14px;font-size:15px;background:#fbfff8;outline:none;transition:.2s}textarea:focus,select:focus{border-color:var(--leaf-2);box-shadow:0 0 0 4px rgba(53,103,71,.12)}textarea{min-height:120px;resize:vertical}.row{display:flex;justify-content:space-between;gap:12px;margin:13px 0;color:#414c43}.total{border-top:1px solid var(--line);margin-top:17px;padding-top:17px;font-size:21px;font-weight:900;color:var(--leaf)}.btn{width:100%;border:none;border-radius:999px;background:linear-gradient(135deg,var(--tomato),#e78a3f);color:white;padding:14px 18px;font-size:16px;font-weight:900;cursor:pointer;margin-top:12px;box-shadow:0 12px 24px rgba(217,79,47,.22);transition:.25s}.btn:hover{transform:translateY(-3px);box-shadow:0 16px 30px rgba(217,79,47,.32)}.error{background:#fff1f1;color:#8a1f1f;border:1px solid #e3b4b4;padding:12px;border-radius:14px;margin-bottom:16px}.muted{color:var(--muted);line-height:1.5;margin-bottom:14px}
@keyframes fadeUp{from{opacity:0;transform:translateY(18px)}to{opacity:1;transform:translateY(0)}}@keyframes slideDown{from{transform:translateY(-100%)}to{transform:translateY(0)}}
@media(max-width:850px){.container{grid-template-columns:1fr}.navbar{gap:12px;flex-wrap:wrap}}
</style>
</head>
<body>
<nav class="navbar"><strong>Food<span>Gallery</span></strong><a href="cart">Back to Cart</a></nav>
<main class="container">
<%
Cart cart = (Cart)request.getAttribute("cart");
User user = (User)session.getAttribute("loggedInUser");
String error = (String)request.getAttribute("error");
%>
    <section class="panel">
        <h1>Checkout</h1>
        <p class="lead">Confirm your address and payment mode. Your order will be placed instantly.</p>
        <% if(error != null){ %><div class="error"><%= error %></div><% } %>
        <form action="checkout" method="post">
            <div class="form-group">
                <label for="address">Delivery Address</label>
                <textarea id="address" name="address" required><%= user != null && user.getAddress() != null ? user.getAddress() : "" %></textarea>
            </div>
            <div class="form-group">
                <label for="paymentMode">Payment Mode</label>
                <select id="paymentMode" name="paymentMode" required>
                    <option value="Cash on Delivery">Cash on Delivery</option>
                    <option value="UPI">UPI</option>
                    <option value="Card">Card</option>
                </select>
            </div>
            <button class="btn" type="submit">Place Order</button>
        </form>
    </section>
    <aside class="panel summary-panel">
        <h2>Order Summary</h2>
        <p class="muted">Fresh from the kitchen to your doorstep.</p>
        <% if(cart != null){ for(CartItem item : cart.getItems()){ %>
            <div class="row"><span><%= item.getName() %> x <%= item.getQuantity() %></span><strong>Rs. <%= String.format("%.2f", item.getPrice() * item.getQuantity()) %></strong></div>
        <% }} %>
        <div class="row total"><span>Total</span><span>Rs. <%= cart != null ? String.format("%.2f", cart.getTotalPrice()) : "0.00" %></span></div>
    </aside>
</main>
</body>
</html>
