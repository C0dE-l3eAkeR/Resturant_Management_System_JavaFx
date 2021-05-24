
package application;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;

public class PlaceOrder implements Serializable {

	Integer OrderID;
	int TableNo;
	String WaiterID;
	ArrayList<String> FoodID = new ArrayList<String>();
	ArrayList<String> FoodName = new ArrayList<String>();
	String LFoodID;
	String LFoodName;
	// int
	ArrayList<Integer> Quantity = new ArrayList<Integer>();
	ArrayList<Double> Price = new ArrayList<Double>();
	String OrderTypex;
	Double TotalPrice;

	public PlaceOrder() {
	}
 static int cnt=0;
	public PlaceOrder(Integer OrderID, int TableNo, String waiterID, ArrayList<String> FoodID, ArrayList<String> FoodName,
			ArrayList<Integer> Quantity, ArrayList<Double> Price, Double TotalPrice, String OrderType) {
		this.OrderID = OrderID;
		this.TableNo = TableNo;
		this.WaiterID = waiterID;
		this.FoodID = FoodID;
		this.FoodName = FoodName;
		this.Quantity = Quantity;
		this.Price = Price;
		this.OrderTypex = OrderType;
		this.LFoodID = FoodID.get(FoodID.size() - 1);
		this.LFoodName = FoodName.get(FoodName.size() - 1);
		this.TotalPrice = TotalPrice;
		cnt++;
	}

	public String geLFoodID() {
		return LFoodID;
	}

	public String geLFoodName() {
		return LFoodName;
	}

	public Integer getOrderID() {
		return OrderID;
	}

	public int getTableNo() {
		return TableNo;
	}

	public String getWaiterID() {
		return WaiterID;
	}

	public ArrayList<String> getFoodId() {
		return FoodID;
	}

	public ArrayList<String> getFoodName() {
		return FoodName;
	}

	public ArrayList<Integer> getQuantity() {
		return Quantity;
	}

	public ArrayList<Double> getPrice() {
		return Price;
	}

	public Double getTotalPrice() {
		return TotalPrice;
	}

	public String getOrderType() {
		return OrderTypex;
	}

	public void setOrderID(Integer OrderID) {
		this.OrderID = OrderID;
	}

	public void setTableNo(int TableNo) {
		this.TableNo = TableNo;
	}

	public void setLFoodID(String LFoodID) {
		this.LFoodID = LFoodID;
	}

	public void setLFoodName(String LFoodName) {
		this.LFoodID = LFoodName;
	}

	public String setWaiterID(String WaiterID) {
		return WaiterID;
	}

	public void setFoodId(ArrayList<String> FoodID) {
		this.FoodID = FoodID;
	}

	public void setFoodName(ArrayList<String> FoodName) {
		this.FoodName = FoodName;
	}

	public void setQuantity(ArrayList<Integer> Quantity) {
		this.Quantity = Quantity;
	}

	public void setTotalPrice(Double TotalPrice) {
		this.TotalPrice = TotalPrice;
	}

	public void setPrice(ArrayList<Double> Price) {
		this.Price = Price;
	}

	public void setOrderType(String OrderType) {
		this.OrderTypex = OrderType;
	}

	@Override
	public String toString() {
		return null;
	}

}
