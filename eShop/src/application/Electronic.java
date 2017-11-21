package application;

import java.awt.Image;
import java.util.List;

import javafx.scene.Scene;

public class Electronic extends Product{
	private String specifications;
	private String brand;
	
	public Electronic(String name, String description, Image image, double price, double rate, List<String> reviews, List<Integer> ratings,
			int stockCounter, String purchaseDate, String specifications, String Brand) {
		super(name, description, image, price, rate, reviews, ratings, stockCounter, purchaseDate);
		this.specifications= specifications;
		this.brand = brand;
	}
	
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyRating(int rating) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void modifyAmount(double amount) {
		// TODO Auto-generated method stub
		
	}


}
