package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Place_Order_Panel_Controller implements Initializable {

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
	private TextField OrderID;

	@FXML
	private TextField TableNo;

	@FXML
	private TextField WaiterID;

	@FXML
	private TextField FoodID;

	@FXML
	private TextField FoodName;

	@FXML
	private TextField Quantity;

	@FXML
	private TextField Price;

	@FXML
	private Button ADD;

	@FXML
	private Button Remove;

	@FXML
	private Button PlaceOrder;

	@FXML
	private ComboBox<String> OrderType;

	ObservableList<String> AllOrderType = FXCollections.observableArrayList("Home Delivery", "Table", "Parcel");
	//
	ObservableList<PlaceOrder> Order = FXCollections.observableArrayList();
	static ObservableList<PlaceOrder> FinalOrderList = FXCollections.observableArrayList();
	static PlaceOrder Slip;

	void countOrderId() throws SQLException {

	}

	PlaceOrder placeOrderFinal = new PlaceOrder();

	@FXML
	void hanbdleFoodName(ActionEvent event) {

	}

	ArrayList<String> afoodName = new ArrayList<String>();
	ArrayList<String> afoodID = new ArrayList<String>();
	ArrayList<Integer> aQuantity = new ArrayList<Integer>();
	ArrayList<Double> aPrice = new ArrayList<Double>();

	Double TotalPrice = 0.0;

	@FXML
	void handleADDButton(ActionEvent event) throws EmptyFieldException {

		try {
			ArrayList<String> foodName = new ArrayList<String>();
			ArrayList<String> foodID = new ArrayList<String>();
			ArrayList<Integer> quantity = new ArrayList<Integer>();
			ArrayList<Double> tPrice = new ArrayList<Double>();
			int orderID = Integer.parseInt(OrderID.getText());
			foodID.add(FoodID.getText());
			foodName.add(FoodName.getText());
			afoodID.add(FoodID.getText());
			afoodName.add(FoodName.getText());
			String quant = Quantity.getText();

			int table = Integer.parseInt(TableNo.getText());
			String waiterID = WaiterID.getText();
			String price = Price.getText();
			String orderType = OrderType.getValue();

			int quan = Integer.parseInt(quant);
			quantity.add(quan);
			aQuantity.add(quan);
			tPrice.add(Double.parseDouble(price));
			aPrice.add(Double.parseDouble(price));
			TotalPrice += Double.parseDouble(price);
			PlaceOrder placeOrder = new PlaceOrder(orderID, table, waiterID, foodID, foodName, quantity, tPrice, TotalPrice,
					orderType);
			placeOrderFinal = new PlaceOrder(orderID, table, waiterID, afoodID, afoodName, aQuantity, aPrice, TotalPrice,
					orderType);

			setAllOrder();
			// AllOrder = getAllOrder(oid);
			Order.add(placeOrder);

			FoodID.clear();
			FoodName.clear();
			Quantity.clear();
			Price.clear();
		} catch (Exception e) {
			if (FoodID.getText().isBlank() || FoodName.getText().isBlank() || Quantity.getText().isBlank()
					|| TableNo.getText().isBlank() || WaiterID.getText().isBlank()) {
				throw new EmptyFieldException("Fillup All Fields", e);
			}
		}
	}

	void setAllOrder() {
		OrderIDT.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
		FoodIDT.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
		FoodNameT.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
		QuantityT.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		PriceT.setCellValueFactory(new PropertyValueFactory<>("Price"));
		TableNoT.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
		OrderTypeT.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
		WaiterIDT.setCellValueFactory(new PropertyValueFactory<>("WaiterID"));
		OrderTable.setItems(Order);
	}

	@FXML
	void handleRemoveButton(ActionEvent event) {

		int index = OrderTable.getSelectionModel().selectedIndexProperty().get();
		
	
		TotalPrice-=	(aPrice.get(index)*	aQuantity.get(index));
		afoodID.remove(index);
		afoodName.remove(index);
		aPrice.remove(index);
		aQuantity.remove(index);
		placeOrderFinal = new PlaceOrder(Order.get(index).getOrderID(), Order.get(index).getTableNo(), Order.get(index).getWaiterID(), afoodID, afoodName, aQuantity, aPrice, TotalPrice,
				Order.get(index).getOrderType());
				Order.remove(index);
	}

	static ObservableList<Food> foodList = FoodItem_Panel_Controller.foodItemList;

	@FXML
	void handleFoodID(ActionEvent event) {
		try {
			String Id = FoodID.getText();
			if (Id.equals(""))
				return;

			String foodName = null;
			Double fprice = null;
			for (Food food1 : foodList) {
				if (food1.getFoodIdxx().equals(Id)) {
					foodName = food1.getFoodName();
					fprice = food1.getSellingPrice();
					break;
				}
			}

			// Set Item in All Text Field
			FoodName.setText(foodName);
			Price.setText(String.valueOf(fprice));
			Quantity.setText(String.valueOf("1"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please Type Correct Food ID", "Wrong!", 0);
		}
	}

	@FXML
	void handleOrderID(ActionEvent event) {

	}

	@FXML
	void handleOrderType(ActionEvent event) {

	}

	void copy(ObservableList<PlaceOrder> a, ObservableList<PlaceOrder> b) {

		PlaceOrder data = a.get(0);
		b.add(data);

	}

	static Stage stage1 = new Stage();

	@FXML
	void handlePlaceOrderButton(ActionEvent event) throws IOException {

		// copy(Order,FinalOrderList);
		FinalOrderList.add(placeOrderFinal);
		placeOrderFinal = new PlaceOrder();
		afoodName = new ArrayList<String>();
		afoodID = new ArrayList<String>();
		aQuantity = new ArrayList<Integer>();
		aPrice = new ArrayList<Double>();
		Order.clear();
		TotalPrice = 0.0;
		OrderID.setText(String.valueOf(FinalOrderList.size() + 1));

		// DataBase in Action
		ArrayList<application.PlaceOrder> orderList = new ArrayList<PlaceOrder>(FinalOrderList);
		DBConnect.writeSerialized(orderList, "./src/DataBase/OrderList.ser");

		JOptionPane.showMessageDialog(null, "Successfully Order Placed", "Welcome", 1);
		StackPane root = (StackPane) FXMLLoader.load(getClass().getResource("Order_Slip.fxml"));
		Scene scene = new Scene(root);
		scene.getWindow();
		stage1.setScene(scene);
		stage1.show();

	}

	@FXML
	void handlePrice(ActionEvent event) {

	}

	@FXML
	void handleQuantity(ActionEvent event) {
		if (Quantity.getText().equals("1"))
			return;
		else {
			int quan = Integer.parseInt(Quantity.getText());
			Double fprice = Double.parseDouble(Price.getText());
			Price.setText(String.valueOf(fprice * quan));
		}
	}

	@FXML
	void handleTableNo(ActionEvent event) {

	}

	@FXML
	void handleWaiterID(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		// DataBase in Action
		ArrayList<Food> readFood = new ArrayList<Food>();
		try {
			readFood = DBConnect.readSerialized("./src/DataBase/foodItems.ser");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		foodList = FXCollections.observableArrayList(readFood);

		OrderTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		OrderID.setText(String.valueOf(FinalOrderList.size() + 1));
		OrderType.setItems(AllOrderType);

	}

}
