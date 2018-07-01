package combine;

import java.awt.Color;
import java.awt.Graphics;

import maths.Maths;
import data.Constants;
import data.Literal;
import data.StaticPublisher;
import graphics.Light;

public class LiteralLight extends Light {

	public Literal literal;

	
	
	public LiteralLight(Literal lit, int x, int y) {
		this.literal = lit;
		int varNumber = literal.getVariable().getNumber();
		Color colorBulb;
		varNumber = Maths.betweenZeroAndN(varNumber,Constants.maxVariableNumber);
		if(literal.isNegated()) {
			colorBulb = Constants.offColor[varNumber];
		}
		else {
			colorBulb = Constants.onColor[varNumber];			
		}
		
		init(x,y,colorBulb,Color.BLACK,Color.YELLOW);
		
		
	}
	
	public void paint(Graphics g) {

			this.clear(g);
			if(!this.remove) {
				if(this.literal.isTrue()) {
					paintFullLight(g);
				}
				else {
					paintNoLight(g);
				}
			}
			//this.drawContainingRect(g);
		
	}
	
	public boolean isLiteralTrue() {
		return this.literal.isTrue();
	}
	
	public Literal getLiteral() {
		return this.literal;
	}
	
	public void setLiteral(Literal l) {
		this.literal = l;
	}
	
}
