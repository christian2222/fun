package gui;

import java.awt.*;
import javax.swing.*;

public class BinPanel extends JPanel {


    protected JPanel scrollPanel = new JPanel();
    protected JScrollPane scrollPane = new JScrollPane(scrollPanel);
    
    public BinPanel() {
	this.setLayout(new FlowLayout());
	scrollPane.setPreferredSize(new Dimension(200,400));
	this.add(scrollPanel);
	this.addBin();
    }
    
    public void addBin() {
	this.scrollPanel.add(new BinPanelPart(""));
    }
    
}
