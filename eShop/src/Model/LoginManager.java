package Model;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

import application.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import login.LoginController;
import login.MainViewController;
import login.ProductViewController;

/** Manages control flow for logins */
public class LoginManager {
	private Scene scene;

	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated.
	 * Will show the main application screen.
	 * @throws Exception 
	 */
	public void authenticated(String username) throws Exception {
		showMainView(username);
	}

	/**
	 * Callback method invoked to notify that a user has logged out of the main
	 * application. Will show the login application screen.
	 */
	public void logout() {
		showLoginScreen();
	}

	public void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(this);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void showMainView(String username) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			scene.setRoot((Parent) loader.load());
			MainViewController controller = loader.<MainViewController>getController();
			controller.userInteraction(this, username);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
		
	public void showProduct(String username, Product p) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("productView.fxml"));
			scene.setRoot((Parent) loader.load());
			ProductViewController controller = loader.<ProductViewController>getController();
			controller.userInteraction(this,username, p);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void showProductListView(List<Product> productsList){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("productList.fxml"));
			scene.setRoot((Parent) loader.load());
			ProductViewController controller = loader.<ProductViewController>getController();
			//controller.userInteraction(this,username, p);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
