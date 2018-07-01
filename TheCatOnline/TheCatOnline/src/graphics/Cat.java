package graphics;

import java.awt.*;

public class Cat extends Canvas {
	
	

	private int dx = 2;
	private int headRadius = 14;
	private int dHead = 10;
	private int xcoord[], ycoord[];
	
	public boolean inRoom = true;
	public int x,y;

	public Cat(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isInRoom() {
		return inRoom;
	}

	public void setInRoom(boolean inRoom) {
		this.inRoom = inRoom;
	}

	public void paint(Graphics g) {
		//paintBlackCatAt(g, 200, 200);
		this.clear(g);
		if(this.inRoom) {
			paintFullCatColor(g, x, y, Color.BLACK);
		}
		else {
			paintCatBordersAt(g, x, y, Color.BLACK);
		}
		// draw the point from which the cat is drawn
		// g.drawOval(100, 100, 3, 3);
	}
	
	public void clear(Graphics g) {
		g.clearRect(x-2*dx,y-dx,17,17);
	}
	
	
	public void paintCatBordersAt(Graphics g, int x, int y, Color c) {
		g.setColor(c);
		// draw left ear
		g.drawLine(x-dx,y+2*dx,x,y);
		g.drawLine(x, y, x+dx, y+2*dx);
		// draw upHead;
		g.drawLine(x+dx,y+2*dx,x+dHead-dx, y+2*dx);
		// draw right ear
		g.drawLine(x+dHead,y,x+dHead-dx,y+2*dx);
		g.drawLine(x+dHead, y, x+dHead+dx, y+2*dx);
		// left cheek
		g.drawLine(x-dx,y+2*dx, x-dx, y+2*dx+5);
		// right cheek
		g.drawLine(x+dx+dHead,y+2*dx, x+dx+dHead, y+2*dx+5);
		//g.drawOval(x-dx, y, headRadius, headRadius);
		g.drawArc (x-dx, y, headRadius, headRadius, 180, 180);
	}
	
	public void paintFullCatColor(Graphics g, int x, int y, Color c) {

		g.setColor(c);
		xcoord = new int[10];
		ycoord = new int[10];
		xcoord[0] = x-dx;
		ycoord[0] = y+2*dx+5;
		xcoord[1] = x-dx;
		ycoord[1] = y + 2*dx;
		xcoord[2] = x;
		ycoord[2] = y;
		xcoord[3] = x +dx;
		ycoord[3] = y+2*dx;
		xcoord[4] = x +dHead-dx;
		ycoord[4] = y+2*dx;
		xcoord[5] = x+dHead;
		ycoord[5] = y;
		xcoord[6] = x+dHead+dx;
		ycoord[6] = y+2*dx;
		xcoord[7] = x+dHead+dx;
		ycoord[7] = y+2*dx+5;
		g.fillPolygon(xcoord, ycoord, 8);
		g.fillArc (x-dx, y, headRadius, headRadius, 180, 180);
	}
	
	public void paintCatConstructionAt(Graphics g, int x, int y) {
		// draw left ear
		g.drawLine(x-dx,y+2*dx,x,y);
		g.setColor(Color.RED);
		g.drawLine(x, y, x+dx, y+2*dx);
		// draw upHead;
		g.setColor(Color.BLUE);
		g.drawLine(x+dx,y+2*dx,x+dHead-dx, y+2*dx);
		// draw right ear
		g.setColor(Color.BLACK);
		g.drawLine(x+dHead,y,x+dHead-dx,y+2*dx);
		g.setColor(Color.RED);
		g.drawLine(x+dHead, y, x+dHead+dx, y+2*dx);
		// left cheek
		g.setColor(Color.CYAN);
		g.drawLine(x-dx,y+2*dx, x-dx, y+2*dx+5);
		// right cheek
		g.drawLine(x+dx+dHead,y+2*dx, x+dx+dHead, y+2*dx+5);
		g.setColor(Color.BLACK);
		//g.drawOval(x-dx, y, headRadius, headRadius);
		g.setColor(Color.ORANGE);
		g.drawArc (x-dx, y, headRadius, headRadius, 180, 180);
		
		
	}
}
