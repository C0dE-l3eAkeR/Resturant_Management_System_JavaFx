package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DataBase.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Queqe_Panel_Controller implements Initializable {

	@FXML
	private TableView<PlaceOrder> OrderTable;

	@FXML
	private TableColumn<PlaceOrder, Integer> OrderIDT;

	@FXML
	private TableColumn<PlaceOrder, Integer> TableNoT;

	@FXML
	private TableColumn<PlaceOrder, String> WaiterIDT;

	@FXML
	private TableColumn<PlaceOrder, String> FoodIDT;

	@FXML
	private TableColumn<PlaceOrder, String> FoodNameT;

	@FXML
	private TableColumn<PlaceOrder, Integer> QuantityT;

	@FXML
	private TableColumn<PlaceOrder, Double> PriceT;

	@FXML
	private TableColumn<PlaceOrder, String> OrderTypeT;

	@FXML
	private Button Cancel;


	static ObservableList<PlaceOrder> FinalOrderList = Place_Order_Panel_Controller.FinalOrderList;

	void setAllOrder() {
		OrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		OrderIDT.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		FoodIDT.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
		FoodNameT.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
		QuantityT.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		PriceT.setCellValueFactory(new PropertyValueFactory<>("Price"));
		TableNoT.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
		OrderTypeT.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
		WaiterIDT.setCellValueFactory(new PropertyValueFactory<>("WaiterID"));
		
	}



	@FXML
	void handleCancelButton(ActionEvent event) {

		int index = OrderTable.getSelectionModel().selectedIndexProperty().get();
		FinalOrderList.remove(index);
		
		//DataBase in Action
		ArrayList<application.PlaceOrder> orderList = new ArrayList<PlaceOrder>(FinalOrderList);
		try {
			DBConnect.writeSerialized(orderList, "./src/DataBase/OrderList.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static ObservableList<Food> foodList = FoodItem_Panel_Controller.foodItemList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
	
	
		//DataBase In Action
		ArrayList<PlaceOrder> orderList = new ArrayList<PlaceOrder>();
		try {
			orderList=DBConnect.readSerialized("./src/DataBase/OrderList.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FinalOrderList = FXCollections.observableArrayList(orderList);
		
		setAllOrder();
		OrderTable.setItems(FinalOrderList);
		
	}

}
