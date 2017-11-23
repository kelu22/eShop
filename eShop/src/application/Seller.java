package application;

import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
	private List<Product> productList = new ArrayList<Product>();
	private double rating;

	public Seller(String username, String password, boolean isAdmin, double rating) {
		super(username, password, isAdmin);
		this.rating = rating;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void addProductList(Product product) {
		productList.add(product);
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
