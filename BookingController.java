package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttnAccept;

    @FXML
    private Button bttnCikis;

    @FXML
    private ComboBox<String> combo1;

    @FXML
    private ComboBox<String> combo2;

    
    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private Label label1;

    @FXML
    private TextField txt1;

  public void insert(String RoomName,String StartDate,String EndDate,String Extras,String Mail) {
    	

        Connection connection;
        SqlConnection con= new SqlConnection();
        PreparedStatement statement;
       
        try{
       
            connection = con.getConnection();
            String sql="INSERT INTO reservatioons(RoomName,Mail,Extras,StartDate,EndDate) VALUES (?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,RoomName);
            statement.setString(2,Mail);
            statement.setString(3,Extras);
            statement.setString(4,StartDate);
            statement.setString(5, EndDate);
      
         
            
            statement.executeUpdate();
    }catch (SQLException exception){
        con.showErrorMessage(exception);
    }
    }
    
    @FXML
    void Accept(ActionEvent event) {
    	insert(combo1.getValue().toString(),date1.getValue().toString(),date2.getValue().toString(),combo2.getValue(),txt1.getText());
    	Alert uyari= new Alert(AlertType.CONFIRMATION);
    	uyari.setTitle("Confirmation");
    	uyari.setHeaderText("Booking");
    	uyari.setContentText("Are u sure?");
    	
    	ButtonType btn1= new ButtonType("Yes",ButtonData.OK_DONE);
    	ButtonType btn2= new ButtonType("No",ButtonData.CANCEL_CLOSE);
    	uyari.getButtonTypes().setAll(btn1,btn2);
    	Optional<ButtonType> onay= uyari.showAndWait();
    	if(onay.get()== btn1)
    	{
    		insert(combo1.getValue().toString(),date1.getValue().toString(),date2.getValue().toString(),combo2.getValue(),txt1.getText());
    		label1.setText("Reservation is succesfull");
    	}
    	
    	
    }

    @FXML
    void bttnexit(ActionEvent event) {
    	final Stage stage1 = (Stage) bttnCikis.getScene().getWindow();
		stage1.close();
    }

    @FXML
    void initialize() {
    	combo1.getItems().add("Single Room");
    	combo1.getItems().add("Double Room");
    	combo1.getItems().add("Standart Triple Room");
    	combo1.getItems().add("Comfort Triple Room");
    	
    	combo2.getItems().add("I want to take advantage of the hotel's unlimited food and food");
    	combo2.getItems().add("I would like to take advantage of the various activities organized by the hotel");
    	
    }

}
