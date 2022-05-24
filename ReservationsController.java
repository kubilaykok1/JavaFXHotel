package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ReservationsController {

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
    private TableView<ObservableList> data1;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private TextField txtID;
    
    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;
    
    @FXML
    private TextField txt31;

    @FXML
    void ClickBack(ActionEvent event) {
    	try {
    		final Stage stage1 = (Stage) bttn2.getScene().getWindow();
    		stage1.close();
    		
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("StaffHome.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 600, 309);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);	
			scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setTitle("Staff HomePage");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

    private ObservableList<ObservableList> data;
    public void buildData() {
    	
    	
        try {
        	 Connection connection;
             SqlConnection helper = new SqlConnection();
            
             connection = helper.getConnection();
            
            
            data = FXCollections.observableArrayList();
            String SQL = "SELECT * from reservatioons";

            ResultSet rs = connection.createStatement().executeQuery(SQL);
 

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
 
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
 
                data1.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            
            while (rs.next()) {
              
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                  
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
 
            }
            data1.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        
    }
    
    @FXML
    void GetInfo(MouseEvent event) {
    	TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
      	int index = pos.getRow();
      	ObservableList rowList = (ObservableList) data1.getItems().get(index);
      	txt31.setText(rowList.get(0).toString());
      	txt1.setText(rowList.get(1).toString());
      	txt2.setText(rowList.get(2).toString());
      	txt3.setText(rowList.get(3).toString());
   
//      	ArrayList<Product> p = new ArrayList<>(taview.getSelectionModel().getSelectedItems());
    }
    
    
    
    public void deleteReservation(String ID)
    {
        Connection connection;
        SqlConnection helper = new SqlConnection();
        PreparedStatement statement;
     

        
        try{
        	 TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
         	int index = pos.getRow();
         	String selected = data1.getItems().get(index).toString();
         	selected = selected.substring(1, selected.indexOf(","));
         	
         	
            connection= helper.getConnection();
            statement = connection.prepareStatement("delete from reservatioons where ReservationID =?");
            statement.setString(1,selected);
       
           
 
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }
    @FXML
    void bttnCancel(ActionEvent event) {
    	deleteReservation(txt31.getText());
    	buildData();
    }
    
    public void update()
    {		
   	 TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
   	int index = pos.getRow();
   	String selected = data1.getItems().get(index).toString();
   	selected = selected.substring(1, selected.indexOf(","));
    	
    	 
    	 Connection connection;
         SqlConnection helper = new SqlConnection();
         PreparedStatement statement;
         try{
        	 
             connection = helper.getConnection();
             String sql = "UPDATE  `reservatioons` SET "
             		+ "`Extras`=?, "  		        		
             		+ "`EndDate`= ? WHERE ReservationID = '"+selected+"'";
             	
             statement = connection.prepareStatement(sql);   
            
             statement.setString(1,txt1.getText());
             
             statement.setString(4, txt3.getText());
         

            
             statement.executeUpdate();
             buildData();             
         }catch (SQLException exception){
             helper.showErrorMessage(exception);
         }
    }
    
    

    @FXML
    void bttnUpdate(ActionEvent event) {
    	update();
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Hotel Information");
    	alert.setContentText("Updated Reservation!!");
    	alert.show();
    	buildData();
    }

    @FXML
    void initialize() {
    	buildData();
    	
    }

}

