
public class Scrambler {
	
	public String wordscramble(String s){
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < s.length(); i++){
			if (i % 2 == 0){
				s1 = s1 + s.substring(i, i+1);
			} else {
				s2 = s2 + s.substring(i, i+1);
			}
		}
		return s1 + s2;
	}

}
