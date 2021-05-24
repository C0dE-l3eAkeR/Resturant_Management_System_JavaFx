package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DataBase.DBConnect;
import HandleExceptions.EmptyFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddEmployee_Panel_Controller implements Initializable {

	@FXML
	private TextField FirstName;

	@FXML
	private TextField LastName;

	@FXML
	private TextField Designation;

	@FXML
	private TextField Salary;

	@FXML
	private ComboBox<String> Gender;

	@FXML
	private DatePicker JoiningDate;

	@FXML
	private TextField Address;

	@FXML
	private TextField Phone;

	@FXML
	private TextField EmployeeID;

	@FXML
	private TextField NIDnumber;

	@FXML
	private Button AddPicture;

	@FXML
	private ImageView imageView;

	private String profilePictuePath = null;

	ObservableList<String> Genderlist = FXCollections.observableArrayList("Male", "Female", "Others");
	static ObservableList<EmployeeInfo> emplist = FXCollections.observableArrayList();

	@FXML
	void handleAddress(ActionEvent event) {

	}

	@FXML
	void handleDesignation(ActionEvent event) {

	}

	@FXML
	void handleEmployeeID(ActionEvent event) {

	}

	@FXML
	void handleFirstName(ActionEvent event) {

	}

	@FXML
	void handleGender(ActionEvent event) {

	}

	@FXML
	void handleJoiningDate(ActionEvent event) {

	}

	@FXML
	void handleLastName(ActionEvent event) {

	}

	@FXML
	void handleNIDnumber(ActionEvent event) {

	}

	@FXML
	void handlePhone(ActionEvent event) {

	}

	@FXML
	void handleSalary(ActionEvent event) {

	}

	@FXML
	void handleAddPictureButton(ActionEvent event) throws FileNotFoundException {
		FileChooser fileChooser = new FileChooser();
		Stage picStage = (Stage) this.AddPicture.getScene().getWindow();
		File imageFile = fileChooser.showOpenDialog(picStage);

		System.out.println(imageFile.getAbsolutePath());
		if (imageFile.exists()) {
			this.profilePictuePath = imageFile.toURI().toString();
			Image image = new Image(profilePictuePath);
			imageView.setImage(image);
		}

	}

	@FXML
	void handleAddEmployeeButton(ActionEvent event) throws IOException, EmptyFieldException {
		try {
			int id = Integer.parseInt(EmployeeID.getText());
			String designation = this.Designation.getText();
			Date Joindate = Date.valueOf(JoiningDate.getValue());
			double salary = Double.parseDouble(Salary.getText());
			String firstName = FirstName.getText();
			String lastName = LastName.getText();
			int phone = Integer.parseInt(Phone.getText());
			String gender = Gender.getValue();
			String Nidnumber = NIDnumber.getText();
			String address = Address.getText();
 

				// Create Employee Object to get Insert Value For Employee Table
				EmployeeInfo employee = new EmployeeInfo(id, designation, Joindate, salary, address, Nidnumber, firstName,
						lastName, phone, gender, profilePictuePath);
				// dbaction.InsertEmployee(employee);

				emplist.add(employee);
				JOptionPane.showMessageDialog(null, "Congrats! Employee has been Added");

				// Database Action
				ArrayList<EmployeeInfo> employeeData = new ArrayList<EmployeeInfo>(emplist);
				DBConnect.writeSerialized(employeeData, "./src/DataBase/EmployeeInfo.ser");

				// Clear All Field Text After Added
				EmployeeID.clear();
				Designation.clear();
				FirstName.clear();
				LastName.clear();
				Phone.clear();
				Gender.setValue("Male");

				NIDnumber.clear();
				Address.clear();
				Salary.clear();
				imageView.setImage(null);
				JoiningDate.setValue(null);
			
		} catch (Exception e) {

			if (EmployeeID.getText().isBlank() || Designation.getText().isBlank() || Designation.getText().isBlank() || JoiningDate.getValue()!= null || Salary.getText().isBlank()
					 || FirstName.getText().isBlank() || LastName.getText().isBlank() || NIDnumber.getText().isBlank() || Address.getText().isBlank() || profilePictuePath.isBlank()) {
				
				JOptionPane.showMessageDialog(null, "Wrong! Please Check All Information!");
				throw new EmptyFieldException("Please fill up all field!", e);
			
			}
			
			
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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

		emplist = FXCollections.observableArrayList(employeeData);

		Gender.setValue("Male");
		Gender.setItems(Genderlist);

	}

}
