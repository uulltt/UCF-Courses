import java.util.ArrayList;

public class FactorLogic {
public int num;
public ArrayList<Integer> numbers;
public FactorLogic(int i){
	num = i;
	numbers = new ArrayList<Integer>();
}
public void Factorize(){
	for (int i = 2; i < num;){
		if (num % i == 0){
			num/=i;
			//System.out.println(i);
			numbers.add(i);
		} else {
			i++;
		}
	}
	//System.out.println(num);
	numbers.add(num);
}

}
