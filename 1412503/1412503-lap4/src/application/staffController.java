package application;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class staffController implements Initializable {
	@FXML
	private TextField textId;
	@FXML
	private TextField textName;
	@FXML
	private TextField textUsername;
	@FXML
	private TextField textPass;
	@FXML
	private TextField textEmail;
	@FXML
	private TextField textSalary;
	
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnExit;	
	@FXML
	private TableView<staff> table_staff = new TableView<staff>();
	private final ObservableList<staff> data =
		        FXCollections.observableArrayList(
		        QueryGetData.getStaff()
		        );   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textId.setDisable(true);
		textName.setDisable(true);
		textUsername.setDisable(true);
		textPass.setDisable(true);
		textEmail.setDisable(true);
		textSalary.setDisable(true);
		TableColumn cid = new TableColumn("ID");
        cid.setMinWidth(143);
        cid.setCellValueFactory(
            new PropertyValueFactory<staff,String>("id")
        );

        TableColumn cname = new TableColumn("Name");
        cname.setMinWidth(270);
        cname.setCellValueFactory(
            new PropertyValueFactory<staff,String>("name")
        );

        TableColumn cemail = new TableColumn("Email");
        cemail.setMinWidth(270);
        cemail.setCellValueFactory(
            new PropertyValueFactory<staff,String>("email")
        );
        TableColumn csalary = new TableColumn("Salary");
        csalary.setMinWidth(183);
        csalary.setCellValueFactory(
            new PropertyValueFactory<staff,String>("salary")
        );
		
        table_staff.getColumns().addAll(cid, cname, cemail, csalary);
        
		table_staff.setItems(data);
		
	}
	
	public void showAdd(){
		textId.getStyleClass().removeAll(Collections.singleton("error"));   
		textUsername.getStyleClass().removeAll(Collections.singleton("error")); 
		textPass.getStyleClass().removeAll(Collections.singleton("error")); 
		textId.setDisable(false);
		textName.setDisable(false);
		textUsername.setDisable(false);
		textPass.setDisable(false);
		textEmail.setDisable(false);
		textSalary.setDisable(false);
		textId.clear();
		textName.clear();
		textUsername.clear();
		textPass.clear();
		textEmail.clear();
		textSalary.clear();
	}
	
	public void showSave(){
		Alert alert = new Alert(AlertType.INFORMATION);
		String id = textId.getText();
		String name = textName.getText();
		String user = textUsername.getText();
		String pass = textPass.getText();
		String email = textEmail.getText();
		String salary = textSalary.getText();
		Boolean flag = true;
		if(id.isEmpty()){
			textId.getStyleClass().add("error");
			flag = false;
		}
		else{
			textId.getStyleClass().removeAll(Collections.singleton("error"));   
		}
		if(QueryGetData.checkId_Username(id, user)==0){
			alert.setHeaderText("ID already exists!! ");
			alert.showAndWait();
			flag = false;
		}
		if(QueryGetData.checkId_Username(id, user)==1){
			alert.setHeaderText("Username already exists!!");
			alert.showAndWait();
			flag = false;
		}
		if(user.isEmpty()){
			textUsername.getStyleClass().add("error");
			flag = false;
		}
		else{
			textUsername.getStyleClass().removeAll(Collections.singleton("error"));   
		}
		if(pass.isEmpty()){
			textPass.getStyleClass().add("error");
			flag = false;
		}
		else{
			textPass.getStyleClass().removeAll(Collections.singleton("error"));   
		}
		if(flag){
			QueryGetData.addStaff(id, name, email, salary, user, pass);
			alert.setTitle("Add staff");
			alert.setHeaderText("Add staff successfull!!");
			alert.showAndWait();
			try {
				Parent pane;
				pane = FXMLLoader.load(getClass()
				           .getResource("staff_list.fxml"));
				Scene scene = new Scene(pane);
				Stage stage = (Stage) btnSave.getScene().getWindow();
				stage.setResizable(false);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());					        
				stage.setScene(scene);							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void showExit(){
		try {
			Parent pane;
			pane = FXMLLoader.load(getClass()
			           .getResource("login.fxml"));
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnExit.getScene().getWindow();
			stage.setScene(scene);							
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
