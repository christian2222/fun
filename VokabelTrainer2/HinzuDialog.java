/*
 * Created on 03.10.2004
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
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

	
	
public class HinzuDialog extends JDialog
{
	private static JLabel wortLabel = new JLabel("Wort");
	private static JLabel vokLabel = new JLabel("Vokabel");
	public static JTextField wortFeld = new JTextField(20);
	public static JTextField vokFeld = new JTextField(20);
	
	private static JButton okButton = new JButton("Ok");
	private static JButton cancelButton = new JButton("Abbruch");
	
	private static JPanel mainPanel = new JPanel(new BorderLayout());
	private static JPanel centerPanel = new JPanel(new GridLayout(2,2));
	private static JPanel southPanel = new JPanel(new FlowLayout());
	
	
	
	public HinzuDialog()
	{
		super(Visual.bd,"Vokabel hinzufügen",true);
		setSize(300,200);
		
		init();
		pack();
		setVisible(false);	    
	}
		
	public void setWort(String s)
	{
	    wortFeld.setText(s);
	}
	
	public String getWort()
	{
	    return wortFeld.getText();
	}
	
	public void setVokabel(String s)
	{
	    vokFeld.setText(s);
	}
	
	public String getVokabel()
	{
	    return vokFeld.getText();
	}

	private void init()
	{
		addListeners();
		
	    centerPanel.add(wortLabel);
	    centerPanel.add(wortFeld);
	    centerPanel.add(vokLabel);
	    centerPanel.add(vokFeld);
	    
	    cancelButton.setMnemonic(KeyEvent.VK_C);
	    okButton.setMnemonic(KeyEvent.VK_K);
	    southPanel.add(cancelButton);
	    southPanel.add(okButton);
	    
	    mainPanel.add(southPanel,BorderLayout.SOUTH);
	    mainPanel.add(centerPanel,BorderLayout.CENTER);
	    
		this.getContentPane().add(mainPanel);		    
	}
	
	private void addListeners()
	{
	    okButton.addActionListener(new HinzuOkListener());
	    cancelButton.addActionListener(new HinzuCancelListener());
	}
    
}
