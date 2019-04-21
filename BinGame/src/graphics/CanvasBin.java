package graphics;

import java.awt.*;
import java.awt.event.*;
import random.*;
import test.*;
import data.*;

public class CanvasBin extends Canvas {
    
    protected Bin internBin;
    protected int binWidth = 40;
    protected int x = 50;
    protected int y = 120;
    
    protected static final int addBound = 10;
    protected static final Dimension needDim = new Dimension(50,120);
    
    protected static final Color itemColor = new Color(0.3f,0.5f,0.9f);

    public CanvasBin(Bin b) {
	this.setInternBin(b);
	this.init();
    }
    
    protected void init() {
	this.enableEvents(AWTEvent.MOUSE_EVENT_MASK);
	this.x = 20;
	this.y = 120;
	this.binWidth = 30;
    }
    
    
    
    protected void processMouseEvent(MouseEvent event) {
	if ((event.getID() == MouseEvent.MOUSE_PRESSED)&&(MyFrame.round < MyFrame.maxRound) ) {
	    MyFrame.addedSuccess = this.addCurrentBin(MyFrame.actualItem);
	    if (MyFrame.addedSuccess) {
		MyFrame.paintNewItem();
	    }
	    this.repaint(); 
	}
    }

    /**
     * adds the actual Item to this bin
     */	
    public boolean addCurrentBin(Item i) {
	boolean success = false;
	// addSuccess returns a boolean success-value
	success = this.internBin.addSuccess(i);
	return success;
    }
    
    
    public void paint(Graphics g) {
	this.init();
	this.paintPrint(g);
	this.paintCompleteBin(g);
    }
    
    public void setInternBin(Bin b) {
	this.internBin = b;
    }
    
    public Bin getInternBin() {
	return this.internBin;
    }
    
    public Dimension getPreferredSize() {
	return needDim;
    }
    
    public Dimension getMinimumSize() {
	return needDim;
    }
    
    public void paintContainedItems(Graphics g) {
	g.setColor(this.itemColor);
	for(Item i:internBin.getItemList()) {
	    this.paintItem(g, i);
	}
    }
    
    public void paintCompleteBin(Graphics g) {
	this.paintBin(g);
	this.paintContainedItems(g);
    }
    
    public void paintBin(Graphics g) {
	//g.drawRect(0,0,x,y);
	g.drawLine(x, y, x, y - Bin.maxSize);
	g.drawLine(x, y, x + this.binWidth, y);
	g.drawLine(x + this.binWidth, y, x + this.binWidth,y - Bin.maxSize);
    }
    
    public void paintPrint(Graphics g) {
	g.drawString(""+this.getInternBin().getCurrentSize(), x+15,y+20);
    }
    
    public void paintItem(Graphics g, Item i) {
	Color saveColor = g.getColor();
	g.fillRect(x+2, y-i.getSize(), this.binWidth-4, i.getSize()-2);
	g.setColor(this.itemColor);
	// berechnung der neuen Koordinaten
	int newX = x;
	int newY = y-i.getSize();
	x = newX;
	y = newY;
    }
    
    
}
