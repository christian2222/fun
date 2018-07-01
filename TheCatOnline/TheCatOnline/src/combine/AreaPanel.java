package combine;

import java.awt.Canvas;
import java.awt.Graphics;

import data.Constants;
import data.DisjClause3;
import data.KNF;
import data.Literal;

public class AreaPanel extends Canvas {

	int x,y;
	public Room[] rooms = new Room[Constants.maxDisjClauseNumber];
	KNF formula = new KNF();
	
	public AreaPanel(int x, int y) {
		this.x = x;
		this.y = y;
		this.formula.randomInit();
		for(int i = 0; i < Constants.maxDisjClauseNumber; i++) {
			this.rooms[i] = new Room(this.formula.getClause(i), 0, 0, i);
		}
		this.initPositions();
	}
	
	protected void initPositions() {
		for(int j = 0; j < 5; j++) {
			for(int i= 0; i < 6; i++) {
				rooms[i*5+j].setPosition(this.x+40+160*i,this.y+140+120*j);
			}
		}
	}
	
	public void invert() {
		Literal l;

		for(int c = 0; c < Constants.maxDisjClauseNumber; c++) {
			for(int i = 0; i < 3; i++) {
				l = this.formula.getClause(c).getLiteral(i);
				//l.setNegated(false);
				
				l.getVariable().invert();
				
			}
		}
		
		this.reloadRooms();
	}
	
	public void reloadRooms() {
		for(int c = 0; c < Constants.maxDisjClauseNumber; c++) {
			this.rooms[c].reloadClause(this.formula.getClause(c));
		}
	}
	
	
	public void paint(Graphics g) {
		for(int j = 0; j < 5; j++) {
			for(int i= 0; i < 6; i++) {
				rooms[i*5+j].paint(g);
			}
		}
	}
	
	public void initValue(int value) {
		for(int i = 0; i < Constants.maxVariableNumber; i++) {
			this.setVariable(i, value);
		}
	}
	
	public boolean isTrue() {
		return this.formula.isTrue();
	}
	
	public void setVariable(int name, int value) {
		Literal l;
		for(int c = 0; c < Constants.maxDisjClauseNumber; c++) {
			for(int i = 0; i < 3; i++) {
				l = this.formula.getClause(c).getLiteral(i);
				if(l.getVariable().getNumber() == name) {
					l.getVariable().setValue(value);
				}
			}
		}
		
		this.reloadRooms();
	}
}
