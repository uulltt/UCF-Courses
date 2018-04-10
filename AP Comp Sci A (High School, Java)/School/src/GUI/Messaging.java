package GUI;

import javax.swing.JOptionPane;

public class Messaging {
	
	public String InputMessage(String s){
		s = JOptionPane.showInputDialog(s);
		return s;
	}
	
	public void messageDialog(String s){
		JOptionPane.showMessageDialog(null, s);
	}
	
	public void confirmDialog(String s){
		int x = JOptionPane.showConfirmDialog(null, s, "Confirm Dialog", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (x == JOptionPane.YES_OPTION){
		System.out.println("cool beans");
		} else if (x == JOptionPane.NO_OPTION) {
		System.out.println("Not Cool, Bro.");
		}
	}

}
