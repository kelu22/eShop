package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Connector;
import application.Movie;
import application.Electronic;
import application.Music;
import application.Product;
import application.Customer;
import application.Seller;
import application.User;

public class daoModelImpl implements daoModel {
	Connector connect = new Connector();
	private Statement statement = null;

	/**
	 * Method to create the database with the fields of: Primary key: pid ID to
	 * identify Income Pep
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */

	public void createTables() throws Exception, SQLException {
		try {
			System.out.println("He entrado");
			statement = connect.getConnection().createStatement();
			/**
			 * Creation of user-related tables
			 */

			String sql = "CREATE TABLE customers_ar " + " (customer_id INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(20), " + " password VARCHAR(20), " + " isAdmin VARCHAR(3), "
					+ " PRIMARY KEY ( customer_id ))";

			statement.executeUpdate(sql);

			sql = "CREATE TABLE sellers_ar " + "(seller_id INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(20), " + " password VARCHAR(20), " + " isAdmin VARCHAR(3), "
					+ " PRIMARY KEY ( seller_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of product-related tables
			 */
			sql = "CREATE TABLE music_ar " + "(music_id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(255), "
					+ " description VARCHAR(255), " + " image VARCHAR(255), " + " price NUMERIC(20,2), "
					+ " rate NUMERIC(1), " + " stock_counter NUMERIC(8), " + " purchase_date VARCHAR(25), "
					+ " author VARCHAR(80), " + " album_name VARCHAR(100), " + " PRIMARY KEY ( music_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE movie_ar " + "(movie_id INTEGER not NULL AUTO_INCREMENT, " + " name VARCHAR(255), "
					+ " description VARCHAR(255), " + " image VARCHAR(255), " + " price NUMERIC(20,2), "
					+ " rate NUMERIC(1), " + " stock_counter NUMERIC(8), " + " purchase_date VARCHAR(25), "
					+ " duration VARCHAR(4), " + " trailer VARCHAR(255), " + " PRIMARY KEY ( movie_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE electronic_ar " + "(electronic_id INTEGER not NULL AUTO_INCREMENT, "
					+ " name VARCHAR(255), " + " description VARCHAR(255), " + " image VARCHAR(255), "
					+ " price NUMERIC(20,2), " + " rate NUMERIC(1), " + " stock_counter NUMERIC(8), "
					+ " purchase_date VARCHAR(25), " + " specifications VARCHAR(255), " + " brand VARCHAR(50), "
					+ " PRIMARY KEY ( electronic_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE products_ar " + "(product_id INTEGER not NULL AUTO_INCREMENT, "
					+ " PRIMARY KEY (product_id), " + " music_id INTEGER, " + " electronic_id INTEGER, "
					+ " movie_id INTEGER, "  + " seller_id INTEGER, "+" FOREIGN KEY (music_id) REFERENCES music_ar(music_id), "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id),"+" FOREIGN KEY(seller_id) REFERENCES sellers_ar(seller_id), " + "PRIMARY KEY ( product_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of order-related tables
			 */
			sql = "CREATE TABLE orders_ar " + "(order_id INTEGER not NULL AUTO_INCREMENT, " + " customer_id INTEGER, "
					+ " product_id INTEGER, " + " PRIMARY KEY ( order_id )" + ")";
			statement.executeUpdate(sql);
			sql = "ALTER TABLE orders_ar ADD FOREIGN KEY (customer_id) REFERENCES customers_ar(customer_id)";
			// statement.executeUpdate(sql);
			sql = "ALTER TABLE orders_ar ADD FOREIGN KEY (product_id) REFERENCES products_ar(product_id)";
			statement.executeUpdate(sql);
			sql = "CREATE TABLE cart_ar " + "(cart_id INTEGER not NULL AUTO_INCREMENT, " + " customer_id INTEGER, "
					+ " product_id INTEGER, " + " FOREIGN KEY (customer_id) REFERENCES customers_ar(customer_id), "
					+ " FOREIGN KEY (product_id) REFERENCES products_ar(product_id), " + " PRIMARY KEY ( cart_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE product_list_ar " + "(list_id INTEGER not NULL AUTO_INCREMENT, "
					+ " seller_id INTEGER, " + " product_id INTEGER, "
					+ " FOREIGN KEY (seller_id) REFERENCES sellers_ar(seller_id), "
					+ " FOREIGN KEY (product_id) REFERENCES products_ar(product_id), " + " PRIMARY KEY ( list_id ))";
			statement.executeUpdate(sql);
			/**
			 * Creation of rate-related tables
			 */
			sql = "CREATE TABLE ratemusic_ar " + "(ratem_id INTEGER not NULL AUTO_INCREMENT, " + " rate NUMERIC(1), "
					+ " music_id INTEGER, " + " FOREIGN KEY (music_id) REFERENCES music_ar(music_id), "
					+ " PRIMARY KEY ( ratem_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE rateelectronic_ar " + "(ratee_id INTEGER not NULL AUTO_INCREMENT, "
					+ " rate NUMERIC(1), " + " electronic_id INTEGER, "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " PRIMARY KEY ( ratee_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE ratemovie_ar " + "(ratemo_id INTEGER not NULL AUTO_INCREMENT, " + " rate NUMERIC(1), "
					+ " movie_id INTEGER, " + " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id), "
					+ " PRIMARY KEY ( ratemo_id ))";
			statement.executeUpdate(sql);

			/**
			 * Creation of review-related tables
			 */
			sql = "CREATE TABLE musicreview_ar " + "(reviewm_id INTEGER not NULL AUTO_INCREMENT, "
					+ " music_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (music_id) REFERENCES music_ar(music_id), " + " PRIMARY KEY ( reviewm_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE electronicreview_ar " + "(reviewe_id INTEGER not NULL AUTO_INCREMENT, "
					+ " electronic_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (electronic_id) REFERENCES electronic_ar(electronic_id), "
					+ " PRIMARY KEY ( reviewe_id ))";
			statement.executeUpdate(sql);

			sql = "CREATE TABLE moviereview_ar " + "(reviewmov_id INTEGER not NULL AUTO_INCREMENT, "
					+ " movie_id INTEGER, " + " review VARCHAR(255), "
					+ " FOREIGN KEY (movie_id) REFERENCES movie_ar(movie_id), " + " PRIMARY KEY ( reviewmov_id ))";
			statement.executeUpdate(sql);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to insert all the data in the DB
	 * 
	 * @param robjs¡
	 * @throws Exception
	 */

	public void insertUser(User u) throws Exception {
		try {

			statement = connect.getConnection().createStatement();

			// System.out.println(u instanceof Customer);
			if (u instanceof Customer) {

				String sql = "INSERT INTO customers_ar(username, password, isAdmin) " + "VALUES ('" + u.getUsername()
						+ "', '" + u.getPassword() + "', '" + u.isAdmin() + "')";
				statement.executeUpdate(sql);
			} else {

				String sql = "INSERT INTO sellers_ar(username, password, isAdmin) " + "VALUES ('" + u.getUsername()
						+ "', '" + u.getPassword() + "', '" + u.isAdmin() + "')";
				statement.executeUpdate(sql);
			}

			statement.close();
		}

		catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());

		}

	}

