package login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/** Controls the main application screen */
public class MainViewController {

	@FXML
	private Button logoutButton;
	@FXML
	private Label usernameLabel;

	public void setUsername(final Model.LoginManager loginManager, String username) {

		usernameLabel.setText(username);

		logoutButton.setOnAction(e -> loginManager.logout());

	}
}