package GUI;

import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Graphics2 extends JFrame {
	
	JButton jb1;
	JButton jb2;
	JTextField jtf1;
	
	public Graphics2 (String s, int l, int w){
		super(s);
		setLayout (new FlowLayout());
		setDefaultCloseOperation(Graphics2.EXIT_ON_CLOSE);
		setSize(l, w);
		setVisible(true);
		
		jb1 = new JButton("Button 1");
		add(jb1);
		
		Icon u = new ImageIcon(getClass().getResource("u.png"));
		Icon t = new ImageIcon(getClass().getResource("t.png"));
		jb2 = new JButton(u);
		jb2.setRolloverIcon(t);
		add(jb2);
	}

}