	public void insertProduct(Product p) throws Exception {
		try {

			statement = connect.getConnection().createStatement();
			// System.out.println("Hola, he conectado");
			if (p instanceof Music) {

				String sql = "INSERT INTO music_ar(name, description, image, price, rate, stock_counter, purchase_date, author, album_name) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" + ((Music) p).getAuthor() + "', '" + ((Music) p).getAlbumName() + "')";
				statement.executeUpdate(sql);
				int idp = searchIdProduct(p);
				int idu = searchIdUserProduct(p);
				sql = "INSERT INTO products_ar(music_id, electronic_id, movie_id)"
					+"VALUES('"+idp+"', 'NULL', 'NULL', "+idu+"')";
				statement.executeUpdate(sql);

			} else if (p instanceof Electronic) {
				Electronic p2 = (Electronic) p;
				String sql = "INSERT INTO electronic_ar(name, description, image, price, rate, stock_counter, purchase_date, specifications, brand) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" + p2.getSpecifications() + "', '" + p2.getBrand() + "')";
				statement.executeUpdate(sql);
				int idp = searchIdProduct(p);
				int idu = searchIdUserProduct(p);
				sql = "INSERT INTO products_ar(music_id, electronic_id, movie_id)"
						+"VALUES('NULL', '"+idp+"', 'NULL', "+idu+"')";
				statement.executeUpdate(sql);
			} else {
				Movie p2 = (Movie) p;
				String sql = "INSERT INTO movie_ar(name, description, image, price, rate, stock_counter, purchase_date, duration, trailer) "
						+ "VALUES ('" + p.getName() + "', '" + p.getDescription() + "', '" + p.getImage() + "', '"
						+ p.getPrice() + "', '" + p.getRate() + "', '" + p.getStockCounter() + "', '"
						+ p.getPurchaseDate() + "', '" + p2.getDuration() + "', '" + p2.getTrailer() + "')";
				statement.executeUpdate(sql);
				int idp = searchIdProduct(p);
				int idu = searchIdUserProduct(p);
				sql = "INSERT INTO products_ar(music_id, electronic_id, movie_id)"
					+"VALUES('NULL', 'NULL', '"+idp+"', "+idu+"')";
				statement.executeUpdate(sql);
			}

			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}

	}

