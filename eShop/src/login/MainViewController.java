package login;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/** Controls the main application screen */
public class MainViewController {

	@FXML
	private Button logoutButton;
	@FXML
	private Label usernameLabel;
	@FXML
	private ImageView imovie1;
	@FXML
	private ImageView imovie2;
	@FXML
	private ImageView imovie3;
	@FXML
	private ImageView imusic1;
	@FXML
	private ImageView imusic2;
	@FXML
	private ImageView imusic3;
	@FXML
	private ImageView ielec1;
	@FXML
	private ImageView ielec2;
	@FXML
	private ImageView ielec3;
	@FXML
	private Label lmovie1;
	@FXML
	private Label lmovie2;
	@FXML
	private Label lmovie3;
	@FXML
	private Label lmusic1;
	@FXML
	private Label lmusic2;
	@FXML
	private Label lmusic3;
	@FXML
	private Label lelec1;
	@FXML
	private Label lelec2;
	@FXML
	private Label lelec3;

	public void userInteraction(final Model.LoginManager loginManager, String username) {
		
		//Setting values in the view from the database
		usernameLabel.setText("Welcome back "+ username +"!");
		
		//Actions triggered in the view
		logoutButton.setOnAction(e -> loginManager.logout());
		imovie1.setOnMouseClicked(e -> loginManager.showProduct(username));
		imovie2.setOnMouseClicked(e -> loginManager.showProduct(username));
		imovie3.setOnMouseClicked(e -> loginManager.showProduct(username));
		imusic1.setOnMouseClicked(e -> loginManager.showProduct(username));
		imusic2.setOnMouseClicked(e -> loginManager.showProduct(username));
		imusic3.setOnMouseClicked(e -> loginManager.showProduct(username));
		ielec1.setOnMouseClicked(e -> loginManager.showProduct(username));
		ielec2.setOnMouseClicked(e -> loginManager.showProduct(username));
		ielec3.setOnMouseClicked(e -> loginManager.showProduct(username));
		
	}
}