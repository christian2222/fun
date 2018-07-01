package graphics;

import java.awt.*;
import java.awt.event.*;

import data.Constants;
import data.Literal;

public class Light extends Canvas {

	protected Color fillColor,frameColor,bulbColor;
	protected int x,y;
	
	protected boolean isLight = false;
	protected boolean remove = false;
	
	
	public Light() {
		
	}
	
	public Light(int x, int y, Color bulbColor, Color frameColor, Color fillColor) {
		super();
		this.bulbColor = bulbColor;
		this.frameColor = frameColor;
		this.fillColor = fillColor;
		this.x = x;
		this.y = y;
		
	}
	
	public void init(int x, int y, Color bulbColor, Color frameColor, Color fillColor) {
		this.bulbColor = bulbColor;
		this.frameColor = frameColor;
		this.fillColor = fillColor;
		this.x = x;
		this.y = y;		
	}

	
	public boolean isLight() {
		return isLight;
	}


	public void setLight(boolean isLight) {
		this.isLight = isLight;
	}
	





	public void paint(Graphics g) {

			this.clear(g);
			if(!this.remove) {
				if(this.isLight) {
					paintFullLight(g);
				}
				else {
					paintNoLight(g);
				}
			}
		
	}
	
	public void clear(Graphics g) {
		int dx = 10;
		int dy = 10;
		g.clearRect(x,y, 4*dx, 7*dy+2);		
	}
	
	public void drawContainingRect(Graphics g) {
		g.setColor(this.frameColor);
		int dx = 10;
		int dy = 10;
		g.drawRect(x,y, 4*dx, 7*dy);
	}
	
	public void paintNoLight(Graphics g) {
		
		g.setColor(this.frameColor);
		int dx = 10;
		int dy = 10;
		int dxwick = 5;
		int dywick = 5;
		int dxd = 17;
		int xwick = x + dxd;
		int ywick = y+2*dy + 3*dywick;
		int degree = 180;
		// draw ref point
		//g.drawOval(x, y, 3, 3);
		// draw bulb
		g.drawArc(x,y, 4*dx, 4*dx, 0,degree);
		//g.fillArc(x, y, 4*dx, 4*dx, 0, degree);
		/*
		g.drawLine(x +4*dx,y + 2*dy,x+3*dx,y+5*dy);
		g.drawLine(x+ 3*dx,y+5*dy,x+dx,y+5*dy);
		g.drawLine(x+dx,y+5*dy,x,y+2*dy);
		*/
		// draw bulb with coordinates
		int xcoord[] = new int[5];
		int ycoord[] = new int[5];
		xcoord[0] = x+4*dx;
		ycoord[0] = y+2*dy;
		xcoord[1] = x+3*dx;
		ycoord[1] = y+5*dy;
		xcoord[2] = x+dx;
		ycoord[2] = y+5*dy;
		xcoord[3] = x;
		ycoord[3] = y+2*dy;
		g.drawPolygon(xcoord, ycoord, 4);
		// draw wick
		g.drawLine(xwick,ywick + 3*dywick, xwick+dxwick, ywick+2*dywick);
		g.drawLine(xwick+dxwick,ywick+2*dywick,xwick,ywick+dywick);
		g.drawLine(xwick,ywick+dywick,xwick+dxwick,ywick);
		// connectors
		g.drawLine(x+dx,y+5*dy,x+dx,y+7*dy);
		g.drawLine(x+3*dx,y+5*dy,x+3*dx,y+7*dy);
		g.drawLine(x+dx, y+6*dy, x+3*dx, y+6*dy);
		g.setColor(bulbColor);
		g.fillRect(x+dx, y+6*dy, 2*dx,dy);		
		g.drawLine(x+dx, y+7*dy, x+3*dx, y+7*dy);
		g.setColor(Constants.standColor);
	}
	
	public void paintFullLight(Graphics g) {
		
		g.setColor(fillColor);
		int dx = 10;
		int dy = 10;
		int dxwick = 5;
		int dywick = 5;
		int dxd = 17;
		int xwick = x + dxd;
		int ywick = y+2*dy + 3*dywick;
		int degree = 180;
		// draw bulb
		g.fillArc(x,y, 4*dx, 4*dx, 0,degree);
		//g.fillArc(x, y, 4*dx, 4*dx, 0, degree);
		/*
		g.drawLine(x +4*dx,y + 2*dy,x+3*dx,y+5*dy);
		g.drawLine(x+ 3*dx,y+5*dy,x+dx,y+5*dy);
		g.drawLine(x+dx,y+5*dy,x,y+2*dy);
		*/
		// draw bulb with coordinates
		int xcoord[] = new int[5];
		int ycoord[] = new int[5];
		xcoord[0] = x+4*dx;
		ycoord[0] = y+2*dy;
		xcoord[1] = x+3*dx;
		ycoord[1] = y+5*dy;
		xcoord[2] = x+dx;
		ycoord[2] = y+5*dy;
		xcoord[3] = x;
		ycoord[3] = y+2*dy;
		g.fillPolygon(xcoord, ycoord, 4);
		
		// draw wick
		g.setColor(frameColor);
		// redraw wick bound
		g.drawPolygon(xcoord, ycoord, 4);
		g.drawArc(x,y, 4*dx, 4*dx, 0,degree);
		g.drawLine(xwick,ywick + 3*dywick, xwick+dxwick, ywick+2*dywick);
		g.drawLine(xwick+dxwick,ywick+2*dywick,xwick,ywick+dywick);
		g.drawLine(xwick,ywick+dywick,xwick+dxwick,ywick);
		// connector
		g.drawLine(x+dx,y+5*dy,x+dx,y+7*dy);
		g.drawLine(x+3*dx,y+5*dy,x+3*dx,y+7*dy);
		g.drawLine(x+dx, y+6*dy, x+3*dx, y+6*dy);
		g.setColor(bulbColor);
		g.fillRect(x+dx, y+6*dy, 2*dx,dy);
		g.drawLine(x+dx, y+7*dy, x+3*dx, y+7*dy);		

		g.setColor(Constants.standColor);
	}
	
}
