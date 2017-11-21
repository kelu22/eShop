package Model;

import java.sql.SQLException;
import java.util.List;

import application.Product;
import application.User;

public interface daoModel {

	 public void createTables() throws Exception, SQLException;
	 public void insertUser(User  u)throws Exception;
	 public void insertProduct(Product  p)throws Exception;
	 public void insertRating(double rate, Product  p)throws Exception;
	 public void insertOrder(Product p, User u);
	 public void insertCart(Product p, User u);
	 public void insertProductList(Product p, User u);
	 public int searchIdUser(User u) throws Exception;
	 public int searchIdProduct(Product  p)throws Exception;
	 public void deleteUser(User u) throws Exception;
	 public void deleteProduct(Product p) throws Exception;
	 public List<Product> getProducts();
	 
}
