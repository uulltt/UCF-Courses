package GUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class Graphics4 extends JFrame{
	
	private JList jl1;
	private static String[] colornames = {"blue", "magenta","orange","black","red","yellow","green"};
	private static Color[] colors = {Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.BLACK, Color.RED, Color.YELLOW, Color.GREEN}; 
	
	public Graphics4(int x, int y){
		super("List");
		setLayout(new FlowLayout());
		setSize(x,y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		jl1 = new JList(colornames);
		jl1.setVisibleRowCount(6);
		jl1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add (new JScrollPane(jl1));
		
		jl1.addListSelectionListener(
			new ListSelectionListener(){
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					getContentPane().setBackground(colors[jl1.getSelectedIndex()]);
				}
			}
		);
		
		setVisible(true);
	}

}
