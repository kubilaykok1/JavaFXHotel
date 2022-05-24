package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;  
import javafx.animation.PauseTransition;  
import javafx.animation.RotateTransition;  
import javafx.animation.ScaleTransition;  
import javafx.animation.SequentialTransition;  
import javafx.animation.TranslateTransition;  
import javafx.application.Application;  
import javafx.scene.Group;  
import javafx.scene.Scene;  
import javafx.scene.paint.Color;  
import javafx.scene.shape.Polygon;  
import javafx.stage.Stage;  
import javafx.util.Duration;  


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField emailtxt;
    
    @FXML
    private ImageView image1;


    @FXML
    private Button girisbttn;

    @FXML
    private PasswordField psswordtxt;

    @FXML
    private Hyperlink sifrelink;

    @FXML
    private Hyperlink uyelink;
    
    @FXML
    private Button bttn2;
    
    @FXML
    private AnchorPane anchor_sol;
    
    @FXML
    private Button staffbttn;
    
    
	private static final String DATABASE_URL = "jdbc:mysql://localhost/newhotel";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "";

	

    @FXML
    void ýmageExit(MouseEvent event) {
    	System.exit(0);
    }

  public int LoginCheck(String Mail,String passwrd ){
    	
        Connection connection;
        SqlConnection con= new SqlConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        try{
        	
            connection = con.getConnection();
            statement = connection.prepareStatement("select * from members where Mail=? and passwrd=? ");
            statement.setString(1,Mail);
            statement.setString(2,passwrd);
         
            
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
  
  public int AdminCheck(String Mail, String Password){
  	
      Connection connection;
      SqlConnection con= new SqlConnection();
      PreparedStatement statement;
      ResultSet resultSet;
      try{
      	
          connection = con.getConnection();
          statement = connection.prepareStatement("select * from administration where Mail=? and passwrd=?");
          statement.setString(1,Mail);
          statement.setString(2,MD5.MD5(Password));
         
       
          
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
    void Giris(ActionEvent event) throws SQLException {
    	int control = LoginCheck(emailtxt.getText(),psswordtxt.getText());
    	if (control==0) {
    		
    		
    		try{
               
    			 FXMLLoader fxmlLoader = new FXMLLoader();
    	            fxmlLoader.setLocation(getClass().getResource("Anasayfa.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 327, 362);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }catch (IOException exception) {
                System.out.println("Error!!!");
                System.out.println("Error : "+exception.getMessage());
            }
            Stage stage = (Stage)girisbttn.getScene().getWindow();
            stage.close();
        } else {
            Alert hata= new Alert(AlertType.ERROR);
            hata.setTitle("ERROR");
            hata.setHeaderText("TRY AGAIN");
            hata.setContentText("Username or Password wrong");
            hata.showAndWait();
        }

      
        
        
    }

 
	@FXML
    void SifreHatýrlat(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("SifreHatirlat.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 413, 293);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setTitle("Þifremi Unuttum");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void UyeOl(ActionEvent event) {
    	try {
    		
    		FadeTransition kaybol= new FadeTransition(Duration.seconds(5),anchor_sol);
    		kaybol.setFromValue(0);
    		kaybol.setToValue(6);
    		kaybol.play();
       
    		
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("UyeKayit.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 498, 468);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setTitle("Üye Kayýt");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void staffgiris(ActionEvent event) {
    	int control = AdminCheck(emailtxt.getText(),psswordtxt.getText());
    	if (control==0 ) {
    		
    		
        	try {    		
        		FadeTransition kaybol= new FadeTransition(Duration.seconds(5),anchor_sol);
        		kaybol.setFromValue(0);
        		kaybol.setToValue(6);
        		kaybol.play();
            
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("StaffHome.fxml"));
                /*
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                Scene scene = new Scene(fxmlLoader.load(), 630, 400);
                Stage stage = new Stage();
                stage.initStyle(StageStyle.TRANSPARENT);	
    			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
                stage.setTitle("Staff");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
    			e.printStackTrace();
    		}
            Stage stage = (Stage)girisbttn.getScene().getWindow();
            stage.close();
        } else {
            Alert hata= new Alert(AlertType.ERROR);
            hata.setTitle("ERROR");
            hata.setHeaderText("TRY AGAIN");
            hata.setContentText("Username or Password wrong");
            hata.showAndWait();
        }
    	
    	
  
    }

 

    
    @FXML
    void initialize() {    	 	
    	Polygon poly = new Polygon();  
        
        //Setting points for the polyogn   
        poly.getPoints().addAll(new Double[] {320.0,270.0,270.0,220.0,270.0,270.0,320.0,120.0,370.0,270.0,370.0,220.0});  
         
        //Setting Color and Stroke properties for the polygon    
        poly.setFill(Color.LIMEGREEN);  
        poly.setStroke(Color.BLACK);  
      
        //Setting durations for the transitions  
        Duration dur1 = Duration.millis(1000);  
        Duration dur2 = Duration.millis(500);  
      
          
        //Setting the pause transition  
        PauseTransition pause = new PauseTransition(Duration.millis(1000));  
          
        //Setting the fade transition   
        FadeTransition fade = new FadeTransition(dur2,anchor_sol);  
        fade.setFromValue(1.0f);  
        fade.setToValue(0.3f);  
        fade.setCycleCount(2);  
        fade.setAutoReverse(true);  
          
        //Setting Translate transition  
        TranslateTransition translate = new TranslateTransition(dur1,anchor_sol);  
        translate.setToX(-150f);  
        translate.setCycleCount(2);  
        translate.setAutoReverse(true);  
          
        //Setting Rotate Transition   
        RotateTransition rotate = new RotateTransition(dur2,anchor_sol);  
        rotate.setByAngle(180f);  
        rotate.setCycleCount(4);  
        rotate.setAutoReverse(true);  
          
        //Setting Scale Transition   
     /*  ScaleTransition scale = new ScaleTransition(dur1,anchor_sol);  
        scale.setByX(1.5f);  
        scale.setByY(1.2f);  
        scale.setCycleCount(2);  
        scale.setAutoReverse(true);*/
          
        //Instantiating Sequential Transition class by passing the list of transitions into its constructor  
        SequentialTransition seqT = new SequentialTransition (poly,rotate, pause, fade, translate);  
          
        //playing the transition   
        seqT.play();  
           
     		
    }

}
