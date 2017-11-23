package application;

import java.awt.Image;
import java.util.List;

public abstract class Product {
	protected String name;
	protected String description;
	protected Image image;
	protected double price;
	protected double rate;
	protected List<String> reviews;
	protected List<Integer> ratings;
	protected int stockCounter;
	protected String purchaseDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public List<String> getReviews() {
		return reviews;
	}

	public void setReviews(List<String> reviews) {
		this.reviews = reviews;
	}

	public int getStockCounter() {
		return stockCounter;
	}

	public void setStockCounter(int stockCounter) {
		this.stockCounter = stockCounter;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Product(String name, String description, Image image, double price, double rate, List<String> reviews,
			List<Integer> ratings, int stockCounter, String purchaseDate) {
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.rate = rate;
		this.ratings = ratings;
		this.reviews = reviews;
		this.stockCounter = stockCounter;
		this.purchaseDate = purchaseDate;
	}

	public abstract void showView();

	public abstract void modifyRating(int rating);

	public abstract void modifyAmount(double amount);

}
