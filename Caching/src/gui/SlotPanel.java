package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import datastructures.Page;

public class SlotPanel extends JPanel  implements Comparable{

	protected Page page;
	
	public SlotPanel(Page p) {
		this.setSize(52, 102);
		this.page = p;
	}
	
	public void paintPage(Graphics g) {
		
		g.drawRect(0, 0, 50, 100);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		g.drawString(""+this.page.getToken(), 20, 60);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintPage(g);
	}
	
	public Dimension getPreferredDimension() {
		return new Dimension(52,102);
	}
	
	public Page getPage() {
		return this.page;
	}

	@Override
	public int compareTo(Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		if(!(o instanceof SlotPanel)) {
			return Integer.MIN_VALUE;
		}
		
		SlotPanel q = (SlotPanel) o; 
		return this.getPage().compareTo(q.getPage());
	}
	
}
