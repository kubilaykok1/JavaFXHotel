package application;

import java.awt.Window;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class StaffEditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bttnAdd;

    @FXML
    private Button bttnBack;

    @FXML
    private Button bttnDelete;

    @FXML
    private Button bttnUpdate;

    @FXML
    private TableView<ObservableList> data1;

    @FXML
    private DatePicker date1;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt4;

    @FXML
    private TextField txt5;

    @FXML
    private TextField txt6;

    @FXML
    void ClickBack(ActionEvent event) {
    	try {
    		final Stage stage1 = (Stage) bttnBack.getScene().getWindow();
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
            stage.setTitle("Edit Personel");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}	
    }
    	
    	public void insert(String firstname,String lastname,String phone,String identityNo,String Mail,String position,String BirthDate) {
        	

            Connection connection;
            SqlConnection con= new SqlConnection();
            PreparedStatement statement;
           
            try{
           
                connection = con.getConnection();
                String sql="INSERT INTO staff(StaffName,StaffSurname,StaffPhone,IdentityNo,Mail,Position,BirthDate) VALUES (?,?,?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1,firstname);
                statement.setString(2,lastname);
                statement.setString(3,phone);
                statement.setString(4, identityNo);
                statement.setString(5,Mail);
                statement.setString(6,position);
                statement.setString(7,BirthDate);
             
                
                statement.executeUpdate();
        }catch (SQLException exception){
            con.showErrorMessage(exception);
        }
            	
    }
    	
    	 public void deleteStaff()
    	    {
    	        Connection connection; 
    	        SqlConnection helper = new SqlConnection();
    	        PreparedStatement statement;
    	        TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
    	    	int index = pos.getRow();
    	    	String selected = data1.getItems().get(index).toString();
    	    	selected = selected.substring(1, selected.indexOf(","));
    	    	System.out.println(selected);

    	        // this gives the value in the selected cell:
    	        try{
    	            connection= helper.getConnection();
    	            statement = connection.prepareStatement("delete from staff where StaffID =?");
    	            statement.setString(1,selected);
    	       
    	           
    	            
    	            statement.executeUpdate();
    	        }catch (SQLException exception){
    	            helper.showErrorMessage(exception);
    	        }
    	    }
    	 	
    	
    	 private ObservableList<ObservableList> data;
    	    public void buildData() {
    	    	
    	    	
    	        try {
    	        	 Connection connection;
    	             SqlConnection helper = new SqlConnection();
    	            
    	             connection = helper.getConnection();
    	            
    	            
    	            data = FXCollections.observableArrayList();
    	            //SQL FOR SELECTING ALL OF ESTATE
    	            String SQL = "SELECT * from staff";
    	            //ResultSet
    	            ResultSet rs = connection.createStatement().executeQuery(SQL);
    	 
    	            /**
    	             * ************
    	             * TABLE COLUMN ADDED DYNAMICALLY *
    	             ***********
    	             */
    	            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
    	                //We are using non property style for making dynamic table
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
    	                //Iterate Row
    	                ObservableList<String> row = FXCollections.observableArrayList();
    	                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
    	                    //Iterate Column
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
    void bttnAdd(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation");
    	alert.setContentText("Are u sure?");
    	
    	ButtonType btn1= new ButtonType("Yes",ButtonData.OK_DONE);
    	ButtonType btn2= new ButtonType("No",ButtonData.CANCEL_CLOSE);
    	
    	alert.getButtonTypes().setAll(btn1,btn2);
    	Optional<ButtonType> sonuc = alert.showAndWait();
    	
    	if(sonuc.get()==btn1)
    	{insert(txt1.getText(),txt2.getText(),txt3.getText(),txt4.getText(),txt5.getText(),txt6.getText(),date1.getValue().toString());
    	buildData();
    	}
    }

    @FXML
    void bttnDelete(ActionEvent event) {
    	 Alert alert1= new Alert(AlertType.CONFIRMATION);
         alert1.setTitle("Confirmation");
         alert1.setContentText("Are you sure you want to delete it?");
     	
     	
     	ButtonType btn1= new ButtonType("Yes",ButtonData.YES);
     	ButtonType btn2= new ButtonType("No",ButtonData.CANCEL_CLOSE);
     	
         alert1.getButtonTypes().setAll(btn1, btn2);
     	
     	Optional<ButtonType> sonuc= alert1.showAndWait();
     	
     	if(sonuc.get()==btn1)    	
     	{
     		deleteStaff();
     		buildData();
     	}
     	
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
             String sql = "UPDATE  `staff` SET "
             		+ " `Mail`=? "
             		+ "`StaffName=?`"
             		+ "`StaffSurname=?`"           		
             		+ "`Position=? `"
             		+ "`Mail=? `"
             		+ "`StaffPhone=? `"
             		+ "WHERE StaffID ='"+selected+"' ";
             	
             statement = connection.prepareStatement(sql);   
            
             statement.setString(1,txt5.getText());
             statement.setString(2, txt1.getText());
             statement.setString(3, txt2.getText());
             statement.setString(4, txt6.getText());
             statement.setString(5, txt5.getText());
             statement.setString(6, txt3.getText());
             
       
            
            
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
        	alert.setContentText("Updated!!");
        	alert.show();
        	buildData();
    }
    
    @FXML
    void GetInfo(MouseEvent event) {
    	TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
      	int index = pos.getRow();
      	ObservableList rowList = (ObservableList) data1.getItems().get(index);
      	txt1.setText(rowList.get(1).toString());
      	date1.setValue((LocalDate) rowList.get(4));
      	txt3.setText(rowList.get(3).toString());
      	txt4.setText(rowList.get(5).toString());
      	txt5.setText(rowList.get(6).toString());
//      	ArrayList<Product> p = new ArrayList<>(taview.getSelectionModel().getSelectedItems());
    }

    
    
    
    @FXML
    void initialize() {
    
    	buildData();
    }

}
