import java.util.ArrayList;


public class Store {
	private ArrayList<StockItem> myStockList;
	public Store(){
		myStockList = new ArrayList<StockItem>();
	}
	
	public void removeAll(int idNum){
		for (int i = 0; i < myStockList.size(); i++){
			if (idNum == myStockList.get(i).getID()){
				myStockList.remove(i);
			}
		}
	}

}
