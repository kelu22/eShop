package login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class ProductViewController {

	@FXML
	private Button logoutButton;
	@FXML
	private Button buyButton;
	@FXML
	private Button mainViewButton;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label desLabel;
	@FXML
	private Label stockLabel;
	@FXML
	private Label specific1Label;
	@FXML
	private Label specific2Label;
	@FXML
	private Label rateLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private ImageView iproduct;
	@FXML
	private ListView reviewsList;

	public void userInteraction(final Model.LoginManager loginManager, String username) {
			
			//Setting values in the view from the database
			usernameLabel.setText("Welcome back "+ username +"!");
			
			//Actions triggered in the view
			mainViewButton.setOnAction(e -> loginManager.showMainView(username));
			logoutButton.setOnAction(e -> loginManager.logout());
	
	}
}