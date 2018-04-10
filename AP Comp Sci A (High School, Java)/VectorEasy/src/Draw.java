import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;



public class Draw extends JFrame{
	public Scene mousepanel;
	private JLabel statusbar;
	private JButton Clear;
	private boolean isadding;
	int startx, starty, endx, endy;
	
	public Draw(int width, int height){
		super("Drawing Program");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(true);
		isadding = false;
		mousepanel = new Scene();
		add(mousepanel, BorderLayout.CENTER);
		Handler h = new Handler();
		mousepanel.addMouseListener(h);
		mousepanel.addMouseMotionListener(h);
		Thread t = new Thread(new Point(){

			@Override
				public void run(){
					while(true){
						mousepanel.paint(getGraphics());
						try {
						Thread.sleep(1);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				}
	});
	t.start();	

		setVisible(true);
	}
	
	
	private class Handler implements MouseListener, MouseMotionListener{
		
		
		@Override
		public void mouseDragged(MouseEvent arg0) {
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (isadding){
				mousepanel.linelist.add(new Line(startx, starty, e.getPoint().x, e.getPoint().y + 26));
				isadding = false;
			} else {
			startx = e.getPoint().x; 
			starty = e.getPoint().y + 26;
			isadding = true;
			}
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
			
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}

}


	
}
