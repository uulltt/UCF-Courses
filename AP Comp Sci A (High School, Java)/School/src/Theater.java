import java.util.ArrayList;


public class Theater {
	private ArrayList<Movie> Shows;
	private String[] title;
	private int opentime;
	private int closetime;
	private int ran;
	private String[][] sched;
	
	
	public Theater(int o, int c, String w, String x, String y, String z){
		title = new String[4];
		opentime = o;
		closetime = c;
		sched = new String[7][(c-o)/50];
		title[0] = w;
		title[1] = x;
		title[2] = y;
		title[3] = z;
		sched[0][0]="mon";
		sched[1][0]="tue";
		sched[2][0]="wed";
		sched[3][0]="thu";
		sched[4][0]="fri";
		sched[5][0]="sat";
		sched[6][0]="sun";
	}
	
	public ArrayList<Movie> AddMovies(){
		
		ArrayList <Movie> am = new ArrayList<Movie>();
		for (int x = opentime; x < closetime; x+=30){
			ran = (int)(Math.random()*4) + 1;
			if (x % 100 >= 60){
				x+=40;
			}
			if (ran == 1){
				am.add(new Movie(title[0], x));
			}
			else if (ran == 2){
				am.add(new Movie(title[1], x));
			}
			else if (ran == 3){
				am.add(new Movie(title[2], x));
			}
			else if (ran == 4){
				am.add(new Movie(title[3], x));
			}
			
		}
		return am;
	}
	
	public void MakeChart(){
		for (int x = 0; x < sched.length; x++){
			for (int y = 1; y < sched[x].length; y++){
				Shows = AddMovies();
				if (y % 3 == 0){
					Shows.get(y).is3d = true;
				}
				else {
					Shows.get(y).is3d = false;
				}
				if (Shows.get(y).is3d == false){
				sched[x][y] = (Shows.get(y-1).time + " - " + Shows.get(y).name);
				}
				else if (Shows.get(y).is3d == true){
					sched[x][y] = (Shows.get(y-1).time + " - " + Shows.get(y).name + ", 3d");
				}
			}
		}
		
		for (int z = 0; z < sched[0].length; z++){
			System.out.println(sched[0][z] + " " + sched[1][z] + " " + sched[2][z] + " " + sched[3][z] + " " + sched[4][z] + " " + sched[5][z] + " " + sched[6][z]);
		}
	}
}
