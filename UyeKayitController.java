package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UyeKayitController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttn1;

    @FXML
    private Button bttn2;

    @FXML
    private DatePicker date;

    @FXML
    private TextField email;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPsswrd;

    @FXML
    private TextField txtSur;
    
    @FXML
    private TextField txtQue;

    
    private static final String DATABASE_URL = "jdbc:mysql://localhost/hoteldatabase";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "";
    
    @FXML
    void backHome(ActionEvent event) {
    	
    		final Stage stage1 = (Stage) bttn2.getScene().getWindow();
    		stage1.close();
      
    }
    
    
    public void insert(String firstname,String lastname,String email,String birthdate,String passwrd,String Gender, String Que) {
    	

        Connection connection;
        SqlConnection con= new SqlConnection();
        PreparedStatement statement;
       
        try{
       
            connection = con.getConnection();
            String sql="INSERT INTO members(MemberName,MemberSurname,Mail,BirthDate,passwrd,Gender,SecurityQue) VALUES (?,?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,firstname);
            statement.setString(2,lastname);
            statement.setString(3,email);
            statement.setString(4,birthdate);
            statement.setString(5,MD5.MD5(passwrd));
            statement.setString(6,Gender);
            statement.setString(7, Que);
         
            
            statement.executeUpdate();
    }catch (SQLException exception){
        con.showErrorMessage(exception);
    }
    }

    @FXML
    void signUp(ActionEvent event) {
    
    	Alert uyari= new Alert(AlertType.CONFIRMATION);
    	uyari.setTitle("Confirmation");
    	uyari.setHeaderText("Sign in");
    	uyari.setContentText("Are u sure?");
    	
    	ButtonType btn1= new ButtonType("Yes",ButtonData.OK_DONE);
    	ButtonType btn2= new ButtonType("No",ButtonData.CANCEL_CLOSE);
    	uyari.getButtonTypes().setAll(btn1,btn2);
    	Optional<ButtonType> onay= uyari.showAndWait();
    	if(onay.get()==btn1)
    	{
    		insert(txtName.getText(),txtSur.getText(),email.getText(),date.getValue().toString(),txtPsswrd.getText(),gender.getValue().toString(),txtQue.getText());
    		Alert uyari2= new Alert(AlertType.INFORMATION);
        	uyari2.setTitle("Info");
        	uyari2.setHeaderText("Sign in");
        	uyari2.setContentText("Member registration has been successfully completed");
        }
    }
    @FXML
    void initialize() {	
    	gender.getItems().add("Male");
    	gender.getItems().add("Female");
    }

}
