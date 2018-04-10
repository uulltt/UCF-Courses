import javax.swing.JFrame;


public class Start {
	static String[][] map;
	static float tilepercentage;

		static // Use this for initialization
		void MapStart (int x, int y, int p) {
			map = new String[x][y];
			if (p > 99 || p < 1)
				tilepercentage = (int)(Math.random()*100);
			else
				tilepercentage = p;
			for (int i = 0; i < x; i++){
				for (int ii = 0; ii < y; ii++){
					int j = (int)(Math.random()*100);
					if (j > p && i > 0 && i < x && ii > 0 && ii < y)
						map[i][ii] = " "; 
					else if (j <= p || i <= 0 || i >= x-1 || ii <= 0 || ii >= y-1)
						map[i][ii] = "#";
					}
				}
			for (int i = 0; i < x; i++){
				for (int ii = 0; ii < y; ii++){
					int j = 0;
					int k = 0;
					if (i > 0 && ii > 0 && i < x-1 && ii < y-1){
					for (int x1 = -1; x1 < 2; x1++){
						for (int y1 = -1; y1 < 2; y1++){
							if (map[i][ii]== "#" && map[i+x1][ii+y1] == " ")
								j++;
							if (map[i][ii]== " " && map[i+x1][ii+y1] == "#")
								k++;
							if (j >= 5)
								map[i][ii] = " ";
							if (k >= 5)
								map[i][ii] = "#";
							}
						}
					}
				}
			}
			}

public static void main(String[] args) {
MapStart(100, 100, 45);
   for (int i = 0; i < map.length; i++){
	   for (int ii = 0; ii < map[0].length; ii++){
		   if (ii == 0)
			   System.out.println(map[i][ii]);
		   else
			   System.out.print(map[i][ii]);
	   }
   }
}
}