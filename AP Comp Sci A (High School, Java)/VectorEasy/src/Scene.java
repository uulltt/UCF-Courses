import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scene extends JPanel{
	public ArrayList<Line> linelist;
	public Scene(){
		linelist = new ArrayList<Line> ();
		this.setBackground(Color.BLACK);
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		for (Line l : linelist){
			g2d.drawLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
		}
	}
}
