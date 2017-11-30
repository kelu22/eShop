package login;

import java.io.IOException;
import Model.LoginManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/** Main application class for the login demo application */
public class LoginDemoApplication extends Application {

	// public static void main(String[] args) {
	// launch(args);
	// }

	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(new StackPane());

		LoginManager loginManager = new LoginManager(scene);
		loginManager.showLoginScreen();

		stage.sizeToScene();
		stage.setScene(scene);
		stage.show();
	}
}