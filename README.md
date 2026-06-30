# Food Gallery

Food Gallery is a Java Servlet and JSP food-ordering web app for browsing restaurants, viewing menus, managing a cart, and placing orders.

## Run The Project

1. Open the project in Eclipse.
2. Make sure the configured server is Apache Tomcat 10.1.
3. Run `database.sql` in MySQL to create the `foodstore` database, tables, and starter restaurant/menu data.
4. Update the database username or password in `src/main/java/com/tap/utility/DBConnection.java` if your local MySQL credentials are different.
5. Run the project on Tomcat and open `/tap_food/home`.

## Main Pages

- `/home` shows restaurants.
- `/menu?restaurantId=1` shows a restaurant menu.
- `/cart` shows the cart with quantity controls.
- `/checkout` places the order for a logged-in user.
- `/order-confirmation?orderId=1` shows the order confirmation.
