<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tap.model.Order"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Confirmed | Food Gallery</title>
<style>
:root{--leaf:#244632;--leaf-2:#356747;--mint:#ecf7e9;--paper:#fffaf1;--ink:#18251c;--muted:#657065;--tomato:#d94f2f;--gold:#f5b84b;--line:#dde6d6;--shadow:0 18px 45px rgba(31,51,38,.16)}
*{margin:0;padding:0;box-sizing:border-box;font-family:"Segoe UI",Arial,sans-serif}
body{min-height:100vh;background:radial-gradient(circle at top right,rgba(245,184,75,.26),transparent 34%),linear-gradient(135deg,#fffaf1,#edf7e9);color:var(--ink);display:grid;place-items:center;padding:28px}
.card{width:min(580px,100%);background:rgba(255,255,255,.95);border:1px solid rgba(221,230,214,.95);border-radius:24px;padding:38px;box-shadow:var(--shadow);text-align:center;animation:popIn .65s ease both}.check{width:78px;height:78px;margin:0 auto 18px;border-radius:24px;background:linear-gradient(135deg,var(--leaf-2),var(--leaf));color:white;display:flex;align-items:center;justify-content:center;font-size:40px;font-weight:900;box-shadow:0 14px 28px rgba(36,70,50,.24);animation:float 4s ease-in-out infinite}h1{color:var(--leaf);font-size:clamp(32px,5vw,48px);margin-bottom:12px}p{color:var(--muted);line-height:1.55}.details{margin:24px 0;text-align:left;background:linear-gradient(135deg,#f8fff4,#fff6e6);border:1px solid var(--line);border-radius:18px;padding:20px}.row{display:flex;justify-content:space-between;gap:12px;margin:12px 0}.row strong{color:var(--leaf)}.btn{display:inline-block;text-decoration:none;background:linear-gradient(135deg,var(--tomato),#e78a3f);color:white;padding:13px 20px;border-radius:999px;font-weight:900;box-shadow:0 12px 24px rgba(217,79,47,.24);transition:.25s}.btn:hover{transform:translateY(-3px);box-shadow:0 16px 30px rgba(217,79,47,.34)}
@keyframes popIn{from{opacity:0;transform:translateY(24px) scale(.98)}to{opacity:1;transform:translateY(0) scale(1)}}@keyframes float{0%,100%{transform:translateY(0)}50%{transform:translateY(-8px)}}
</style>
</head>
<body>
<%
Order order = (Order)request.getAttribute("order");
%>
<main class="card">
    <div class="check">&#10003;</div>
    <h1>Order Confirmed</h1>
    <p>Your food is being prepared. Thanks for ordering with Food Gallery.</p>
    <% if(order != null){ %>
    <div class="details">
        <div class="row"><span>Order ID</span><strong>#<%= order.getOrderId() %></strong></div>
        <div class="row"><span>Status</span><strong><%= order.getStatus() %></strong></div>
        <div class="row"><span>Payment</span><strong><%= order.getPaymentMode() %></strong></div>
        <div class="row"><span>Total</span><strong>Rs. <%= String.format("%.2f", order.getTotalAmount()) %></strong></div>
    </div>
    <% } %>
    <a class="btn" href="home">Back to Restaurants</a>
</main>
</body>
</html>
