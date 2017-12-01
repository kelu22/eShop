package application;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javafx.scene.Scene;

public class Movie extends Product {

	private String duration;
	private String trailer;

	public Movie(String name, String description, String image, double price, double rate, int stockCounter, String purchaseDate, String duration, String trailer) {
		super(name, description, image, price, rate, stockCounter, purchaseDate);
		this.duration = duration;
		this.trailer = trailer;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
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

	@Override
	public void addReview(String review) {
		reviews.add(review);
		
	}

}
