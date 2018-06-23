package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Paint;

import javax.swing.JPanel;

import datastructures.Memory;
import datastructures.Page;

public class MemoryPanel extends JPanel {

	public PagePanel[] panelArray;
	
	public MemoryPanel(Memory<Page> m) {
		int size = m.getSize();
		this.setLayout(new GridLayout(0,size));	
		this.setSize(52*size, 102);
		panelArray = new PagePanel[size];
		
		for(int i = 0; i < size; i++) {
			panelArray[i] = new PagePanel(m.getPageAtIndex(i));
		}
		for(int i = 0; i < size; i++) {
			this.add(panelArray[i]);
		}
		
		this.repaint();
	}
	
	public void reload(Memory<Page> m) {
		for(int i = 0; i < this.panelArray.length; i++) {
			this.panelArray[i].reloadPage(m.getPageAtIndex(i));
		}		
	}
	

	public Dimension getPreferredSize() {
		return new Dimension(550, 110);
	}
	
	
}
