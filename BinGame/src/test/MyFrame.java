package test;

import graphics.*;
import gui.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import algorithms.*;

import random.*;
import data.*;

public class MyFrame extends JFrame implements ActionListener {
    
    protected static JButton add = new JButton("add");
    protected static BinPanel bp = new BinPanel();
    protected static int BinNums = 1;
    protected static JPanel east = new JPanel();
    protected static JPanel middle = new JPanel();
    protected static Graphics graphic;
    protected static MyFrame mFrame = new MyFrame();
    protected static JScrollPane scroller = new JScrollPane();
    public static Item actualItem = new Item(0);
    public static boolean addedSuccess = false;
    public static int round = 0;
    public static final int maxRound = 11;
    public static NextFit nextFit = new NextFit();
    public static FirstFit firstFit = new FirstFit();
    public static BestFit bestFit = new BestFit();
    public static FirstFitDecreasing ffDecrease = new FirstFitDecreasing();
    
    public MyFrame() {
	super();
	this.setSize(950, 700);
	this.setTitle("MyFrame");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setVisible(true);
	this.repaint();
	this.add.addActionListener(this);
	east.setLayout(new BorderLayout());
	east.add(add,BorderLayout.SOUTH);
	east.add(middle,BorderLayout.CENTER);
    }
    
    public static void paintNewItem() {
	if(round < maxRound-1) {
	    int i = Generation.getRandomInt(51);
	    if(i == 0) i = 1;
	    actualItem = new Item(i);
	    mFrame.setTitle("Round "+(round+1)+" new size: "+i);
	    round++;
	    nextFit.add(actualItem);
	    firstFit.add(actualItem);
	    bestFit.add(actualItem);
	    ffDecrease.add(actualItem);
	}
	else {
	    mFrame.setTitle("End of Game!");
	    System.out.println(nextFit.report());
	    System.out.println();
	    System.out.println(firstFit.report());
	    System.out.println();	
	    System.out.println(bestFit.report());
	    System.out.println();
	    //fristFitDescrease is an offline Algorithm
	    ffDecrease.apply();
	    System.out.println(ffDecrease.report());
	    System.out.println();
	    System.out.println("You used "+BinNums+" bottles.");
	    System.out.println();
	    System.out.println();
	    System.out.println("Zusammenfassung:");
	    System.out.println(nextFit.shortInfo());
	    System.out.println(firstFit.shortInfo());
	    System.out.println(bestFit.shortInfo());
	    System.out.println(ffDecrease.shortInfo());
	    System.out.println();
	    System.out.println("You used "+BinNums+" bottles.");
	}
	    
	
    }
    
    public static void main(String[] args) {

	GridBagLayout gbl = new GridBagLayout();
	mFrame.setLayout(gbl);
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx = 0;
	gbc.gridy = 1;
	gbl.setConstraints(east, gbc);
	mFrame.add(east);
	//scroller = new JScrollPane(bp);
	//scroller.setMinimumSize(new Dimension(200,400));
	gbc.gridx = 0;
	gbc.gridy = 0;
	// instead of scroller here bp
	gbl.setConstraints(bp, gbc);
	mFrame.add(bp);
	paintNewItem();	
	mFrame.repaint();
	//mFrame.add(new BinPanelPart("Bin 1"),BorderLayout.CENTER);
	//mFrame.add(new CanvasBin(new Bin()),BorderLayout.CENTER);
	//mFrame.add(new JRadioButton("Bin Number 1"),BorderLayout.SOUTH);
	//mFrame.add(new JButton("Hello"));
    }
    
    public void paint(Graphics g) {
	//g.drawLine(0,0,500,500);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	if (BinNums < 11) {
	    bp.addBin();
	    BinNums++; 
	    this.resize(this.getWidth()+1,this.getHeight()+1);
	}
	else
	    add.setEnabled(false);
	
	
    }
    
	/**	Bin b = new Bin();
	Item i = new Item(22);
	Item j = new Item(34);
	b.addSuccess(i);
	b.addSuccess(j);
	b.addSuccess(i);
	
	CanvasBin cb = new CanvasBin(b);
	pane.setLayout(new BorderLayout());
	pane.add(cb,BorderLayout.CENTER);
	*/

	/** funktioniert
	GraphicBin gbin = new GraphicBin();
	gbin.add(new Item(40));
	gbin.add(new Item(33));
	gbin.add(new Item(17));
	gbin.addSuccess(new Item(59));
	gbin.addSuccess(new Item(8));
	gbin.paintCompleteBin(g);
	*/
	/** funktioniert
	gbin.paintBin(g);
	gbin.paintItem(g, i);
	gbin.paintItem(g, j);
	gbin.paintItem(g, i);
	gbin.paintItem(g, j);
	*/

    /**
     * 	
	mFrame.add(new JButton("hello"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new BinPanelPart("huhu"));
	mFrame.add(new JButton("Bin 2"));

     */
}
