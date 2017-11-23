package application;

import java.io.IOException;
import java.sql.SQLException;

import Model.daoModel;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.List;

import Model.LoginManager;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Store extends Application implements View {
	private List<User> users = new ArrayList<User>();
	private User user;

	public static void main(String[] args) {
		launch(args);
		try {
			daoModel dao = new Model.daoModelImpl();
			// dao.createTables();
			User u = new Customer("Arturo", "raquelwapa", false);
			// dao.insertUser(u);
			// dao.deleteUser(u);
			// System.out.println(dao.searchIdUser(u));
			User v = new Customer("Paloma", "arturocachonda", false);
			// dao.insertUser(v);
			// dao.deleteUser(v);
			// dao.deleteUser(v);
			// System.out.println(dao.searchIdUser(v));
			User s = new Seller("Tere", "OT", false, 0);
			// dao.insertUser(s);
			// dao.deleteUser(s);
			dao.showTable("sellers_ar");
		} catch (SQLException e) {
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());

			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws IOException {
		Scene scene = new Scene(new StackPane());

		LoginManager loginManager = new LoginManager(scene);
		loginManager.showLoginScreen();

		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub

	}
}