	public void insertRating(double rate, Product p) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				Music p2 = (Music) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO ratemusic_ar(rate, music_id) " + "VALUES ('" + rate + "', '" + id + "')";
				statement.executeUpdate(sql);
			} else if (p instanceof Electronic) {
				Electronic p2 = (Electronic) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO rateelectronic_ar(rate, electronic_id) " + "VALUES ('" + rate + "', '" + id
						+ "')";
				statement.executeUpdate(sql);
			} else {
				Movie p2 = (Movie) p;
				int id = searchIdProduct(p);
				String sql = "INSERT INTO ratemovie_ar(rate, movie_id) " + "VALUES ('" + rate + "', '" + id + "')";
				statement.executeUpdate(sql);
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}

	public void insertOrder(Product p, User u) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			int idU = searchIdUser(u);
			String sql = "INSERT INTO orders_ar(customer_id, product_id) " + "VALUES ('" + id + "', '" + idU + "')";
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}

	public void insertCart(Product p, User u) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			int idU = searchIdUser(u);
			String sql = "INSERT INTO cart_ar(customer_id, product_id) " + "VALUES ('" + id + "', '" + idU + "')";
			statement.executeUpdate(sql);
			statement.close();

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}

	public void insertProductList(Product p, User u) throws Exception {
		try {
			statement = connect.getConnection().createStatement();
			int id = searchIdProduct(p);
			int idU = searchIdUser(u);
			String sql = "INSERT INTO product_list_ar(customer_id, product_id) " + "VALUES ('" + id + "', '" + idU
					+ "')";
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}

	
	public int returnUser(User u) throws Exception {
		int id=0;
		try {
			statement = connect.getConnection().createStatement();
			if (u instanceof Customer) {
				String sql = "SELECT customer_id FROM customers_ar WHERE username='" + u.getUsername() + "' AND password='" + u.getPassword()+"'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				id = rs.getInt("customer_id");

			} else {
				String sql = "SELECT seller_id FROM sellers_ar WHERE username= '" + u.getUsername() + "' AND password='" + u.getPassword()+"'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println("El id de " + u.getUsername() + " es " + rs.getInt("seller_id"));
				id = rs.getInt("seller_id");
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	public int searchIdUserProduct(Product p) throws Exception{
		int id = 0;
		int idp = searchIdProduct(p);
		try{
			statement = connect.getConnection().createStatement();
			if(p instanceof Music){
				String sql = "SELECT product_id FROM products_ar WHERE music_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				id = rs.getInt("music_id");
			}else if(p instanceof Electronic){
				String sql = "SELECT product_id FROM products_ar WHERE electronic_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				id = rs.getInt("electronic_id");
			}else if (p instanceof Movie){
				String sql = "SELECT product_id FROM products_ar WHERE movie_id='" + idp + "'";
				ResultSet rs = statement.executeQuery(sql);
				id = rs.getInt("movie_id");
			}
			
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}
	public int searchIdUser(User u) throws Exception {
		int id = 0;
		try {
			statement = connect.getConnection().createStatement();
			// System.out.println("Hola, he conectado");
			if (u instanceof Customer) {
				// System.out.println("Hola, he entrado");
				// System.out.println(u.getUsername());
				String sql = "SELECT customer_id FROM customers_ar WHERE username='" + u.getUsername() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				// System.out.println("El id de "+ u.getUsername()+ " es
				// "+rs.getInt("customer_id"));
				id = rs.getInt("customer_id");

			} else {
				String sql = "SELECT seller_id FROM sellers_ar WHERE username= '" + u.getUsername() + "'";
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println("El id de " + u.getUsername() + " es " + rs.getInt("seller_id"));
				id = rs.getInt("seller_id");
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}

	public int searchIdProduct(Product p) throws Exception {
		int id = 0;
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				Music p2 = (Music) p;
				String sql = "SELECT music_id FROM music_ar WHERE name=" + p2.getName();
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println("El id de " + p2.getName() + " es " + rs.getInt("music_id"));
				id = rs.getInt("music_id");
			} else if (p instanceof Electronic) {
				Electronic p2 = (Electronic) p;
				String sql = "SELECT electronic_id FROM electronic_ar WHERE name=" + p2.getName();
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println("El id de " + p2.getName() + " es " + rs.getInt("electronic_id"));
				id = rs.getInt("electronic_id");
			} else {
				Movie p2 = (Movie) p;
				String sql = "SELECT movie_id FROM movie_ar WHERE name=" + p2.getName();
				ResultSet rs = statement.executeQuery(sql);
				rs.next();
				System.out.println("El id de " + p2.getName() + " es " + rs.getInt("movie_id"));
				id = rs.getInt("movie_id");
			}
			statement.close();
		} catch (Exception e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		return id;
	}

	public void deleteUser(User u) throws Exception {
		int id = searchIdUser(u);
		try {
			statement = connect.getConnection().createStatement();
			if (u instanceof Customer) {
				String sql = "DELETE FROM customers_ar WHERE customer_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM orders_ar WHERE customer_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM cart_ar WHERE customer_id = " + id;
				statement.executeUpdate(sql);
			} else {
				String sql = "DELETE FROM sellers_ar WHERE seller_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM product_list_ar WHERE seller_id = " + id;
				statement.executeUpdate(sql);
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}

	}

	public void deleteProduct(Product p) throws Exception {
		int id = searchIdProduct(p);
		try {
			statement = connect.getConnection().createStatement();
			if (p instanceof Music) {
				String sql = "DELETE FROM music_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM products_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM musicreview_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM ratemusic_ar WHERE music_id = " + id;
				statement.executeUpdate(sql);

			} else if (p instanceof Electronic) {
				String sql = "DELETE FROM electronic_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM products_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM electronicreview_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM rateelectronic_ar WHERE electronic_id = " + id;
				statement.executeUpdate(sql);
			} else {
				String sql = "DELETE FROM movie_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM products_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM moviereview_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
				sql = "DELETE FROM ratemovie_ar WHERE movie_id = " + id;
				statement.executeUpdate(sql);
			}
			statement.close();
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
	}

	public List<Product> getProducts() throws Exception {
		List<Product> products = new ArrayList<>();
		try {
			statement = connect.getConnection().createStatement();
			String sql = "SELECT music_id IS not NULL FROM products_ar";
			ResultSet rs = statement.executeQuery(sql);
			Double rate = 0.0;
			int i = 0;
			while (rs.next()) {
				sql = "SELECT * FROM music_ar WHERE music_id = " + rs.getString("music_id");
				ResultSet rs2 = statement.executeQuery(sql);
				List<String> reviews = new ArrayList<>();
				sql = "SELECT review FROM musicreview_ar where music_id =" + rs.getString("music_id");
				ResultSet rs3 = statement.executeQuery(sql);
				List<Integer> ratings = new ArrayList<>();
				sql = "SELECT rate FROM ratemusic_ar where music_id =" + rs.getString("music_id");
				ResultSet rs4 = statement.executeQuery(sql);

				while (rs2.next()) {
					while (rs3.next()) {
						reviews.add(rs3.getString("review"));
					}
					while (rs4.next()) {
						ratings.add(rs4.getInt("rate"));
						rate += rs4.getInt("rate");
						i = i + 1;
					}
					rate = rate / i;
					Product p = new Music(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("author"), rs2.getString("album_name"));
					products.add(p);
				}
			}
			i = 0;
			rate = 0.0;
			sql = "SELECT electronic_id IS NOT NULL FROM products_ar";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				sql = "SELECT * FROM electronic_ar WHERE electronic_id = " + rs.getString("electronic_id");
				ResultSet rs2 = statement.executeQuery(sql);
				List<String> reviews = new ArrayList<>();
				sql = "SELECT review FROM electronicreview_ar where electronic_id =" + rs.getString("electronic_id");
				ResultSet rs3 = statement.executeQuery(sql);
				List<Integer> ratings = new ArrayList<>();
				sql = "SELECT rate FROM rateelectronic_ar where electronic_id =" + rs.getString("electronic_id");
				ResultSet rs4 = statement.executeQuery(sql);

				while (rs2.next()) {
					while (rs3.next()) {
						reviews.add(rs3.getString("review"));
					}
					while (rs4.next()) {
						ratings.add(rs4.getInt("rate"));
						rate += rs4.getInt("rate");
						i = i + 1;
					}
					rate = rate / i;
					Product p = new Electronic(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("brand"), rs2.getString("specifications"));
					products.add(p);
				}

			}
			i = 0;
			rate = 0.0;
			sql = "SELECT movie_id IS NOT NULL FROM products_ar";
			rs = statement.executeQuery(sql);
			while (rs.next()) {
				sql = "SELECT * FROM movie_ar WERE movie_id = " + rs.getString("movie_id");
				ResultSet rs2 = statement.executeQuery(sql);
				List<String> reviews = new ArrayList<>();
				sql = "SELECT review FROM moviereview_ar where movie_id =" + rs.getString("movie_id");
				ResultSet rs3 = statement.executeQuery(sql);
				List<Integer> ratings = new ArrayList<>();
				sql = "SELECT rate FROM ratemovie_ar where movie_id =" + rs.getString("movie_id");
				ResultSet rs4 = statement.executeQuery(sql);
				while (rs2.next()) {
					while (rs3.next()) {
						reviews.add(rs3.getString("review"));
					}
					while (rs4.next()) {
						ratings.add(rs4.getInt("rate"));
						rate += rs4.getInt("rate");
						i = i + 1;
					}
					rate = rate / i;
					Product p = new Movie(rs2.getString("name"), rs2.getString("description"), rs2.getString("image"), rs2.getInt("price"), rs2.getInt("rate"), rs2.getInt("stock_counter"), rs2.getString("purchase_date"), rs2.getString("duration"), rs2.getString("trailer"));
					products.add(p);
				}
			}
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		statement.close();
		return products;
	}

	public void showTable(String nametable) throws Exception {
		ResultSet rs;
		String sql;
		try {
			statement = connect.getConnection().createStatement();
			switch (nametable) {
			case "customers_ar":
				sql = "SELECT * FROM customers_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("username"));
				}
				break;
			case "sellers_ar":
				sql = "SELECT * FROM sellers_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("username"));
				}
				break;
			case "music_ar":
				sql = "SELECT * FROM music_ar";
				rs = statement.executeQuery(sql);
				System.out.println("Lista de canciones");
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "electronic_ar":
				sql = "SELECT * FROM electronic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "movie_ar":
				sql = "SELECT * FROM movie_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println(rs.getString("name"));
				}
				break;
			case "products_ar":
				sql = "SELECT * FROM products_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("PID:" + rs.getString("product_id"));
					System.out.println("MUID:" + rs.getString("music_id"));
					System.out.println("ELID:" + rs.getString("electronic_id"));
					System.out.println("MOID:" + rs.getString("movie_id"));
				}
				break;
			case "orders_ar":
				sql = "SELECT * FROM orders_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("CID:" + rs.getString("customer_id"));
					System.out.println("OID:" + rs.getString("order_id"));
				}
				break;	
			case "productlist_ar":
				sql = "SELECT * FROM product_list_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("seller_id"));
					System.out.println("PID:" + rs.getString("product_id"));
				}
				break;
			case "musicreview_ar":
				sql = "SELECT * FROM musicreview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("music_id"));
					System.out.println("PID:" + rs.getString("reviewm_id"));
				}
				break;
			case "electronicreview_ar":
				sql = "SELECT * FROM musicreview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("electronic_id"));
					System.out.println("PID:" + rs.getString("reviewe_id"));
				}
				break;
			case "moviereview_ar":
				sql = "SELECT * FROM moviereview_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("movie_id"));
					System.out.println("PID:" + rs.getString("reviewmov_id"));
				}
				break;
			case "ratemusic_ar":
				sql = "SELECT * FROM ratemusic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("music_id"));
					System.out.println("PID:" + rs.getString("ratem_id"));
				}
				break;
			case "rateelectronic_ar":
				sql = "SELECT * FROM rateelectronic_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("electronic_id"));
					System.out.println("PID:" + rs.getString("ratee_id"));
				}
				break;
			case "ratemovie_ar":
				sql = "SELECT * FROM ratemovie_ar";
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					System.out.println("SID:" + rs.getString("movie_id"));
					System.out.println("PID:" + rs.getString("ratemo_id"));
				}
				break;
			}
				statement.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public List<Product> searchProductbyName(String name) throws Exception {
		ResultSet rs;
		String sql;
		List<Product> products = new ArrayList<>();
		try {
			statement = connect.getConnection().createStatement();
			sql = "SELECT * FROM music_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Music(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("author"), rs.getString("album_name"));
				products.add(p);
			}
			sql = "SELECT * FROM electronic_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Electronic(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("specifications"), rs.getString("brand"));
				products.add(p);
			}
			sql = "SELECT * FROM movie_ar WHERE name LIKE '%"+name+"%'";
			rs = statement.executeQuery(sql);
			while(rs.next()){
				Product p = new Movie(rs.getString("name"), rs.getString("description"), rs.getString("image"), rs.getInt("price"), rs.getInt("rate"), rs.getInt("stock_counter"), rs.getString("purchase_date"), rs.getString("duration"), rs.getString("trailer"));
				products.add(p);
			}
			return products;
		}
		catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println(e.getMessage());
		}
		statement.close();
		return null;
	}
}
