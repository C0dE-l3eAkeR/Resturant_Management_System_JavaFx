package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class OverView_Panel_Controller implements Initializable {

	@FXML
	private TextField TotalEmployees;

	@FXML
	private TextField TotalFoodItems;

	@FXML
	private TextField PendingOrders;

	@FXML
	private TextField OrderToday;

	@FXML
	private Label time;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	//DataBase in action
		ArrayList<EmployeeInfo> employeeData = new ArrayList<EmployeeInfo>();
		ArrayList<Food> readFood = new ArrayList<Food>();
		ArrayList<PlaceOrder> orderList1 = new ArrayList<PlaceOrder>();
		
		try {
			employeeData = DBConnect.readSerialized("./src/DataBase/EmployeeInfo.ser");
			readFood = DBConnect.readSerialized("./src/DataBase/foodItems.ser");
			orderList1=DBConnect.readSerialized("./src/DataBase/OrderList.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EmployeeList_Controller.emplist = FXCollections.observableArrayList(employeeData);
		FoodItem_Panel_Controller.foodItemList = FXCollections.observableArrayList(readFood);
		Queqe_Panel_Controller.FinalOrderList = FXCollections.observableArrayList(orderList1);
		
			
		
		TotalEmployees.setText(String.valueOf(EmployeeList_Controller.emplist.size()));
		TotalEmployees.setEditable(false);
		TotalFoodItems.setText(String.valueOf(FoodItem_Panel_Controller.foodItemList.size()));
		TotalFoodItems.setEditable(false);
		PendingOrders.setText(String.valueOf(Queqe_Panel_Controller.FinalOrderList.size()));
		PendingOrders.setEditable(false);
		OrderToday.setText(String.valueOf(PlaceOrder.cnt));
		OrderToday.setEditable(false);

		
		
		
		Timeline setClock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			LocalTime timeLine = LocalTime.now();
			time.setText(timeLine.getHour() + ":" + timeLine.getMinute() + ":" + timeLine.getSecond());
		}), new KeyFrame(Duration.seconds(1)));
		setClock.setCycleCount(Animation.INDEFINITE);
		setClock.play();
	}
	
}
