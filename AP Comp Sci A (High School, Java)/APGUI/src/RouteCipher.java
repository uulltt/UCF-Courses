
public class RouteCipher {
	public String[][] LetterBlock;
	private int numRows, numCols;
	public String str;
	
	public RouteCipher(int r, int c, String s){
		numRows = r;
		numCols = c;
		str = s;
		LetterBlock = new String[numRows][numCols];
	}
	
	private void fillBlock() {
		int pos = 0;
		for (int r = 0; r < this.numRows; r++ ) {
			for (int c = 0; c < this.numCols; c++ ) {
				if (pos < str.length()) {
					this.LetterBlock[r][c] = str.substring(pos, pos+1);
					pos++;
					} else {
					this.LetterBlock[r][c] = "A";
					}
				}
			}
		}
	
	public String encryptBlock(){
		fillBlock();
		String s = "";
		for (int c = 0; c < this.numCols; c++ ) {
			for (int r = 0; r < this.numRows; r++ ) {
				s = s + this.LetterBlock[r][c];
			}
		}
		return s;
	}
	
	public String encryptMessage(String message) {
		String encryptedMessage = "";
		int chunkSize = this.numRows * this.numCols;
		while (message.length() > 0) {
		if (chunkSize > message.length()) {
		chunkSize = message.length();
		}
		str = message;
		encryptedMessage += encryptBlock();
		message = message.substring(chunkSize);
		}
		return encryptedMessage;
		}

}
