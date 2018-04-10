import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUI extends JFrame {
	public int mil, sec, min;
	public JButton start, stop, lap;
	public JTextField time;
	public JTextField[] laps;
	public String[] laptime;
	public volatile boolean starttime;
	public int newlap;
	public String stopvalue, stopText;
	
	public String timeField(){
		String second = null;
		String millisecond = null;
		if (mil < 10)
			millisecond = (".00" + mil);
		else if (mil < 100 && mil >= 10 && sec < 10)
			millisecond = (".0" + mil);
		else if (mil >= 100)
			millisecond = ("." + mil);
		if (sec < 10)
			second = (":0" + sec);
		else
			second = (":" + sec);
		return(min + second + millisecond);
	}
	
	public GUI(){
		starttime = true;
		mil = 0;
		sec = 0;
		min = 0;
		newlap = 0;
		stopText = "Stop";
		stopvalue = "0:00.000";
		setLayout(null);
		setSize(400, 300);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		start = new JButton("Start");
		stop = new JButton(stopText);
		lap = new JButton("Lap");
		time = new JTextField(timeField());
		laps = new JTextField[8];
		time.setEditable(false);
		Thread t = new Thread(new GoTime(){
			@Override
			public void run(){
				while(true){
					if (!starttime){
						stopText = "Stop";
					} else {
						stopText = "Reset";
					}
				while(!starttime){
					time.setText(timeField());
					mil++;
					if (mil >= 1000){
						mil = 0;
						sec++;
					}
					if (sec >= 60){
						mil = 0;
						sec = 0;
						min++;
					}
					try {
						Thread.sleep(1);
					} catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
			}
		}); t.start();
		for (int x = 0; x < laps.length; x++){
			laps[x] = new JTextField();
			laps[x].setEditable(false);
		}
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				starttime = false;
				if (stopvalue == timeField()){
					//t.start();
				}
			}
		});
		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stopvalue = timeField();
				if (!starttime){
				starttime = true;
				}else{
					for (int x = 0; x < laps.length; x++){
						
					}
					min = 0;
					sec = 0;
					mil = 0;
					time.setText(timeField());
					for (int x = 0; x < laps.length; x++){
						laps[x].setText(null);
						newlap = 0;
					}
				}
			}
		});
		lap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				laps[newlap].setText(timeField());
				newlap++;
				min = 0;
				sec = 0;
				mil = 0;
				time.setText(timeField());
			}
		});
		if (!starttime){
		}
		for (int x = 0; x < laps.length; x++){
			laps[x].setBounds(300, (x*30)+20, 100, 30);
			add(laps[x]);
		}
		time.setBounds(0, 0, 300, 20);
		start.setBounds(0, 20, 100, 30);
		stop.setBounds(100, 20, 100, 30);
		lap.setBounds(200, 20, 100, 30);
		add(time);
		add(start);
		add(stop);
		add(lap);
		
		setVisible(true);
	}

}
