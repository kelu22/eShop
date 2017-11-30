package Model;

import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import login.LoginController;
import login.MainViewController;

/** Manages control flow for logins */
public class LoginManager {
	private Scene scene;

	public LoginManager(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Callback method invoked to notify that a user has been authenticated.
	 * Will show the main application screen.
	 */
	public void authenticated(String username) {
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

	private void showMainView(String username) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("mainview.fxml"));
			scene.setRoot((Parent) loader.load());
			MainViewController controller = loader.<MainViewController>getController();
			controller.userInteraction(this, username);
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void showProduct() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("productView.fxml"));
			scene.setRoot((Parent) loader.load());
			//MainViewController controller = loader.<MainViewController>getController();
			//controller.userInteraction();
		} catch (IOException ex) {
			Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
