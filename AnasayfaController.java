package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnasayfaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttn1;

    @FXML
    private Button bttn2;

    @FXML
    private Button bttn3;

    @FXML
    private Button bttn4;

    @FXML
    void btnnBooking(ActionEvent event) {
    	try {	
    		final Stage stage1 = (Stage) bttn2.getScene().getWindow();
    		stage1.close();
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Booking.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 740, 450);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Booking");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    

    @FXML
    void bttnPrice(ActionEvent event) {
    	try {
    		
    		final Stage stage1 = (Stage) bttn2.getScene().getWindow();
    		stage1.close();
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Prices.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 430, 282);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Prices");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void bttnRooms(ActionEvent event) {
    	try {
    		final Stage stage1 = (Stage) bttn2.getScene().getWindow();
    		stage1.close();
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Rooms.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 790, 640);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Information for Rooms");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void initialize() {
     
    	
    }

}
