package GUI;

import java.awt.event.*;

import javax.swing.*;

public class Graphics5 extends JFrame {
	
	private JList left;
	private JList right;
	private JButton move;
	private static String[] peepsilike = {"Jahan", "Charmander", "Alejandro", "Emma", "Chris"};
	
	public Graphics5(int x, int y){
		super("MoveList");
		setSize(x, y);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		
		left = new JList(peepsilike);
		left.setVisibleRowCount(3);
		left.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane (left));
		
		move = new JButton("Move ---/>");
		
		move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				right.setListData(left.getSelectedValues());
			}
			});
		add(move);
		
	right = new JList();
	right.setVisibleRowCount(3);
	right.setFixedCellHeight(left.getFixedCellHeight());
	right.setFixedCellWidth(left.getFixedCellWidth());
	right.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	add(new JScrollPane(right));
		this.setVisible(true);
	}

}
