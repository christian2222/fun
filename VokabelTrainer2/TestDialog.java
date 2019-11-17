/*
 * Created on 07.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author chrissy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TestDialog extends JDialog
{
    private JPanel mainPanel = new JPanel(new GridLayout(2,1));

	    private JPanel upperPanel = new JPanel(new BorderLayout());

    		private JPanel upperCenterPanel = new JPanel(new GridLayout(2,2));    
			    private JLabel vorgLabel = new JLabel("Vorgabe:");
    			private JLabel eingLabel = new JLabel("Eingabe:");
		    	private JTextArea vorgabe = new JTextArea(3,20);
    			private JTextArea eingabe = new JTextArea(3,20);

	    		private JScrollPane vorgScroller = new JScrollPane();
	    		private JScrollPane eingScroller = new JScrollPane();

    
	    	private JPanel upperSouthPanel = new JPanel(new FlowLayout());
			    private JButton okButton = new JButton("Ok");
    			private JButton cancelButton = new JButton("Abbruch");
    

	   	private JPanel lowerPanel = new JPanel(new BorderLayout());
		    private JScrollPane infoScroller = new JScrollPane();
	   		private JTextArea info = new JTextArea(50,10);
	
    

    public TestDialog()
	{
	    super(Visual.mainFrame,"Vokabel Test",true);
	    setSize(800,300);
	    this.init();
	    
	    setVisible(false);	    
	}
    
    public void setToTopPosition()
    {
        vorgabe.setCaretPosition(0);
        info.setCaretPosition(0);
    }
    
    private void init()
	{
	    this.addListeners();
	    vorgabe.setEditable(false);
	  	info.setEditable(false);

	  	vorgScroller = new JScrollPane(vorgabe);
	    eingScroller = new JScrollPane(eingabe);
		upperCenterPanel.add(vorgLabel);
	    upperCenterPanel.add(vorgScroller);
	    upperCenterPanel.add(eingLabel);
	    upperCenterPanel.add(eingScroller);
	    
	    cancelButton.setMnemonic(KeyEvent.VK_A);
	    okButton.setMnemonic(KeyEvent.VK_O);
	    upperSouthPanel.add(cancelButton);
	    upperSouthPanel.add(okButton);
	    
	    upperPanel.add(upperCenterPanel,BorderLayout.CENTER);
	    upperPanel.add(upperSouthPanel,BorderLayout.SOUTH);
	    
	    
	    infoScroller = new JScrollPane(info);
	    lowerPanel.add(infoScroller,BorderLayout.CENTER);
	    
	    setToTopPosition();
	    
	    mainPanel.add(upperPanel);
	    mainPanel.add(lowerPanel);
	    
	    Container pane = this.getContentPane();
	    pane.add(mainPanel);
	}
	
    public String getVorgabe()
    {
        return vorgabe.getText();
    }

    public void setVorgabe(String s)
    {
        vorgabe.setText(s);
    }
    
    public String getEingabe()
    {
        return eingabe.getText();
    }

    public void setEingabe(String s)
    {
        eingabe.setText(s);
    }
    
    public String getInfo()
    {
        return this.info.getText();
    }
    
    public void setInfo(String s)
    {
        this.info.setText(s);
    }
    
    public void setOkButtonLabel(String s)
    {
        this.okButton.setText(s);
    }

    public void setCancelButtonLabel(String s)
    {
        this.cancelButton.setText(s);
    }    

    public void enableOkButton()
    {
        this.okButton.setEnabled(true);
    }
        
    public void disableOkButton()
    {
        this.okButton.setEnabled(false);
    }
    
    public void enableCancelButton()
    {
        this.cancelButton.setEnabled(true);
    }
    
    public void disableCancelButton()
    {
        this.cancelButton.setEnabled(false);
    }
    
    public void enableEingabe()
    {
        this.eingabe.setEnabled(true);
    }
    
    public void disableEingabe()
    {
        this.eingabe.setEnabled(false);
    }
    
	
	private void addListeners()
	{
	    okButton.addActionListener(new TestDialogOkListener());
	    cancelButton.addActionListener(new TestDialogCancelListener());
	}
}
