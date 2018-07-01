package combine;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class TestArea extends JFrame implements MouseListener {

	
	AreaPanel ap = new AreaPanel(0,0);
	
	public TestArea() {
		super();
		// TODO Auto-generated constructor stub
		//c.paint(this.getGraphics());
		this.addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		TestArea ta = new TestArea();
		ta.setSize(1024,768);
		ta.setTitle("Graphics Test");
		ta.setVisible(true);


	}
	
	public void paint(Graphics g) {
		this.ap.paint(g);
		//this.ap.reloadRooms();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.ap.invert();
		this.repaint();

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
