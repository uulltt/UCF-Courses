
public class BatteryCharger {
	private int[] rateTable;
	
	private int getChargingCost (int startTime, int chargeTime){
		/*int count = 0;
		 int cost = 0;
		int to23 = 0;
		while (chargeTime > 23){
			count++;
			chargeTime -= 23;
		}
		to23 = 23 - startTime;
		chargeTime -= to23;
		
		if (chargeTime >= to23){
			for (int i = 0; i < to23; i++){
				cost+= ratetable[startTime + 1];
				}
			chargeTime -= to23;
			}
		for (int i = 0; i < chargeTime; i++){
			
		}*/
		int cost = 0;
		for (int i = 0; i < chargeTime; i++){
			cost += rateTable[(startTime+1) % 24];
		}
		return cost;
	}

}
