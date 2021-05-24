package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DataBase.DBConnect;
import HandleExceptions.EmptyFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FoodItem_Panel_Controller implements Initializable {

	@FXML
	private TextField FoodID;

	@FXML
	private TextField BuyingPrice;

	@FXML
	private TextField FoodName;

	@FXML
	private TextField SellingPrice;

	@FXML
	private TableView<Food> FooditemsTable;

	@FXML
	private TableColumn<Food, Integer> FoodIDT;

	@FXML
	private TableColumn<Food, String> FoodNameT;

	@FXML
	private TableColumn<Food, Double> BuyingPriceT;

	@FXML
	private TableColumn<Food, Double> SellingPriceT;

	@FXML
	private Button ADD;

	@FXML
	private Button Edit;

	@FXML
	private Button Delete;

	@FXML
	private Button Update;

	private double Profit;

	static ObservableList<Food> foodItemList = FXCollections.observableArrayList();

	void SetAllFood() {

		FooditemsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		FoodIDT.setCellValueFactory(new PropertyValueFactory<>("FoodIdxx"));
		FoodNameT.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
		BuyingPriceT.setCellValueFactory(new PropertyValueFactory<>("ByingPrice"));
		SellingPriceT.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
		// Profit.setCellValueFactory(new PropertyValueFactory<>("Profit"));

		FooditemsTable.setItems(foodItemList);

	}

	@FXML
	void handleADDButton(ActionEvent event) throws EmptyFieldException {

		try {
			String Foodid = FoodID.getText();
			String FooDName = FoodName.getText();
			String BuyPrice = BuyingPrice.getText();
			String SellPrice = SellingPrice.getText();
			Double Buyprice = Double.parseDouble(BuyPrice);
			Double Sellprice = Double.parseDouble(SellPrice);

			Food food = new Food(Foodid, FooDName, Buyprice, Sellprice, Sellprice - Buyprice);
			JOptionPane.showMessageDialog(null, "New Food Item Added", "OK", 1);

			FoodID.clear();
			FoodName.clear();
			BuyingPrice.clear();
			SellingPrice.clear();
			foodItemList.add(food);
			ArrayList<Food> dataList = new ArrayList<Food>(foodItemList);
			try {
				DBConnect.writeSerialized(dataList, "./src/DataBase/foodItems.ser");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			if (FoodID.getText().isBlank() || FoodName.getText().isBlank() || BuyingPrice.getText().isBlank()
					|| SellingPrice.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Fillup All Field");
				throw new EmptyFieldException("Please Fillup All Fields", e);

			}
		}
	}

	int index;

	@FXML
	void handleBuyingPrice(ActionEvent event) {

	}

	@FXML
	void handleDeleteButton(ActionEvent event) {
		index = FooditemsTable.getSelectionModel().selectedIndexProperty().get();
		foodItemList.remove(index);

		// Database in Action
		ArrayList<Food> dataList = new ArrayList<Food>(foodItemList);
		try {
			DBConnect.writeSerialized(dataList, "./src/DataBase/foodItems.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void handleEditButton(ActionEvent event) {
		index = FooditemsTable.getSelectionModel().selectedIndexProperty().get();

		FoodID.setText(String.valueOf(foodItemList.get(index).getFoodIdxx()));
		FoodName.setText(foodItemList.get(index).getFoodName());
		BuyingPrice.setText(String.valueOf(foodItemList.get(index).getByingPrice()));
		SellingPrice.setText(String.valueOf(foodItemList.get(index).getSellingPrice()));

		Edit.setVisible(false);
		Update.setVisible(true);
	}

	@FXML
	void handleUpdateButton(ActionEvent event) throws EmptyFieldException {

		try {
		String Foodid = FoodID.getText();
		String FooDName = FoodName.getText();
		String BuyPrice = BuyingPrice.getText();
		String SellPrice = SellingPrice.getText();
    Double Buyprice = Double.parseDouble(BuyPrice);
		Double Sellprice = Double.parseDouble(SellPrice);

		foodItemList.remove(index);
		foodItemList.add(index, new Food(Foodid, FooDName, Buyprice, Sellprice, Sellprice - Buyprice));

		// Database in Action
		ArrayList<Food> dataList = new ArrayList<Food>(foodItemList);
		try {
			DBConnect.writeSerialized(dataList, "./src/DataBase/foodItems.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FoodID.clear();
		FoodName.clear();
		BuyingPrice.clear();
		SellingPrice.clear();

		JOptionPane.showMessageDialog(null, "Food Item Updateded", "OK", 1);
		Edit.setVisible(true);
		Update.setVisible(false);
	}
		catch (Exception e) {
			if (FoodID.getText().isBlank() || FoodName.getText().isBlank() || BuyingPrice.getText().isBlank()
					|| SellingPrice.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Fillup All Field");
				throw new EmptyFieldException("Please Fillup All Fields", e);

			}
		}
	}
	@FXML
	void handleFoodID(ActionEvent event) {

	}

	@FXML
	void handleFoodName(ActionEvent event) {

	}

	@FXML
	void handleSellingPrice(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		foodItemList = FXCollections.observableArrayList(readFood);
		SetAllFood();
		Update.setVisible(false);
		// TODO Auto-generated method stub

	}

}
