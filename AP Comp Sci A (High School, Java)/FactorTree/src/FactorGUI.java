import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FactorGUI extends JFrame {
public FactorLogic fl;
public String num = "";
public String result = "";
private JButton factor;
private JTextField input, results;
public FactorGUI(){
	setLayout(null);
	setSize(245, 70);
	setTitle("Factorizer");
	setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	setResizable(false);
	factor = new JButton("Factor!");
	factor.setBounds(0, 0, 100, 20);
	input = new JTextField();
	input.setEditable(true);
	input.setBounds(100, 0, 140, 20);
	results = new JTextField();
	results.setBounds(0, 20, 240, 20);
	factor.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			result = "";
			num = input.getText();
			fl = new FactorLogic(Integer.parseInt(num));
			fl.Factorize();
			for (Integer i : fl.numbers){
				result+= i.toString() + ", ";
			}
			results.setText(result);
		}
		});
	add (input);
	add (results);
	add(factor);
	setVisible(true);
}
}
