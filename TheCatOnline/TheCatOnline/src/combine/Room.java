package combine;

import java.awt.*;

import data.*;
import maths.Maths;
import graphics.*;


public class Room extends Canvas {

	int x,y;
	int roomNumber;


	String address;
	
	LiteralLight[] litLights = new LiteralLight[3];

	public DisjClause3 clause;
	
	Cat cat;
	
	public Room(DisjClause3 clause, int x, int y, int number) {
		this.clause = clause;
		this.x = x;
		this.y = y;
		this.roomNumber = number;
		this.address = "Zimmer "+this.roomNumber;
		this.cat = new Cat(x+87,y+80);
		this.reloadGraphics();
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.reloadGraphics();
	}
	
	public void reloadClause(DisjClause3 clause) {
		this.clause = clause;
		this.reloadGraphics();
	}
	public void initCat(Cat c) {
		
	}
	
	public void reloadGraphics() {
		for(int i = 0; i < 3; i++) {
			litLights[i] = new LiteralLight(this.clause.getLiteral(i), 11+this.x+40*i, this.y+25);
		}
		this.cat = new Cat(x+87,y+80);
	}
	/*
	public void setClause(DisjClause3 clause) {
		this.clause = clause;
		this.loadLiteralLights();
	}
	*/
	public LiteralLight getLiteralLight(int i) {
		int k = Maths.betweenZeroAndN(i, 3);
		return litLights[k];
	}
	
	public void paint(Graphics g) {
		g.drawRect(x,y,140,20);
		g.drawString(this.address, x+40, y+15);
		g.drawRect(x, y+20, 140, 80);
		boolean isLight = false;
		for(int i = 0; i < 3; i++) {
			this.litLights[i].paint(g);
		}
		
		cat.setInRoom(this.hasLight());
		cat.paint(g);
	}
	
	public void clear(Graphics g) {
		g.clearRect(x, y, 142, 92);
	}

	public boolean hasLight() {
		boolean isLight = false;
		if(litLights == null) return false;
		for(int i = 0; i < 3; i++) {
			isLight = isLight || this.litLights[i].isLiteralTrue();
		}
		return isLight;
		
	}
	// DisjClause dc;
	// eiegntlich nicht n�tig, da die Literale in Literal Light stecken
	
/*
 * 	Light l1 = new Light(100,100,Color.GREEN,Color.BLACK,Color.RED);
	Light l2 = new Light(150,100,Color.ORANGE,Color.BLACK,Color.YELLOW);
	Light l3 = new Light(200,100,Color.MAGENTA,Color.BLACK,Color.BLUE);
	Cat c = new Cat(190,150);

 */
}
