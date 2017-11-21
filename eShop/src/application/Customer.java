package application;

import java.util.ArrayList;
import java.util.List;


public class Customer extends User{
	private List<Product> orderList = new ArrayList<Product>();
	private List<Product> cart = new ArrayList<Product>();

	public Customer(int id, String username, String password, boolean isAdmin, List<Product> orderList,
			List<Product> cart) {
		super(id, username, password, isAdmin);
		this.orderList = orderList;
		this.cart = cart;
	}
	public List<Product> getOrderList() {
		return orderList;
	}
	public List<Product> getCart() {
		return cart;
	}
	public void setProductOrder(Product order) {
		orderList.add(order);
	}
	public void setProductCart(Product cartP){
		cart.add(cartP);
	}
	public void removeOrder(Product order){
		orderList.remove(order);
	}	
	public void removeCart(Product cart){
		orderList.remove(cart);
	}
	
	public void removeCartList(){
		cart.clear();
	}
	public void removeOrderList(){
		orderList.clear();
	}
	
}
