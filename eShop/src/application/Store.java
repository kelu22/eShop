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

public class Store extends Application implements View{
	private List<User> users = new ArrayList<User>();
	private User user;
	
	

	public static void main(String[] args) {
		launch(args);
		try {
			System.out.println("Arturo es un pesado");
			daoModel dao = new Model.daoModelImpl();
			dao.createTables();
		} catch (SQLException e) {
			 System.err.println("SQLState: " +
	                    ((SQLException)e).getSQLState());

	                System.err.println("Error Code: " +
	                    ((SQLException)e).getErrorCode());
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
