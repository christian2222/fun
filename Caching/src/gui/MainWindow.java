package gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import communicate.StaticHolder;
import datastructures.Page;


public class MainWindow extends JFrame {

	protected MemoryPanel cache;
	protected BackgroundPanel background;
	protected StringPanel showed;
	
	protected JLabel seqLabel = new JLabel("sequence");
	protected JLabel cacheLabel = new JLabel("cache");
	protected JLabel backLabel = new JLabel("background");
	

	public MainWindow() {
		super("Memory");
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.initWindow();
	}
	
	public void reload() {
		this.cache.reload(StaticHolder.algortihm.getCache());
	}
	
	public void initWindow() {
		this.cache = new MemoryPanel(StaticHolder.algortihm.getCache());
		this.background = new BackgroundPanel(StaticHolder.algortihm.getBackground());
		this.showed = new StringPanel("");
		
		this.reload();
		this.createPanels();
	}
	
	
	public void serveByCache(int i) {
		this.cache.panelArray[i].blink(Color.BLUE);
	}

	protected void createPanels() {
		this.seqLabel.setBounds(10, 20, 80, 10);
		this.add(seqLabel);
		this.cacheLabel.setBounds(100, 120, 80, 10);
		this.add(cacheLabel);
		this.backLabel.setBounds(50, 320, 100, 10);
		this.add(backLabel);
		this.showed.setLocation(10, 50);
		this.add(showed);
		this.cache.setLocation(100, 150);
		this.add(cache);
		this.background.setLocation(50, 350);
		this.add(background);
		this.repaint();
	}
	
	public void addChar(char c) {
		this.showed.add(c);
	}
}
