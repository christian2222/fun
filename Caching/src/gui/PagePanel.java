package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import communicate.StaticHolder;
import datastructures.Page;

public class PagePanel extends SlotPanel  implements MouseListener {

	
	
	public PagePanel(Page p) {
		super(p);
		addMouseListener(this);
	}
	
	public void reloadPage(Page q) {
		this.page = q;
		this.repaint();
	}
	
	public void blink(Color c) {
		Graphics g = this.getGraphics();
		this.repaint();
		Color oldColor = g.getColor();
		g.setColor(c);
		g.fillRect(0, 0, 50, 100);
		try {
			Thread.sleep(200);
		} catch(Exception e) {
			
		}
		g.setColor(oldColor);
		g.fillRect(0, 0, 50, 100);
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// one step forward in algortihm;
		StaticHolder.selectedPage = this.page;
		this.blink(Color.RED);
		StaticHolder.nextStep = true;
		// TODO Auto-generated method stub
		
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
