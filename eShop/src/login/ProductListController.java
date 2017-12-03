package login;
import java.util.List;

import application.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class ProductListController {
	@FXML
	private ListView<String> listView = new ListView<String>();
	public void userInteraction(final Model.LoginManager loginManager, String username, List<Product> p){
        ObservableList<String> items =FXCollections.observableArrayList ();
        for (int i = 0;i<p.size();i++){
        	items.add(p.get(i).getName());
        }
        listView.setItems(items);

        listView.setCellFactory(param -> new ListCell<String>() {
        	private ImageView imageView = new ImageView();
        	int j = 0;
            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                	imageView.setImage(new Image(p.get(j).getImage()));
                	j++;
                    setText(name);
                    setGraphic(imageView);
                }
            }
        });
//        VBox box = new VBox(listView);
//        box.setAlignment(Pos.CENTER);
//        Scene scene = new Scene(box, 200, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }
		
}

