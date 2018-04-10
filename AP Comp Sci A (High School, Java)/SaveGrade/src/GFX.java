import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;


public class GFX extends JFrame{
	private JButton random, fit;
	private JTextArea area;
	private JTextArea[] tiles;
	public int a,b,c,d;
	public int x;
	public boolean setran;
	TileGame game;
	NumberTile space;
	
	public GFX(){
		x = 0;
		game = new TileGame();
		setLayout(null);
		setSize(400, 400);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		random = new JButton("Random");
		random.setBounds(0, 20, 100, 20);
		fit = new JButton("Fit");
		fit.setBounds(0, 50, 100, 20);
		tiles = new JTextArea[10];
		area = new JTextArea();
		area.setBounds(370, 0, 400, 45);
		area.setEditable(false);
		
		random.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setRandom();
			}
			});
		fit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (game.insertTile(new NumberTile(a, b, c, d, null))){
					x++;
					System.out.println(x);
					for (int z = 0; z < x; z++){
					tiles[z].setText("  " + game.board.get(z).up + "\n" + game.board.get(z).left + "   " + game.board.get(z).right + "\n  " + game.board.get(z).down);
					}
					setRandom();
				if (x >= 10){
					x = 0;
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "This Tile does not Fit.");
				}
			}
			});
		
		for(int y = 0; y < 10; y ++){
			tiles[y] = new JTextArea();
			tiles[y].setBounds((y*25)+(y*12)+19, 310, 25, 90);
			tiles[y].setEditable(false);
			add(tiles[y]);
			}
		add(area);
		add(random);
		add(fit);
		setVisible(true);
	}
	
	public void setRandom(){
		a = (int)(Math.random()*10);
		b = (int)(Math.random()*10);
		c = (int)(Math.random()*10);
		d = (int)(Math.random()*10);
		area.setText("  " + a + "\n" + b + "   " + c + "\n  " + d);
	}

}
