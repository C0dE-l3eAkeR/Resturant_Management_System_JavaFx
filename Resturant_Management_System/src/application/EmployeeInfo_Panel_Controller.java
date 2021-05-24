package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DataBase.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmployeeInfo_Panel_Controller implements Initializable {

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
	private Button EditDetails = new Button();

	@FXML
	private ImageView imageView;

	@FXML
	private Button Update = new Button();

	static ObservableList<EmployeeInfo> empllist = AddEmployee_Panel_Controller.emplist;

	public void setText(String text) {

	}

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
	void handleEditDetailsButton(ActionEvent event) {
		//System.out.println("edt");
		Update.setVisible(true);
		FirstName.setEditable(true);
		LastName.setEditable(true);
		Salary.setEditable(true);
		Gender.setEditable(true);
		JoiningDate.setEditable(true);
		Phone.setEditable(true);
		EmployeeID.setEditable(true);
		Address.setEditable(true);
		NIDnumber.setEditable(true);
		Designation.setEditable(true);
		// EditDetails.setDisable(true);
		EditDetails.setVisible(false);

	}

	int index = EmployeeList_Controller.index;

	@FXML
	void handleUpdateButton(ActionEvent event) {

		//System.out.println("updt");
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
		String profilePictuePath = empllist.get(index).getProfilePicturePath();

		empllist.remove(index);
		empllist.add(index, new EmployeeInfo(id, designation, Joindate, salary, address, Nidnumber, firstName, lastName,
				phone, gender, profilePictuePath));

		// DataBase Action
		ArrayList<EmployeeInfo> employeeData = new ArrayList<EmployeeInfo>(empllist);
		try {
			DBConnect.writeSerialized(employeeData, "./src/DataBase/EmployeeInfo.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// EditDetails.setDisable(false);
		EditDetails.setVisible(true);
		// Update.setDisable(true);
		Update.setVisible(false);
	}

	public static final LocalDate LOCAL_DATE(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.parse(dateString, formatter);
		return localDate;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Update.setDisable(true);
		Update.setVisible(false);
		FirstName.setText(empllist.get(index).getFirstName());
		FirstName.setEditable(false);
		LastName.setText(empllist.get(index).getLastName());
		LastName.setEditable(false);
		Salary.setText(String.valueOf(empllist.get(index).getSalary()));
		Salary.setEditable(false);
		Gender.setValue(empllist.get(index).getGender());
		Gender.setEditable(false);
		SimpleDateFormat sdfmt = new SimpleDateFormat("MM-dd-yyyy");
		String strOutput = sdfmt.format(empllist.get(index).getJoiningDate());
		JoiningDate.setValue(LOCAL_DATE(strOutput));
		JoiningDate.setEditable(false);
		Phone.setText(String.valueOf(empllist.get(index).getPhone()));
		Phone.setEditable(false);
		EmployeeID.setText(String.valueOf(empllist.get(index).getEmployeeID()));
		EmployeeID.setEditable(false);
		NIDnumber.setText(String.valueOf(empllist.get(index).getNIDnumber()));
		NIDnumber.setEditable(false);
		Address.setText(empllist.get(index).getAddress());
		Address.setEditable(false);
		Designation.setText(empllist.get(index).getDesignation());
		Designation.setEditable(false);

		Image image = new Image(empllist.get(index).getProfilePicturePath());
		imageView.setImage(image);

	}

}
