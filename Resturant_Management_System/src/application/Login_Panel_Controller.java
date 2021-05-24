package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DataBase.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_Panel_Controller implements Initializable, Serializable {

	@FXML
	private TextField userNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button enter;

	private Admin admin = Admin.getAdmin();

	static Stage stage = new Stage();

	@FXML
	void handleEnterButton(ActionEvent event) throws IOException {

		String username = userNameField.getText();
		String password = passwordField.getText();

		Admin adminCheck = new Admin(username, password);

		int i = 0;

		String usern = adminCheck.getUserName();
		String pass = adminCheck.getPassword();
		;

		if (admin.getUserName().equals(usern) && admin.getPassword().equals(pass)) {
			Parent root = FXMLLoader.load(getClass().getResource("Base_Panel.fxml"));
			Scene scene = new Scene(root);

			stage.setTitle("Admin Panel");
			stage.setScene(scene);

			Main.stage.close();
			stage.show();
			i = 1;

		} else {
			i = 0;
		}

		if (i == 1) {
			JOptionPane.showMessageDialog(null, "Welcome to Admin Panel", "Welcome", 1);
		} else {
			JOptionPane.showMessageDialog(null, "Your id or password Wrong!", "Wrong Information", 0);
		}

	}

	@FXML
	void handlepasswordField(ActionEvent event) {

	}

	@FXML
	void handleuserNameField(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//			try {
//			DBConnect.writeSerializedObj(new Admin("user","pass"), "./src/DataBase/Admin.ser");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			admin = DBConnect.readSerializedObj("./src/DataBase/Admin.ser");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
