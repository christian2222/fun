/*
 * Created on 30.09.2004
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

public class BearbeitenDialog extends JDialog
{
    public static JButton cancelButton = new JButton("Abbruch");
    public static JButton entfernButton = new JButton("Vokabel löschen");
    public static JButton tauschButton = new JButton("Einträge vertauschen");
    public static JButton hinzuButton = new JButton("Vokabel hinzufügen");
    public static JButton okButton = new JButton("Ok");
    
    private static JScrollPane scroller;
	private static JPanel panel = new JPanel(new BorderLayout());
	private static JPanel buttonPanel = new JPanel(new FlowLayout());

	public BearbeitenDialog()
	{
	    super(Visual.mainFrame,"Bearbeiten der Vokabeln",true);
	    setSize(600,400);
	    JPanel mainPanel = this.createBearbeitenPanel();
	    Container pane = this.getContentPane();
	    pane.add(mainPanel);
	    setVisible(false);
	    
	}
	
	public void load()
	{
	    Data.loadVisualList();
	}	

	
    private JPanel createBearbeitenPanel()
    {
        addListeners();

		scroller = new JScrollPane(Values.liste);
        
         
        panel.add(scroller,BorderLayout.CENTER);

        cancelButton.setMnemonic(KeyEvent.VK_A);
        entfernButton.setMnemonic(KeyEvent.VK_L);
        tauschButton.setMnemonic(KeyEvent.VK_T);
        hinzuButton.setMnemonic(KeyEvent.VK_H);
        okButton.setMnemonic(KeyEvent.VK_O);
        buttonPanel.add(cancelButton);
        buttonPanel.add(entfernButton);
        buttonPanel.add(tauschButton);
        buttonPanel.add(hinzuButton);
        buttonPanel.add(okButton);
        
        panel.add(buttonPanel,BorderLayout.SOUTH);
        
        
        return panel;
         
    }
    
    private void addListeners()
    {
		cancelButton.addActionListener(new BearbeitenCancelListener());        
		hinzuButton.addActionListener(new BearbeitenHinzuListener());
		tauschButton.addActionListener(new BearbeitenTauschListener());
		okButton.addActionListener(new BearbeitenOkListener());
		entfernButton.addActionListener(new BearbeitenEntfernListener());
    }
}

