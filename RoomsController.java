package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RoomsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttn1;

    @FXML
    private ImageView imageComfort;

    @FXML
    private ImageView imageDouble;

    @FXML
    private ImageView imageSingle;

    @FXML
    private ImageView imageStandart;
    
    @FXML
    private TextArea txtArea;

    @FXML
    void ClickStandart(MouseEvent event) {
    	txtArea.setText("22m²•Non-smoking•Air conditioned•Wireless Internet•Cable/Satellite TV•Balcony•Linen and Towels Provided•Telephone•Room Safe•Heating•24hr Security•Bath•Television•Cots Available•Desk\r\n"+ "Bed options: 3 single beds or 1 single bed, 1 extra-large double bed");
    }

    @FXML
    void ComfortClick(MouseEvent event) {
    	txtArea.setText("22m²•Non-smoking•Air conditioned•Cable/Satellite TV•Wireless Internet•Balcony•Linen and Towels Provided•Lift/Elevator Access•Free Toiletries•24hr Security•Telephone•Room Safe•Television•Mini Fridge•Housekeeping•Heating•Desk•Bath");
    }

    @FXML
    void DoubleClick(MouseEvent event) {
    	txtArea.setText("20m²•City view•Non-smoking•Balcony•Heating•Cable/Satellite TV•Air conditioned•Wireless Internet•Room Safe•Housekeeping•Linen and Towels Provided•Shower•Telephone•Free Toiletries•Hairdryer•En-suite Bathroom•24hr Security•Cots Available•Mini Fridge");
    }

    @FXML
    void backClick(ActionEvent event) {
    	try {
    		final Stage stage1 = (Stage) bttn1.getScene().getWindow();
    		stage1.close();
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Anasayfa.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setTitle("Anasayfa");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void clickSingle(MouseEvent event) {
    	txtArea.setText("15m²•Non-smoking•Air conditioned•Wireless Internet•Mini Fridge•Cable/Satellite TV•Linen and Towels Provided•Room Safe•Telephone•Desk•Heating•Lift/Elevator Access•Espresso Machine•Cots Available•24hr Security•Television•Tea/Coffee Maker•Bath•Balcony");
    }

    @FXML
    void initialize() {
    

    }

}

