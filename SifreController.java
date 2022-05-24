package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SifreController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML    
    private CheckBox check1;
    
    @FXML
    private Button exitbttn;

    @FXML
    private PasswordField txt1;

    @FXML
    private PasswordField txt2;

    @FXML
    private TextField txt3;

    
    @FXML
    private TextField txt4;

    
    @FXML
    private Button updatebttn;

 public int SeguridadCheck(String Mail,String segura ){
    	
        Connection connection;
        SqlConnection con= new SqlConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
        	
            connection = con.getConnection();
            statement = connection.prepareStatement("select * from members where Mail=? and SecurityQue=?");
            statement.setString(1,Mail);
            statement.setString(2,segura);
         
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()) {
                return 0;
            } else {
                return 1;
            }
        }catch (SQLException exception){
            con.showErrorMessage(exception);
            return 1;
        }
    }
    
    
    @FXML
    void updateClick(ActionEvent event) {
    	 int control = SeguridadCheck(txt4.getText(),txt3.getText());
      	if (control==0) {
      		 try {
            	 Connection connection;
                 SqlConnection helper = new SqlConnection();
                 PreparedStatement statement;
                 connection = helper.getConnection();
                
      		   String sql = "UPDATE  `members` SET "
               		+ " `passwrd`=? WHERE Mail ='"+txt4.getText()+"'";
               	
               statement = connection.prepareStatement(sql);   
              
               statement.setString(1,MD5.MD5(txt1.getText()));
               statement.executeUpdate();
               
               Alert hata= new Alert(AlertType.INFORMATION);
               hata.setTitle("DONE");
               hata.setHeaderText("Information from HOTEL IT");
               hata.setContentText("New Password is Correctly Updated");
               hata.show();
  
      		 }
              
    	
             
        catch (Exception ex)
        {
        	System.out.println(ex);
        }
      		 }
      		 
        else {
            Alert hata= new Alert(AlertType.ERROR);
            hata.setTitle("ERROR");
            hata.setHeaderText("TRY AGAIN");
            hata.setContentText("Mail or ur answer is wrong");
            hata.showAndWait();
        }
   
        
    } 
    
    @FXML
    void exitClick(ActionEvent event) {
    	Stage stage = (Stage)exitbttn.getScene().getWindow();
        stage.close();
    }
    
    
    
    
    
    @FXML
    void checkEvent(ActionEvent event) {
    
    }

    

    @FXML
    void initialize() {
      

    }

}
