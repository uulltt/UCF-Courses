import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Calc extends JFrame {
	public JButton b[] = new JButton[16];
	public Icon i[] = new Icon[16];
	public String n1, n2, function;
	public JTextField screen;
	public boolean isequal;
	public String answer(){
		int solution = Integer.parseInt(n1);
		switch(function){
		case "+":
			solution = (Float.parseFloat(n1) + Float.parseFloat(n2));
			break;
		case "-":
			solution = (Float.parseFloat(n1) - Float.parseFloat(n2));
			break;
		case "*":
			solution = (Float.parseFloat(n1) * Float.parseFloat(n2));
			break;
		case "/":
			solution = (Float.parseFloat(n1) / Float.parseFloat(n2));
			break;
		}
		return String.valueOf(solution);
	}
	public Calc(){
		setLayout(null);
		setSize(160, 210);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		isequal = false;
		screen = new JTextField();
		screen.setBounds(0, 0, 160, 20);
		screen.setEditable(false);
		for (int x = 0; x < 16; x++){
			i[x]= new ImageIcon(getClass().getResource(String.valueOf(x) + ".png"));
		}
		for (int x = 0; x < 16; x++){
			b[x] = new JButton(i[x]);
		}
		
		b[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "1";
					} else {
					n1 = n1+"1";
					}
				}else{ 
					if (n2 == null){
						n2 = "1";
					} else {
					n2 = n2+"1";
					}
				}
				UpdateScreen();
			}
			});
		b[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "2";
					} else {
					n1 = n1+"2";
					}
				}else{ 
					if (n2 == null){
						n2 = "2";
					} else {
					n2 = n2+"2";
					}
				}
				UpdateScreen();
			}
			});
		b[3].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "3";
					} else {
					n1 = n1+"3";
					}
				}else{ 
					if (n2 == null){
						n2 = "3";
					} else {
					n2 = n2+"3";
					}
				}
				UpdateScreen();
			}
			});
		b[4].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "4";
					} else {
					n1 = n1+"4";
					}
				}else{ 
					if (n2 == null){
						n2 = "4";
					} else {
					n2 = n2+"4";
					}
				}
				UpdateScreen();
			}
			});
		b[5].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "5";
					} else {
					n1 = n1+"5";
					}
				}else{ 
					if (n2 == null){
						n2 = "5";
					} else {
					n2 = n2+"5";
					}
				}
				UpdateScreen();
			}
			});
		b[6].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "6";
					} else {
					n1 = n1+"6";
					}
				}else{ 
					if (n2 == null){
						n2 = "6";
					} else {
					n2 = n2+"6";
					}
				}
				UpdateScreen();
			}
			});
		b[7].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "7";
					} else {
					n1 = n1+"7";
					}
				}else{ 
					if (n2 == null){
						n2 = "7";
					} else {
					n2 = n2+"7";
					}
				}
				UpdateScreen();
			}
			});
		b[8].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "8";
					} else {
					n1 = n1+"8";
					}
				}else{ 
					if (n2 == null){
						n2 = "8";
					} else {
					n2 = n2+"8";
					}
				}
				UpdateScreen();
			}
			});
		b[9].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "9";
					} else {
					n1 = n1+"9";
					}
				}else{ 
					if (n2 == null){
						n2 = "9";
					} else {
					n2 = n2+"9";
					}
				}
				UpdateScreen();
			}
			});
		b[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (function == null){
					if (n1 == null){
						n1 = "0";
					} else {
					n1 = n1+"0";
					}
				}else{ 
					if (n2 == null){
						n2 = "0";
					} else {
					n2 = n2+"0";
					}
				}
				UpdateScreen();
			}
			});
		b[10].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				n1 = null;
				function = null;
				n2 = null;
				isequal = false;
				UpdateScreen();
			}
			});
		b[11].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				function = "+";
				UpdateScreen();
			}
			});
		b[12].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				function = "-";
				UpdateScreen();
			}
			});
		b[13].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				function = "*";
				UpdateScreen();
			}
			});
		b[14].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				function = "/";
				UpdateScreen();
			}
			});
		b[15].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				isequal = true;
				UpdateScreen();
			}
			});
		for (int x = 1; x < 10; x++){
			if (x < 4){
			b[x].setBounds((x-1)*40, 60, 40, 30);
			}
			if (x >= 4 && x < 7){
				b[x].setBounds((x-4)*40, 90, 40, 30);
			}
			if (x >= 7){
				b[x].setBounds((x-7)*40, 120, 40, 30);
			}
			add(b[x]);
		}
		b[0].setBounds(0, 150, 40, 30);
		b[10].setBounds(40, 150, 80, 30);
		for (int x = 11; x < 15; x++){
			b[x].setBounds((x-11)*40, 30, 40, 30);
			add(b[x]);
		}
		b[15].setBounds(120, 60, 40, 120);
		add(b[0]);
		add(b[10]);
		add(b[15]);
		add(screen);
		setVisible(true);
		
	}
	
	public void UpdateScreen(){
		
		if (!isequal){
			if (n1 == null && n2 == null && function == null){
				screen.setText(" ");
			}
			if (n1 != null && n2 == null && function == null ){
				screen.setText(n1);
			}
			if (n1 != null && n2 == null && function != null ){
				screen.setText(n1+function);
			}
			if (n1 != null && n2 != null && function != null ){
				screen.setText(n1+function+n2);
			}
		} else {
			screen.setText(answer());
		}
	}

}
