package gui;

import java.awt.*;

import javax.swing.*;
import graphics.*;
import data.*;

public class BinPanelPart extends JPanel{

    protected CanvasBin cb = new CanvasBin(new Bin());
    
    protected JRadioButton rButton = new JRadioButton();
    
    protected static Dimension neededSize = new Dimension(80,300);
    
    public JRadioButton getButton() {
	return this.rButton;
    }
    
    public BinPanelPart(String name) {
	//this.rButton = new JRadioButton(name);
	this.setLayout(new BorderLayout());
	this.add(cb,BorderLayout.CENTER);
	/**
	 * this.setLayout(new BorderLayout());
	this.add(cb,BorderLayout.CENTER);
	this.add(rButton,BorderLayout.SOUTH);
	**/
	/**GridBagLayout gbl = new GridBagLayout();
	this.setLayout(gbl);
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridwidth = 100;
	gbc.gridheight = 200;
	gbc.gridx=0;
	gbc.gridy=0;
	gbc.weightx = 100;
	gbc.weighty = 200;
	gbl.setConstraints(cb, gbc);
	this.add(cb);
	gbc.gridx=0;
	gbc.gridy=2;
	gbl.setConstraints(rButton, gbc);
	this.add(rButton);
	this.add(new JButton("bye"),gbc);*/
    }
    
    public Dimension getPreferredSize() {
	return neededSize;
    }
    
    public Dimension getMinimumSize() {
	return neededSize;
    }
    
    
}
