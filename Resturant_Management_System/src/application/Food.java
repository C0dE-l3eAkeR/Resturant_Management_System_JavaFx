package application;

import java.io.Serializable;

public class Food implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String FoodIdxx;
    private String FoodName;
    private Double ByingPrice;
    private Double SellingPrice;
    private Double Profit;

    public Food() {
    	
    }
    
    public Food(String FoodIdxx, String FoodName, Double ByingPrice, Double SellingPrice, Double Profit) {
        this.FoodIdxx = FoodIdxx;
        this.FoodName = FoodName;
        this.ByingPrice = ByingPrice;
        this.SellingPrice = SellingPrice;
        this.Profit = Profit;
    }

    
    public void setFoodIdxx(String FoodIdxx) {
      this.FoodIdxx = FoodIdxx;
  }

  public void setFoodName(String FoodName) {
      this.FoodName = FoodName;
  }

  public void setByingPrice(Double ByingPrice) {
      this.ByingPrice = ByingPrice;
  }

  public void setSellingPrice(Double SellingPrice) {
      this.SellingPrice = SellingPrice;
  }

  public void setProfit(Double Profit) {
      this.Profit = Profit;
  }
    
    public String getFoodIdxx() {
        return FoodIdxx;
    }

    public String getFoodName() {
        return FoodName;
    }

    public Double getByingPrice() {
        return ByingPrice;
    }

    public Double getSellingPrice() {
        return SellingPrice;
    }

    public Double getProfit() {
        return Profit;
    }

 

    @Override
    public String toString() {
			return null;
        
    }
    
    
    
}
