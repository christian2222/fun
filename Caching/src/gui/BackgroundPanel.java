package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;

import datastructures.Memory;
import datastructures.Page;

public class BackgroundPanel extends JPanel {

	private SlotPanel[] slotArray;
	
	public BackgroundPanel(Memory<Page> m) {
		int size = m.getSize();
		this.setLayout(new GridLayout(3,0));	
		this.setSize((52*size)/3, 102*3);
		slotArray = new SlotPanel[size];
		
		for(int i = 0; i < size; i++) {
			slotArray[i] = new SlotPanel(m.getPageAtIndex(i));
		}
		
		this.sort();
		
		for(int i = 0; i < size; i++) {
			this.add(slotArray[i]);
		}
		
		//this.repaint();
	}
	
	protected void sort() {
		ArrayList<SlotPanel> aList = new ArrayList<SlotPanel>(slotArray.length);
		for(int i = 0; i < this.slotArray.length; i++) {
			aList.add(this.slotArray[i]);
		}
		
		Collections.sort(aList);
		
		SlotPanel[] spArray = new SlotPanel[aList.size()];
		for(int i = 0; i < spArray.length; i++) {
			spArray[i] = aList.get(i);
		}
		this.slotArray = spArray;
		//this.repaint();
	}
}
