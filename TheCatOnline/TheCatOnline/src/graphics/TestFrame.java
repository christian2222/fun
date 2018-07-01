package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestFrame extends JFrame implements MouseListener {

	Light l1 = new Light(100,100,Color.GREEN,Color.BLACK,Color.RED);
	Light l2 = new Light(150,100,Color.ORANGE,Color.BLACK,Color.YELLOW);
	Light l3 = new Light(200,100,Color.MAGENTA,Color.BLACK,Color.BLUE);
	Cat c = new Cat(190,150);

	
	public TestFrame() {
		super();
		// TODO Auto-generated constructor stub
		//c.paint(this.getGraphics());
		this.addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	public void paint(Graphics g) {

		l1.setLight(true);
		l3.setLight(true);
		
		l1.paint(g);
		l2.paint(g);
		l3.paint(g);
		
		
		l1.drawContainingRect(g);
		
		c.paint(g);
		
		g.setColor(Color.GREEN);
		g.drawRect(100, 100, 140, 70);
		
		

	}
	

	public static void main(String[] args) {
		TestFrame tf = new TestFrame();
		tf.setSize(640,480);
		tf.setTitle("Graphics Test");
		tf.setVisible(true);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		l2.isLight = !l2.isLight;
		l3.remove = true;
		c.inRoom = !c.inRoom;
		this.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
