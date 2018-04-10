
public class StockItem {
	private String description;
	private int idNum;
	private double price;
	private int quantity;
	
	public StockItem(String d, int n, double p, int q){
		description = d;
		idNum = Math.abs(n);
		price = Double.parseDouble(String.valueOf(p).substring(0, 3));
		quantity = q;
	}
	
	public String getDesc(){
		return description;
	}
	
	public int getID(){
		return idNum;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getAmount(){
		return quantity;
	}
	
	public void setPrice(double d){
		price = d;
	}
	
	public void removeAmount(int a){
		if (a >= quantity)
			quantity = 0;
		else
			quantity-=a;
		
	}
	
	public void addAmount(int a){
		quantity+=a;
	}

}
