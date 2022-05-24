package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class MembersController {

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
    private Button transmit;

    
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
    
    int myIndex;
    String RoomName;
    PreparedStatement pst;
    Connection con;
    
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
             String sql = "UPDATE  `members` SET "
             		+ " `Mail`=?, "
             		+ "`MemberName`=?,"
             		+ "`MemberSurname`=?  WHERE MemberID ='"+selected+"'";
             	
             statement = connection.prepareStatement(sql);   
            
             statement.setString(1,txt3.getText());
             statement.setString(2, txt1.getText());
             statement.setString(3, txt2.getText());
             
       
            
            
             statement.executeUpdate();
             buildData();             
         }catch (SQLException exception){
             helper.showErrorMessage(exception);
         }
    }
    
    
    
    @FXML
    void ClickBack(ActionEvent event) {
    	try {
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
            stage.setTitle("Edit Members");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
    
 public void insert(String firstname,String lastname,String email,String birthdate,String passwrd,String Gender) {
    	

        Connection connection;
        SqlConnection con= new SqlConnection();
        PreparedStatement statement;
       
        try{
       
        	connection = con.getConnection();
            String sql="INSERT INTO members(MemberName,MemberSurname,Mail,BirthDate,passwrd,Gender) VALUES (?,?,?,?,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1,firstname);
            statement.setString(2,lastname);
            statement.setString(3,email);
            statement.setString(4,birthdate);
            statement.setString(5,passwrd);
            statement.setString(6,Gender);
         
            
            statement.executeUpdate();
    }catch (SQLException exception){
        con.showErrorMessage(exception);
    }
    }

    
    

    @FXML
    void bttnAdd(ActionEvent event) {
    		insert(txt1.getText(),txt2.getText(),txt3.getText(),date1.getValue().toString(),txt4.getText(),txt5.getText());
    		Alert alert=new Alert(AlertType.INFORMATION);
    		alert.setTitle("Member Registation");
    		 
    		alert.setHeaderText("Member Registration");
    		alert.setContentText("Record Added!");
    		 
    		alert.showAndWait();
    		buildData();
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
    		deleteReservation();
    		buildData();
    	}
    	
    }

    @FXML
    void GetInfo(MouseEvent event) {
    	TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
      	int index = pos.getRow();
      	ObservableList rowList = (ObservableList) data1.getItems().get(index);
      	txt1.setText(rowList.get(1).toString());
      	txt2.setText(rowList.get(2).toString());
      	txt3.setText(rowList.get(3).toString());
      	txt4.setText(rowList.get(5).toString());
      	txt5.setText(rowList.get(6).toString());
      	txt6.setText(rowList.get(0).toString());
      	date1.setAccessibleText(rowList.get(4).toString());
//      	ArrayList<Product> p = new ArrayList<>(taview.getSelectionModel().getSelectedItems());
    }
    @FXML
    void transitData(ActionEvent event) {
    	TablePosition pos = (TablePosition) data1.getSelectionModel().getSelectedCells().get(0);
      	int index = pos.getRow();
      	ObservableList rowList = (ObservableList) data1.getItems().get(index);
      	txt1.setText(rowList.get(1).toString());
      	txt2.setText(rowList.get(2).toString());
      	txt3.setText(rowList.get(3).toString());
      	txt4.setText(rowList.get(5).toString());
      	txt5.setText(rowList.get(6).toString());
      	date1.setAccessibleText(rowList.get(4).toString());
//      	ArrayList<Product> p = new ArrayList<>(taview.getSelectionModel().getSelectedItems()
    }
    

    @FXML
    void bttnUpdate(ActionEvent event) {
	
    		update();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Member Registration");
 
            alert.setHeaderText("Member Registration");
            alert.setContentText("Updated!");
 
            alert.showAndWait();
            buildData();
        }
     
    
    private ObservableList<ObservableList> data;
    public void buildData() {
    	
    	
        try {
        	 Connection connection;
             SqlConnection helper = new SqlConnection();
            
             connection = helper.getConnection();
            
            
            data = FXCollections.observableArrayList();
            //SQL FOR SELECTING ALL OF ESTATE
            String SQL = "SELECT * from members";
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
    public void deleteReservation()
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
            statement = connection.prepareStatement("delete from members where MemberID =?");
            statement.setString(1,selected);
       
           
            
            statement.executeUpdate();
        }catch (SQLException exception){
            helper.showErrorMessage(exception);
        }
    }
    @FXML
    void initialize() {
    	buildData();

    }

}
