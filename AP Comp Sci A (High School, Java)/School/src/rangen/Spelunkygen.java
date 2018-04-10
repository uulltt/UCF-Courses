package rangen;

public class Spelunkygen {
	public static void MakeLevel(){
		int [][] rooms = new int[4][4];
		boolean [][] solution = new boolean[4][4];
		int[] random = new int[3];
		boolean makepath = true;
		random[0] = (int)(Math.random()*4);
		random[1] = (int)(Math.random()*2)+1;
		int currentlevel = 0;
		int currentroom = random[0];
		for (int x = 0; x < rooms.length; x++){
			for (int y = 0; y < rooms[x].length; y++){
				rooms[x][y] = 0;
				solution[x][y] = false;
			}
			}
		rooms[random[0]][0] = random[1];
		solution[random[0]][0] = true;
		while(makepath){
		random[2] = (int)(Math.random()*5)+1;
		if ((random[2] == 1 || random[2] == 2)){
			if (currentroom == 0){
				if (currentlevel != 3){
					rooms[currentroom][currentlevel] = 2;
					if (currentlevel < 2){
						rooms[currentroom][currentlevel+1] = (int)(Math.random()*2)+2;
						}
					currentlevel++;
					random[2] = (int)(Math.random()*2)+3;
					}	
			} else {
				currentroom--;
				rooms[currentroom][currentlevel] = 1;
			
			}
		}
			if ((random[2] == 3 || random[2] == 4)){
				if (currentroom == 3){
					if (currentlevel <= 3)
						rooms[currentroom][currentlevel] = 2;
						if (currentlevel < 2){
						rooms[currentroom][currentlevel+1] = (int)(Math.random()*2)+2;
						}
						currentlevel++;
						random[2] = (int)(Math.random()*2)+1;
				} else {
					currentroom++;
					rooms[currentroom][currentlevel] = 1;
				}
			}
				if ((random[2] == 5)){
					if (currentlevel == 3){
						rooms[currentroom][currentlevel] = 9;
						makepath = false;			
					} else {
						rooms[currentroom][currentlevel] = 2;
						currentlevel++;	
					}
				}
			}
		for (int x = 0; x < rooms.length; x++)
		System.out.println(rooms[0][x] + "" + rooms[1][x] + "" + rooms[2][x] + "" + rooms[3][x]);
	}
	
	public static void main(String[] args){
		MakeLevel();
	}

}
