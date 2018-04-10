package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Graphics6 extends JFrame {
	private JPanel mousepanel;
	private JLabel statusbar;
	
	public Graphics6(){
		super("Mouse Tracker");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		
		mousepanel = new JPanel();
		mousepanel.setBackground(Color.WHITE);
		add(mousepanel, BorderLayout.CENTER);
		
		statusbar = new JLabel("Nothing has been clicked.");
		add(statusbar, BorderLayout.SOUTH);
		
		Handler h = new Handler();
		mousepanel.addMouseListener(h);
		mousepanel.addMouseMotionListener(h);
		setVisible(true);
	}
	
	private class Handler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {
			statusbar.setText("you are dragging the mouse pointer");
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Color rgb = new Color(0);
			
			if (e.getPoint().x < 100){
				rgb = Color.RED;
			}
			else if (e.getPoint().x < 200){
				rgb = Color.GREEN;
			}
			else {
				rgb = Color.BLUE;
			}
			mousepanel.setBackground(rgb);
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			statusbar.setText(String.format("Clicked at %d, %d", e.getX(), e.getY()));
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			statusbar.setText("Mouse button has been pressed down.");
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
