package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Base_Panel_Controller implements Initializable {

	@FXML
	private Button placeOrder;

	@FXML
	private Button addEmployee;

	@FXML
	private Button employeeList;

	@FXML
	private Button addFoodItem;

	@FXML
	private Button OverView;

	@FXML
	private Button logOut;
	@FXML
	private BorderPane BasePanel;

	@FXML
	private Button Status;

	@FXML
	void handleStatusButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Queue.fxml"));
		BasePanel.setCenter(root);
	}

	@FXML
	void handleAddEmployeeButton(ActionEvent event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("AddEmployee_Panel.fxml"));
		BasePanel.setCenter(root);

	}

	@FXML
	void handleAddFoodItemButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("FoodItem_Panel.fxml"));
		BasePanel.setCenter(root);

	}

	@FXML
	void handleEmployeeListButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeList_Panel.fxml"));
		BasePanel.setCenter(root);
	}

	@FXML
	void handleOverViewButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("OverView.fxml"));
		BasePanel.setCenter(root);
	}

	@FXML
	void handleLogOutButton(ActionEvent event) throws IOException {

		JOptionPane.showMessageDialog(null, "Log Out Successful!", "Log Out", 1);
		Login_Panel_Controller.stage.close();
		Main.stage.show();

	}

	@FXML
	void handlePlaceOrederButton(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Place_Order_Panel.fxml"));
		BasePanel.setCenter(root);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("OverView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasePanel.setCenter(root);

	}

}