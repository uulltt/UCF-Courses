
public class Auto {
	
	public static void main(String[] args){
		String[][] map = new String[40][40];
		int ww = 0;
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				int place = (int)(Math.random()*100);
				if (place <= 20){
					map[x][y] = "#";
				} else {
					map[x][y] = "-";
				}
				if (map[x][y] == "-"){
					if (x > 0 && y > 0 && x < 39 && y < 39){
					for (int a = x-1; a < x+2; a++){
						for (int b = y-1; b < y+2; b++){
							if (map[a][b] == "#"){
								ww++;
								if (ww >= 5){
									map[x][y] = "#";
									ww = 0;
								}
							}
							}
						}
					}
				}
			}
		}
		int r = 0;
		while (r < 40){
		for (int c = 0; c < 40; c++){
		System.out.print(map[r][c]);
		if (c == 39){
			System.out.println(map[r][c]);
		}
		}
		r++;
		}
	}

}
