package login;

import application.Electronic;
import application.Movie;
import application.Music;
import application.Product;
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

	public void userInteraction(final Model.LoginManager loginManager, String username, Product p) {
			
			//Setting values in the view from the database
			usernameLabel.setText("Welcome back "+ username +"!");
			nameLabel.setText(p.getName());
			desLabel.setText(p.getDescription());
			stockLabel.setText(Integer.toString(p.getStockCounter()));
			rateLabel.setText(Double.toString(p.getRate()));
			priceLabel.setText(Double.toString(p.getPrice()));
			
			if (p instanceof Music){
				specific1Label.setText(((Music) p).getAlbumName());
				specific2Label.setText(((Music) p).getAuthor());
			}else if(p instanceof Electronic){
				specific1Label.setText(((Electronic) p).getSpecifications());
				specific2Label.setText(((Electronic) p).getBrand());
			}else if(p instanceof Movie){
				specific1Label.setText(((Movie) p).getDuration());
				specific2Label.setText(((Movie) p).getTrailer());
			}
				
			
			//Actions triggered in the view
			mainViewButton.setOnAction(e -> {
				try {
					loginManager.showMainView(username);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			logoutButton.setOnAction(e -> loginManager.logout());
			
	}
}