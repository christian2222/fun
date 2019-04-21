package test;

import gui.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestScroller extends JFrame implements ActionListener{
    
    protected JPanel paneNorth = new JPanel();
    protected JPanel paneSouth = new JPanel();
    protected BinPanel bp = new BinPanel(); 
    protected JButton add = new JButton("add new bin");
    protected int BinNums = 0;
    protected JScrollBar scrollBar = new JScrollBar();
    protected JScrollPane scrollPane = new JScrollPane();
	
    public void layout() {
	this.paneNorth = new JPanel(new BorderLayout());
	scrollPane = new JScrollPane(bp);
	this.paneNorth.add(scrollPane, BorderLayout.CENTER);
	
	this.paneSouth.setLayout(new BorderLayout());
	this.paneSouth.add(this.add,BorderLayout.NORTH);
    }
    
    
    public TestScroller() {
	super();
	this.setSize(300, 200);
	this.setTitle("TestScroller");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.layout();
	this.setLayout(new FlowLayout());
	this.add(this.paneNorth);
	this.add(this.paneSouth);
	this.setVisible(true);
	// TODO Auto-generated constructor stub
    }
    
    public static void main(String[] args) {
	TestScroller ts = new TestScroller();
	
    }
    
    
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	if (BinNums < 20) {
	    bp.addBin();
	    BinNums++; 
	    this.resize(this.getWidth()+1,this.getHeight()+1);
	}
	else
	    add.setEnabled(false);
	
	
    }
    
    

}
