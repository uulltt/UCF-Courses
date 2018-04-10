import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GRFX extends JFrame {
	public JTextArea input, result;
	public JTextField rows, columns;
	public JButton convert;
	public String message, r, c;
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    int i = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public GRFX(){
		setLayout(null);
		setSize(400, 300);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		convert = new JButton("Encrypt");
		input = new JTextArea(message);
		result = new JTextArea();
		rows = new JTextField(r);
		columns = new JTextField(c);
		convert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//if (isNumeric(r) && isNumeric(c)){
				RouteCipher wow = new RouteCipher(Integer.parseInt(rows.getText()), Integer.parseInt(columns.getText()), input.getText());
				result.setText(wow.encryptMessage(input.getText()));
				//}
				//else{
					//JOptionPane.showMessageDialog(null, "Proper array is not set up.");
				//}
				}
			});
		JLabel[] uhh = new JLabel[4];
		uhh[0] = new JLabel("Rows");
		uhh[1] = new JLabel("Columns");
		uhh[2] = new JLabel("Original Message");
		uhh[3] = new JLabel("Encrypted Message");
		uhh[0].setBounds(0, 0, 50, 20);
		uhh[1].setBounds(0, 60, 50, 20);
		uhh[2].setBounds(100, 0, 100, 30);
		uhh[3].setBounds(100, 150, 125, 30);
		rows.setBounds(0, 20, 30, 30);
		columns.setBounds(0, 80, 30, 30);
		input.setBounds(50, 30, 200, 20);
		convert.setBounds(90, 75, 120, 30);
		result.setBounds(50, 130, 200, 20);
		add(uhh[0]);
		add(uhh[1]);
		add(uhh[2]);
		add(uhh[3]);
		add(rows);
		add(columns);
		add(input);
		add(convert);
		add(result);
		setVisible(true);
	}
	

}
