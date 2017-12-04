package Controller;

//import javafx.event.*;
import javafx.fxml.FXML;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Model.daoModel;
import application.Customer;
import application.Electronic;
import application.Movie;
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
	User session;

	public void initManager(final Model.LoginManager loginManager) {
		loginButton.setOnAction((event) -> {
			try {
	
				daoModel dao = new Model.daoModelImpl();
				User u = new Customer("arturo", "wapa", false);
				
				//Show tables
//				System.out.println("Tabla de musica");
//				dao.showTable("music_ar");
//				System.out.println("Tabla de movies");
//				dao.showTable("movie_ar");
//				System.out.println("Tabla de electronic");
//				dao.showTable("electronic_ar");
				
				//Products
				Product m1 = new Movie("Aladdin", "A beautiful princess get catfished", "file:Images/aladdin.png", 15.99, 5, 6, "", "112", "https://www.youtube.com/watch?v=QapaqcDucmg");
				Product m2 = new Movie("The shining", "A familys first Airbnb went wrong", "file:Images/shining.png", 15.99, 4, 10, "", "150", "https://www.youtube.com/watch?v=3b726feAhdU");
				Product m3 = new Movie("Ratatouille", "Health codes ignored", "file:Images/ratatouille.png", 15.99, 5, 6, "", "111", "https://www.youtube.com/watch?v=K5Ef2LkP0ks");
				Product m4 = new Movie("Sixth sense", "Bruce Willis haunt a terrified kid", "file:Images/sense.png", 15.99, 5, 2, "", "140", "https://www.youtube.com/watch?v=VG9AGf66tXM");
				Product m5 = new Movie("Inception", "A series of naps", "file:Images/inception.png", 15.99, 4, 9, "", "180", "https://www.youtube.com/watch?v=8BfMivMDOBI");
				Product m6 = new Movie("Superman", "Aliens destroy city because of a land dispute", "file:Images/superman.png", 15.99, 2, 15, "", "137", "https://www.youtube.com/watch?v=T6DJcgm3wNY");
				Product mu1 = new Music("Muse", " English rock band from Teignmouth, Devon, formed in 1994", "file:Images/muse.jpg", 20.99, 5, 1, "", "Muse", "Drones");
				Product mu2 = new Music("Breaking Benjamin", "American rock band from Wilkes-Barre, Pennsylvania, founded in 1999 by lead singer and guitarist Benjamin Burnley and drummer Jeremy Hummel", "file:Images/benjamin.jpg", 13.99, 5, 3, "", "Breaking Benjamin", "Phobia");
				Product mu3 = new Music("Linkin Park", "American rock band from Agoura Hills, California", "file:Images/linkin.jpg", 15.99, 5, 6, "", "Linkin Park", "Papercut");
				Product mu4 = new Music("Pvris", "merican rock band from Lowell, Massachusetts formed by members Lynn Gunn, Alex Babinski, and Brian MacDonald", "file:Images/pvris.jpg", 14.99, 4, 8, "", "Pvris", "White Noise");
				Product mu5 = new Music("H.I.M", "Finnish gothic rock band from Helsinki, Finland", "file:Images/him.jpg", 14.99, 3, 12, "", "H.I.M.", "Love Metal");
				Product mu6 = new Music("Nickelback", "American rock band from Agoura Hills, California", "file:Images/nickelback.jpg", 15.99, 5, 6, "", "Nickelback", "Dark Horse");
				Product e1 = new Electronic("1MORE Triple Driver", "In-Ear Headphones (Earphones/Earbuds) with Apple iOS and Android Compatible Microphone and Remote (Titanium)", "file:Images/ear1.jpg", 89.00, 4, 25, "","THREE DRIVERS- These headphones have two balanced armatures and a separate dynamic drive" ,"THX");
				Product e2 = new Electronic("BOKATE In-Ear", "In-Ear Earphone Earpiece headphone for apple iPhone 7 7 plus 6s 6s plus and Samsung Galaxy S7 S8 ", "file:Images/ear2.jpg", 19.99, 3, 9, "","Ergonomic design, secure fit, no troublesome wires from tangling or around your neck", "BOKATE");
				Product e3 = new Electronic("Panasonic ErgoFit", "In-Ear Earbuds Headphones with Mic/Controller RP-TCM125-A (Blue) ", "file:Images/ear3.png", 14.87, 4, 18, "","Connectivity technology is wired. Maximum input 200 milli watt","Panasonic");
				Product e4 = new Electronic("Skullcandy S2IKDZ-010", "S2IKDZ-010 Inkd 2.0 Earphones", "file:Images/ear4.png", 14.87, 4, 18, "","Connectivity technology is wired. Maximum input 200 milli watt" ,"Skullcandy");
				Product e5 = new Electronic("Samsung Electronics", "UN32J4001 32-Inch 720p LED TV (2017 Model)", "file:Images/tv1.png", 187.33, 5, 12, "","Samsung 720p high Definition (HD), which makes your favorite films and games more detailed, vibrant and clear", "Samsung");
				Product e6 = new Electronic("LG Electronics", "OLED55C6P Curved 55-Inch 4K Ultra HD Smart OLED TV (2016 Model)", "file:Images/tv2.png", 1186.87, 5, 9, "","Pairs 4K Ultra HD picture clarity with the contrast, color, and detail of High Dynamic Range (HDR) ","LG");
				
				
				//dao.insertCart(m1, u);
				//Insert
//				dao.insertProduct(m1,u);
//				dao.insertProduct(m2,u);
//				dao.insertProduct(m3,u);
//				dao.insertProduct(m4,u);
//				dao.insertProduct(m5,u);
//				dao.insertProduct(m6,u);
//				dao.insertProduct(e1,u);
//				dao.insertProduct(e2,u);
//				dao.insertProduct(e3,u);
//				dao.insertProduct(e4,u);
//				dao.insertProduct(e5,u);
//				dao.insertProduct(e6,u);
//				dao.insertProduct(mu1,u);
//				dao.insertProduct(mu2,u);
//				dao.insertProduct(mu3,u);
//				dao.insertProduct(mu4,u);
//				dao.insertProduct(mu5,u);
//				dao.insertProduct(mu6,u);
				
				//Delete
//				dao.deleteProduct(m1);
//				dao.deleteProduct(m2);
//				dao.deleteProduct(m3);
//				dao.deleteProduct(m4);
//				dao.deleteProduct(m5);
//				dao.deleteProduct(m6);
//				dao.deleteProduct(e1);
//				dao.deleteProduct(e2);
//				dao.deleteProduct(e3);
//				dao.deleteProduct(e4);
//				dao.deleteProduct(e5);
//				dao.deleteProduct(e6);
//				dao.deleteProduct(mu1);
//				dao.deleteProduct(mu2);
//				dao.deleteProduct(mu3);
//				dao.deleteProduct(mu4);
//				dao.deleteProduct(mu5);
//				dao.deleteProduct(mu6);
				
				
//				//Table after operation
//				System.out.println("Tabla products:");
//				dao.showTable("products_ar");
//				System.out.println("Tabla de musica");
//				dao.showTable("music_ar");
//				System.out.println("Tabla de movies");
//				dao.showTable("movie_ar");
//				System.out.println("Tabla de electronic");
//				dao.showTable("electronic_ar");
				
				
				session = authorize();
				username = session.getUsername();
				//authorize();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (username != null) {
				//Launch main View
				//loginManager.authenticated(sessionID);
				try {
					loginManager.authenticated(session, session.getUsername());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		checkBox.setOnAction((event) -> {
			isSeller = checkBox.isSelected();
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

		daoModel dao = new Model.daoModelImpl();
		User u;
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
	private User authorize() throws Exception {
		int id = 0;
		User u;
		// Call method that returns ask in DB if username and pass exists
		daoModel dao = new Model.daoModelImpl();
		//dao.showTable("sellers_ar");
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
			return u;
		}
		return null;
	}

	private static int sessionIDcounter = 0;

	private String generateSessionID() {
		sessionIDcounter++;
		return "Login - session " + sessionIDcounter;
	}
}
