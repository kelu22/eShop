package Controller;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import application.Product;
import application.User;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
public class ProductListController {
	@FXML
	private ListView<String> listView;
	@FXML
	private Button mainViewButton;
	@FXML
	private Button logoutButton;
	@FXML
	private Label usernameLabel;
	

	
	public void userInteraction(final Model.LoginManager loginManager, String username, List<Product> p,User session){
        ObservableList<String> items =FXCollections.observableArrayList ();
        String[] listOfImages = new String[p.size()];
        for (int i = 0;i<p.size();i++){
        	items.add(p.get(i).getName());
        	listOfImages[i] = p.get(i).getImage();
        }
        listView.setItems(items);

        
        
        listView.setCellFactory(param -> new ListCell<String>() {
        	private ImageView imageView = new ImageView();
 
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	imageView.setImage(new Image(returnImage(name, listOfImages,items)));
                	imageView.setFitHeight(120);
                	imageView.setFitWidth(120);
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
        

        
     // Handle ListView selection changes.
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loginManager.showProduct(username, returnProduct(newValue,p), session);    
        });
        
        usernameLabel.setText("Welcome back "+ username +"!");
		//Actions triggered in the view
        mainViewButton.setOnAction(e -> loginManager.showMainView(username,session));
		logoutButton.setOnAction(e -> loginManager.logout());
    }
	public String returnImage(String name, String[] list,ObservableList<String> items){
		for(int i = 0;i<list.length;i++){
			if(name == items.get(i)){
				return list[i];
			}
		}
		return "";
	}

	public Product returnProduct(String name,List<Product> p){
		for(int i = 0;i<p.size();i++){
			if(name == p.get(i).getName()){
				return p.get(i);
			}
		}
		return null;
	}
}

