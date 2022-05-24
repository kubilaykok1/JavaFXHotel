package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PricesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup group1;
    
    @FXML
    private Button bttn1;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private TextField txt1;
    
    @FXML
    void backClick(ActionEvent event) {
    	try{
    		final Stage stage1 = (Stage) bttn1.getScene().getWindow();
    		stage1.close();
			 FXMLLoader fxmlLoader = new FXMLLoader();
	            fxmlLoader.setLocation(getClass().getResource("Anasayfa.fxml"));
           Stage stage = new Stage();
           Scene scene = new Scene(fxmlLoader.load(), 362, 327);
           stage.initStyle(StageStyle.UNDECORATED);
           stage.setScene(scene);
           stage.show();
       }catch (IOException exception) {
           System.out.println("Error!!!");
           System.out.println("Error : "+exception.getMessage());
       }
    }

    @FXML
    void selected1(ActionEvent event) {
    	if(radio1.isSelected())
    		txt1.setText("75£");
    }

    @FXML
    void selected2(ActionEvent event) {
    	if(radio2.isSelected())
    		txt1.setText("140£");
    }

    @FXML
    void selected3(ActionEvent event) {
    	if(radio3.isSelected())
    		txt1.setText("210£");
    }

    @FXML
    void selected4(ActionEvent event) {
    	if(radio4.isSelected())
    		txt1.setText("240£");
    }	
    
    
    @FXML
    void initialize() {
    	radio1.setToggleGroup(group1);
    	radio2.setToggleGroup(group1);
    	radio3.setToggleGroup(group1);
    	radio4.setToggleGroup(group1);
    }

}
