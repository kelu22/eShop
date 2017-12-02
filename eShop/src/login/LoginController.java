package login;

//import javafx.event.*;
import javafx.fxml.FXML;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.daoModel;
import application.Customer;
import application.Music;
import application.Product;
import application.Seller;
import application.User;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

/** Controls the login screen */
public class LoginController {

	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;
	@FXML
	private Button registerButton;
	@FXML
	private CheckBox checkBox;

	private boolean isSeller = false;
	String sessionID = null;
	String username = null;

	public void initManager(final Model.LoginManager loginManager) {
		loginButton.setOnAction((event) -> {
			try {
	
				daoModel dao = new Model.daoModelImpl();
				Product p = new Music("ok", "ok", "image.png", 2, 5, 1,"2345", "author", "album");
				//dao.insertProduct(p);
				dao.showTable("music_ar");
				username = authorize();
				authorize();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (username != null) {
				//Launch main View
				//loginManager.authenticated(sessionID);
				try {
					loginManager.authenticated(username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		checkBox.setOnAction((event) -> {
			isSeller = checkBox.isSelected();
			System.out.println("CheckBox Action (isSeller: " + isSeller + ")");
		});

		registerButton.setOnAction((event) -> {
			try {
				register();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void register() throws Exception {

		System.out.println("No error in img");
		daoModel dao = new Model.daoModelImpl();
		User u;
		dao.showTable("sellers_ar");
		dao.showTable("customers_ar");
		if (isSeller == false) {
			u = new Customer(user.getText(), password.getText(), false);
		} else {
			u = new Seller(user.getText(), password.getText(), false, 0);
		}
		dao.insertUser(u);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("This user has been registered!");
		alert.showAndWait();
		System.out.println("The user: " + u.getUsername() + " has been registered into the DB");
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session otherwise,
	 * return null.
	 * 
	 * @throws Exception
	 * 
	 */
	private String authorize() throws Exception {
		int id = 0;
		User u;
		// Call method that returns ask in DB if username and pass exists
		daoModel dao = new Model.daoModelImpl();
		dao.showTable("sellers_ar");
		if (isSeller == false) {
			u = new Customer(user.getText(), password.getText(), false);
		} else {
			u = new Seller(user.getText(), password.getText(), false, 0);
		}
		try {
			id = dao.returnUser(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (id == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error Dialog");
			alert.setContentText("This user is not in the DB");
			alert.showAndWait();
			System.out.println("This user is not in the DB");
		} else {
			//System.out.println("Yey! this user does exist in the DB");
			//sessionID = generateSessionID();
			username = u.getUsername();
		}
		return username;
	}

	private static int sessionIDcounter = 0;

	private String generateSessionID() {
		sessionIDcounter++;
		return "Login - session " + sessionIDcounter;
	}
}
