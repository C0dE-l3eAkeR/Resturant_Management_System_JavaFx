package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Order_Slip_Controller implements Initializable {

	@FXML
	private Pane pane = new Pane();

	@FXML
	private VBox Vbox = new VBox();
	
  @FXML
  private Button Print;

	// List<PlaceOrder> list =
	// Place_Order_Panel_Controller.FinalOrderList.stream().collect(Collectors.toList());

	ArrayList<PlaceOrder> list = new ArrayList<PlaceOrder>(Place_Order_Panel_Controller.FinalOrderList);

	private PlaceOrder fSlip = list.get(list.size() - 1);
	ArrayList<Integer> foodQuantity = fSlip.getQuantity();
	ArrayList<Double> foodPrice = fSlip.getPrice();

	int i = 0;

	String setPaySlip() {
		Formatter form = new Formatter();

		for (String str : fSlip.getFoodName()) {
			form.format(str + " x " + foodQuantity.get(i) + ".".repeat(42) + foodPrice.get(i) + "\n");
			i++;
		}
		return form.toString();
	}

	@FXML
  void handlePrintButton(ActionEvent event) {
    Place_Order_Panel_Controller.stage1.close();
  }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Text text = new Text();
		Text text2 = new Text();
		Text text3 = new Text();

		text.setFont(new Font(20));
		text.setWrappingWidth(450);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setText("Resturant OF NSU\n" + "Pay Slip\n" + "=".repeat(30) + "\n" + "Food Name" + ".".repeat(35) + "Price\n"
				+ setPaySlip() + "-".repeat(30) + "\n" + "Total" + ".".repeat(42) + fSlip.getTotalPrice() + "\n" + "Vat"
				+ ".".repeat(45) + fSlip.getTotalPrice() * 2 / 100 + "\n" + "Net Total" + ".".repeat(37) + (fSlip.getTotalPrice()
				+ fSlip.getTotalPrice() * 2 / 100) + "\n" + "Thank You!");



		Vbox.getChildren().add(text);
		

	}

}
