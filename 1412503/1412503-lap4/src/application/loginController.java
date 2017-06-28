package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import application.QueryGetData;


public class loginController implements Initializable {
	 @FXML
	 private Button btnLogin;
	 @FXML
	 private Button btnCancel;
	 @FXML
	 private TextField textUsername;
	 @FXML
	 private PasswordField textPassword;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
   
	public void showLogin(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login");
		byte[] pass = null;
		try {
			if(QueryGetData.checkUserName(textUsername.getText())) //check username
				alert.setHeaderText("Incorrect username or password!!!");
			else {
				try {
					pass = QueryGetData.getData(textUsername.getText());
					//check password
					if(Arrays.equals(pass, DigestUtils.md5(textPassword.getText())) || Arrays.equals(pass, DigestUtils.sha1(textPassword.getText()))){
						alert.setHeaderText("Login successful!!!");
						textPassword.setText("");
						
						System.out.println("before try");
						try {
							Parent pane;
							pane = FXMLLoader.load(getClass()
							           .getResource("staff_list.fxml"));
							Scene scene = new Scene(pane);
							Stage stage = (Stage) btnLogin.getScene().getWindow();
							stage.setResizable(false);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());					        
							stage.setScene(scene);							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						alert.setHeaderText("Incorrect username or password!!!");
					}		
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.showAndWait();	  
	   }
	
	public void showCancel(ActionEvent event) {
		QueryGetData.closeConn();
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	   }
}
