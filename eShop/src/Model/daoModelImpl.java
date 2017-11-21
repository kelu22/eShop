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
 * Method to create the database with the fields of:
 * Primary key: pid
 * ID
 * Income
 * Pep
 * @throws Exception
 * @throws SQLException
 */
 public void createTables() throws Exception, SQLException{
	 try{
	 statement = connect.getConnection().createStatement();
	 /**
	  * Creation of user-related tables
	  */
	 //System.out.println("Hola, he conectado");
     String sql = "CREATE TABLE customers " +
             "(customer_id INTEGER not NULL, " +
             " username VARCHAR(20), " + 
             " password NUMERIC(20), " + 
             " isAdmin VARCHAR(3), " + 
             " PRIMARY KEY ( customer_id ))"; 
     
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE sellers " +
             "(seller_id INTEGER not NULL, " +
             " username VARCHAR(20), " + 
             " password NUMERIC(20), " + 
             " isAdmin VARCHAR(3), " + 
             " PRIMARY KEY ( seller_id ))"; 
	 statement.execute(sql);
	 /**
	  * Creation of product-related tables
	  */
	 sql = "CREATE TABLE music " +
             "(music_id INTEGER not NULL, " +
             " name VARCHAR(255), " + 
             " description VARCHAR(255), " + 
             " image VARCHAR(255), "+
             " price NUMERIC(20,2), " +
             " rate NUMERIC(1), " + 
             " stock_counter NUMERIC(8), " + 
             " purchase_date VARCHAR(25), " +
             " author VARCHAR(80), " +
             " album_name VARCHAR(100), " +
             " PRIMARY KEY ( music_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE movie " +
             "(movie_id INTEGER not NULL, " +
             " name VARCHAR(255), " + 
             " description VARCHAR(255), " + 
             " image VARCHAR(255), "+
             " price NUMERIC(20,2), " +
             " rate NUMERIC(1), " + 
             " stock_counter NUMERIC(8), " + 
             " purchase_date VARCHAR(25), " +
             " duration VARCHAR(4), " +
             " trailer VARCHAR(255), " +
             " PRIMARY KEY ( movie_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE electronic " +
             "(electronic_id INTEGER not NULL, " +
             " name VARCHAR(255), " + 
             " description VARCHAR(255), " + 
             " image VARCHAR(255), "+
             " price NUMERIC(20,2), " +
             " rate NUMERIC(1), " + 
             " stock_counter NUMERIC(8), " + 
             " purchase_date VARCHAR(25), " +
             " specifications VARCHAR(255), " +
             " brand VARCHAR(50), " +
             " PRIMARY KEY ( electronic_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE products " +
             "(product_id INTEGER not NULL, " +
             " FOREIGN KEY (music_id) REFERENCES music(music_id), " + 
             " FOREIGN KEY (electronic_id) REFERENCES electronic(electronic_id), " + 
             " FOREIGN KEY (movie_id) REFERENCES movie(movie_id), " +
             " PRIMARY KEY ( product_id ))"; 
	 statement.execute(sql);
	 /**
	  * Creation of order-related tables
	  */
	 sql = "CREATE TABLE orders " +
             "(order_id INTEGER not NULL, " +
             " FOREIGN KEY (customer_id) REFERENCES customers(customer_id), " + 
             " FOREIGN KEY (product_id) REFERENCES products(product_id), " + 
             " PRIMARY KEY ( order_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE cart " +
             "(cart_id INTEGER not NULL, " +
             " FOREIGN KEY (customer_id) REFERENCES customers(customer_id), " + 
             " FOREIGN KEY (product_id) REFERENCES products(product_id), " + 
             " PRIMARY KEY ( cart_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE productt_list " +
             "(list_id INTEGER not NULL, " +
             " FOREIGN KEY (seller_id) REFERENCES sellers(seller_id), " + 
             " FOREIGN KEY (product_id) REFERENCES products(product_id), " + 
             " PRIMARY KEY ( list_id ))"; 
	 statement.execute(sql);
	 /**
	  * Creation of rate-related tables
	  */
	 sql = "CREATE TABLE ratemusic " +
             "(ratem_id INTEGER not NULL, " +
             " rate NUMERIC(1), " + 
             " FOREIGN KEY (music_id) REFERENCES music(music_id), " + 
             " PRIMARY KEY ( ratem_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE rateelectronic " +
             "(ratee_id INTEGER not NULL, " +
             " rate NUMERIC(1), " + 
             " FOREIGN KEY (electronic_id) REFERENCES electronic(electronic_id), " + 
             " PRIMARY KEY ( ratee_id ))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE rateelectronic " +
             "(ratemo_id INTEGER not NULL, " +
             " rate NUMERIC(1), " + 
             " FOREIGN KEY (movie_id) REFERENCES movie(movie_id), " + 
             " PRIMARY KEY ( ratemo_id ))"; 
	 
	 /**
	  * Creation of review-related tables
	  */
	 sql = "CREATE TABLE musicreview " +
             "(reviewm_id INTEGER not NULL, " +
             " review VARCHAR(255), " + 
             " FOREIGN KEY (music_id) REFERENCES music(music_id))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE electronicreview " +
             "(reviewe_id INTEGER not NULL, " +
             " review VARCHAR(255), " + 
             " FOREIGN KEY (electronic_id) REFERENCES electronic(electronic_id))"; 
	 statement.execute(sql);
	 
	 sql = "CREATE TABLE moviereview " +
             "(reviewmov_id INTEGER not NULL, " +
             " review VARCHAR(255), " + 
             " FOREIGN KEY (movie_id) REFERENCES movie(movie_id))"; 
	 statement.execute(sql);
	
	 statement.close();
	 }catch(SQLException e){
		 e.printStackTrace();
	 }

 }
 
 /**
  * Method to insert all the data in the DB
  * @param robjs¡
  * @throws Exception
  */
 
 public void insertUser(User  u)throws Exception{
	 try {
		 
		  statement = connect.getConnection().createStatement();
		  //System.out.println("Hola, he conectado");
		  if (u instanceof Customer){
			  String sql = "INSERT INTO customer(username, password, isAdmin) " + "VALUES ('"+u.getUsername()+"', '"+u.getPassword()+"', '"+u.isAdmin()+"')";
			  statement.executeUpdate(sql);
			  
		  } else {
			  String sql = "INSERT INTO seller(username, password, isAdmin) " + "VALUES ('"+u.getUsername()+"', '"+u.getPassword()+"', '"+u.isAdmin()+"')";
			  statement.executeUpdate(sql);
		  }
		  
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }

 }
 
 public void insertProduct(Product  p)throws Exception{
	 try {
		 
		  statement = connect.getConnection().createStatement();
		  //System.out.println("Hola, he conectado");
		  if (p instanceof Music){
			  Music p2 = (Music)p;
			  String sql = "INSERT INTO customer(name, descripcion, image, price, rate, stockcounter, purchasedate, author, albumname) " + "VALUES ('"+p.getName()+"', '"+p.getDescription()+"', '"+p.getImage()+"', '"+p.getPrice()+"', '"+p.getRate()+"', '"+p.getStockCounter()+"', '"+p.getPurchaseDate()+"', '"+p2.getAuthor()+"', '"+p2.getAlbumName()+"')";
			  statement.executeUpdate(sql);
			  
		  } else if(p instanceof Electronic) {
			  Electronic p2 = (Electronic)p;
			  String sql = "INSERT INTO customer(name, descripcion, image, price, rate, stockcounter, purchasedate, specifications, brand) " + "VALUES ('"+p.getName()+"', '"+p.getDescription()+"', '"+p.getImage()+"', '"+p.getPrice()+"', '"+p.getRate()+"', '"+p.getStockCounter()+"', '"+p.getPurchaseDate()+"', '"+p2.getSpecifications()+"', '"+p2.getBrand()+"')";
			  statement.executeUpdate(sql);
		  } else{
			  Movie p2 = (Movie)p;
			  String sql = "INSERT INTO customer(name, descripcion, image, price, rate, stockcounter, purchasedate, duration, trailer) " + "VALUES ('"+p.getName()+"', '"+p.getDescription()+"', '"+p.getImage()+"', '"+p.getPrice()+"', '"+p.getRate()+"', '"+p.getStockCounter()+"', '"+p.getPurchaseDate()+"', '"+p2.getDuration()+"', '"+p2.getTrailer()+"')";
			  statement.executeUpdate(sql);
		  }
		  
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }


 }
 
 public void insertRating(double rate, Product  p)throws Exception{
	 try {
		  statement = connect.getConnection().createStatement();
		  if (p instanceof Music){
			  Music p2 = (Music)p;
			  int id = searchIdProduct(p);
			  String sql = "INSERT INTO ratemusic(rate, music_id) " + "VALUES ('"+rate+"', '"+id+"')";
			  statement.executeUpdate(sql);
		  } else if(p instanceof Electronic) {
			  Electronic p2 = (Electronic)p;
			  int id = searchIdProduct(p);
			  String sql = "INSERT INTO rateelectronic(rate, electronic_id) " + "VALUES ('"+rate+"', '"+id+"')";
			  statement.executeUpdate(sql);
		  } else{
			  Movie p2 = (Movie)p;
			  int id = searchIdProduct(p);
			  String sql = "INSERT INTO ratemovie(rate, movie_id) " + "VALUES ('"+rate+"', '"+id+"')";
			  statement.executeUpdate(sql);
		  }
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }
 }
 
 public void insertOrder(Product p, User u){
	 try{
		 statement = connect.getConnection().createStatement();
		 int id = searchIdProduct(p);
		 int idU = searchIdUser(u);
		 String sql =  "INSERT INTO orders(customer_id, product_id) " + "VALUES ('"+id+"', '"+idU+"')";
		 
		 
	 }catch (Exception e){
		 
	 }
 }
 
 public void insertCart(Product p, User u){
	 try{
		 statement = connect.getConnection().createStatement();
		 int id = searchIdProduct(p);
		 int idU = searchIdUser(u);
		 String sql =  "INSERT INTO cart(customer_id, product_id) " + "VALUES ('"+id+"', '"+idU+"')";
		 
		 
	 }catch (Exception e){
		 
	 }
 }
 
 public void insertProductList(Product p, User u){
	 try{
		 statement = connect.getConnection().createStatement();
		 int id = searchIdProduct(p);
		 int idU = searchIdUser(u);
		 String sql =  "INSERT INTO product_list(customer_id, product_id) " + "VALUES ('"+id+"', '"+idU+"')";
		 
		 
	 }catch (Exception e){
		 
	 }
 }
 
 public int searchIdUser(User u) throws Exception{
	 int id = 0;
	 try {
		  statement = connect.getConnection().createStatement();
		  //System.out.println("Hola, he conectado");
		  if (u instanceof Customer){
			  Customer u2 = (Customer)u;
			  String sql = "SELECT customer_id FROM customer WHERE username="+ u2.getUsername();
			  id = statement.executeUpdate(sql);
			  
		  } else{
			  Seller s2 = (Seller)u;
			  String sql = "SELECT seller_id FROM customer WHERE username="+ s2.getUsername();
			  id = statement.executeUpdate(sql);
		  }
		  
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }
	 return id;
 }
 public int searchIdProduct(Product  p)throws Exception{
	 int id = 0;
	 try {
		  statement = connect.getConnection().createStatement();
		  //System.out.println("Hola, he conectado");
		  if (p instanceof Music){
			  Music p2 = (Music)p;
			  String sql = "SELECT music_id FROM music WHERE name="+ p2.getName();
			  id = statement.executeUpdate(sql);
			  
		  } else if(p instanceof Electronic) {
			  Electronic p2 = (Electronic)p;
			  String sql = "SELECT electronic_id FROM electronic WHERE name="+ p2.getName();
			  id= statement.executeUpdate(sql);
		  } else{
			  Movie p2 = (Movie)p;
			  String sql = "SELECT movie_id FROM movie WHERE name="+ p2.getName();
			  id = statement.executeUpdate(sql);
		  }
		  
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }
	 return id;
 }
 
 public void deleteUser(User u) throws Exception{
	 int id = searchIdUser(u);
	 try{
		 statement = connect.getConnection().createStatement();
		 if(u instanceof Customer){
			 String sql = "DELETE FROM customers WHERE customer_id = "+id;
			 statement.executeUpdate(sql);
			 sql = "DELETE FROM orders WHERE customer_id = "+id;
			 statement.executeUpdate(sql);
			 sql = "DELETE FROM cart WHERE customer_id = "+id;
			 statement.executeUpdate(sql);
			 statement.close();
			 
		 }else{
			 String sql = "DELETE FROM sellers WHERE seller_id = "+id;
			 statement.executeUpdate(sql);
			 sql = "DELETE FROM product_list WHERE customer_id = "+id;
			 statement.executeUpdate(sql);
			 statement.close();
		 }
	 }catch(Exception e){
		 
	 }
	 
 }
 public void deleteProduct(Product p) throws Exception{
	 int id = searchIdProduct(p);
	 try {
		  statement = connect.getConnection().createStatement();
		  //System.out.println("Hola, he conectado");
		  if (p instanceof Music){
			  String sql = "DELETE FROM music WHERE music_id = "+id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM product WHERE music_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM musicreview WHERE music_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM ratemusic WHERE music_id = " + id;
			  statement.executeUpdate(sql);
			  
		  } else if(p instanceof Electronic) {

			  String sql = "DELETE FROM electronic WHERE electronic_id = "+id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM product WHERE electronic_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM electronicreview WHERE electronic_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM rateelectronic WHERE electronic_id = " + id;
			  statement.executeUpdate(sql);
		  } else{

			  String sql = "DELETE FROM movie WHERE movie_id = "+id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM product WHERE movie_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM moviereview WHERE movie_id = " + id;
			  statement.executeUpdate(sql);
			  sql = "DELETE FROM ratemovie WHERE movie_id = " + id;
			  statement.executeUpdate(sql);
		  }
		  
		  statement.close();
		  }
	 catch(Exception e){
		 
	 }
 }
 
 public List<Product> getProducts(){
	 List<Product> products = new ArrayList<>();
	 try{
		 statement = connect.getConnection().createStatement();
		 String sql = "SELECT music_id IS NOT NULL FROM product";
		 ResultSet rs = statement.executeQuery(sql);
		 Double rate = 0.0;
		 int i = 0;
		 while(rs.next()){
			 sql = "SELECT * FROM music WHERE music_id = "+rs.getString("music_id");
			 ResultSet rs2 = statement.executeQuery(sql);
			 List<String> reviews = new ArrayList<>();
			 sql = "SELECT review FROM musicreview where music_id =" + rs.getString("music_id");
			 ResultSet rs3 = statement.executeQuery(sql);
			 List<Integer> ratings= new ArrayList<>();
			 sql = "SELECT rate FROM ratemusic where music_id =" + rs.getString("music_id");
			 ResultSet rs4 = statement.executeQuery(sql);

			 while(rs2.next()){
				 while(rs3.next()){
					 reviews.add(rs3.getString("review"));
				 }
				 while (rs4.next()){
					 ratings.add(rs4.getInt("rate"));
					 rate += rs4.getInt("rate");
					 i = i+1;
				 }
				 rate = rate/i;
				 Product p = new Music(rs2.getString("name"), rs2.getString("description"), (Image)rs2.getString("image"),Double.parseDouble(rs2.getString("price")), rate, reviews,ratings, Integer.parseInt(rs2.getString("stock_counter")), rs2.getString("purchase_date"), rs2.getString("author"), rs2.getString("album_name") );
				 products.add(p);
			 }
		 }
		 i = 0;
		 rate = 0.0;
		 sql = "SELECT electronic_id IS NOT NULL FROM product";
		 rs = statement.executeQuery(sql);
		 while(rs.next()){
			 sql = "SELECT * FROM electronic WHERE electronic_id = "+rs.getString("electronic_id");
			 ResultSet rs2 = statement.executeQuery(sql);
			 List<String> reviews = new ArrayList<>();
			 sql = "SELECT review FROM electronicreview where electronic_id =" + rs.getString("electronic_id");
			 ResultSet rs3 = statement.executeQuery(sql);
			 List<Integer> ratings= new ArrayList<>();
			 sql = "SELECT rate FROM rateelectronic where electronic_id =" + rs.getString("electronic_id");
			 ResultSet rs4 = statement.executeQuery(sql);

			 while(rs2.next()){
				 while(rs3.next()){
					 reviews.add(rs3.getString("review"));
				 }
				 while (rs4.next()){
					 ratings.add(rs4.getInt("rate"));
					 rate += rs4.getInt("rate");
					 i = i+1;
				 }
				 rate = rate/i;
				 Product p = new Electronic(rs2.getString("name"), rs2.getString("description"), (Image)rs2.getString("image"),Double.parseDouble(rs2.getString("price")), rate, reviews, ratings, Integer.parseInt(rs2.getString("stock_counter")), rs2.getString("purchase_date"), rs2.getString("specifications"), rs2.getString("brand") );
				 products.add(p);
			 }

		 }
		 i = 0;
		 rate = 0.0;
		 sql = "SELECT movie_id IS NOT NULL FROM product";
		 rs = statement.executeQuery(sql);
		 while(rs.next()){
			 sql = "SELECT * FROM movie WERE movie_id = "+rs.getString("movie_id");
			 ResultSet rs2 = statement.executeQuery(sql);
			 List<String> reviews = new ArrayList<>();
			 sql = "SELECT review FROM moviereview where movie_id =" + rs.getString("movie_id");
			 ResultSet rs3 = statement.executeQuery(sql);
			 List<Integer> ratings= new ArrayList<>();
			 sql = "SELECT rate FROM ratemovie where movie_id =" + rs.getString("movie_id");
			 ResultSet rs4 = statement.executeQuery(sql);
			 while(rs2.next()){
				 while(rs3.next()){
					 reviews.add(rs3.getString("review"));
				 }
				 while (rs4.next()){
					 ratings.add(rs4.getInt("rate"));
					 rate += rs4.getInt("rate");
					 i = i+1;
				 }
				 rate = rate/i;
				 Product p = new Music(rs2.getString("name"), rs2.getString("description"), (Image)rs2.getString("image"),Double.parseDouble(rs2.getString("price")), rate, reviews, ratings, Integer.parseInt(rs2.getString("stock_counter")), rs2.getString("purchase_date"), rs2.getString("duration"), rs2.getString("trailer") );
				 products.add(p);
			 }
		 }
	 }catch (Exception e){	 
	 }
	 statement.close();
	 return products;
 }
}


