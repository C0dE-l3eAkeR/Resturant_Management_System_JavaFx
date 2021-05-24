package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import DataBase.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeeList_Controller implements Initializable {

	@FXML
	private TableView<EmployeeInfo> EmployeeTable;

	@FXML
	private TableColumn<EmployeeInfo, Integer> EmployeeID;

	@FXML
	private TableColumn<EmployeeInfo, String> Designation;

	@FXML
	private TableColumn<EmployeeInfo, Date> JoiningDate;

	@FXML
	private TableColumn<EmployeeInfo, Double> Salary;

	@FXML
	private Button Delete;

	static ObservableList<EmployeeInfo> emplist = AddEmployee_Panel_Controller.emplist;

	@FXML
	private Button ViewDetails;

	public static int index;

	@FXML
	void handleVieewDetailsButton(ActionEvent event) throws IOException {
		index = EmployeeTable.getSelectionModel().selectedIndexProperty().get();

		Stage stage = new Stage();
		StackPane root = (StackPane) FXMLLoader.load(getClass().getResource("EmployeeInfo_Panel.fxml"));
		Scene scene = new Scene(root);
		scene.getWindow();
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void HandleEmployeeTable(ActionEvent event) {

	}

	@FXML
	void handleDeleteButton(ActionEvent event) {
		index = EmployeeTable.getSelectionModel().selectedIndexProperty().get();
		AddEmployee_Panel_Controller.emplist.remove(index);

		// DataBase in Action
		ArrayList<EmployeeInfo> employeeData = new ArrayList<EmployeeInfo>(AddEmployee_Panel_Controller.emplist);
		try {
			DBConnect.writeSerialized(employeeData, "./src/DataBase/EmployeeInfo.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	int flag = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (flag == 0) {
			ArrayList<EmployeeInfo> employeeData = new ArrayList<EmployeeInfo>();
			try {
				employeeData = DBConnect.readSerialized("./src/DataBase/EmployeeInfo.ser");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			AddEmployee_Panel_Controller.emplist = FXCollections.observableArrayList(employeeData);
			flag = 1;
		}

		// This Function Only For Employee Table
		// Create Multiple Row Selected option
		EmployeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	
		// Database Action
		// DBAction dbaction = new DBAction();

		EmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
		Designation.setCellValueFactory(new PropertyValueFactory<>("Designation"));
		JoiningDate.setCellValueFactory(new PropertyValueFactory<>("JoiningDate"));
		Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

		EmployeeTable.setItems(AddEmployee_Panel_Controller.emplist);

	}

}
