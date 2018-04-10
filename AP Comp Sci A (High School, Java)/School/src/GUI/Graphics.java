package GUI;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Graphics extends JFrame {
	
	private JTextField jt1;
	private JTextField jt2;
	private JTextField jt3;
	private JPasswordField pw1;
	
	public Graphics(){
		super("Title ME!");
		jt1 = new JTextField(10);
		add(jt1);
		jt2 = new JTextField("enter text");
		add(jt2);
		jt3 = new JTextField("uneditable", 30);
		add(jt3);
	}

}
