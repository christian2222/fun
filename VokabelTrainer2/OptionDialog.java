import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class OptionDialog extends JDialog
{

	public static JPanel mainPanel = new JPanel(new BorderLayout());
	public static JButton ok = new JButton("Ok");
	public static JButton cancel = new JButton("Abbruch");
	public static JTextField wdhText = new JTextField(""+Values.wdh);
	public static JTextField falschWdhText = new JTextField(""+Values.fehlerWdh);
		
	private static JPanel centerPanel = new JPanel(new GridLayout(2,2));
	private static JPanel southPanel = new JPanel(new FlowLayout());
	private static JLabel jl1 = new JLabel("Wiederholen richtiger Vokablen");
	private static JLabel jl2 = new JLabel("Wiederholen falscher Vokablen");
    
    
    public OptionDialog()
	{
		super(Visual.mainFrame,"Einstellungen",true);
		setSize(300,200);
		
		init();
		pack();
		setVisible(false);
	}
	
    private void init()
	{
        addListeners();
        createMainPanel();
	}
    
	private void addListeners()
	{
		ok.addActionListener(new OptionOkListener());
		cancel.addActionListener(new OptionCancelListener());	    
	}
	    
    private void createMainPanel()
    {
        ok.setMnemonic(KeyEvent.VK_O);
        cancel.setMnemonic(KeyEvent.VK_A);
		southPanel.add(ok);
		southPanel.add(cancel);

		centerPanel.add(jl1);
		centerPanel.add(wdhText);
		centerPanel.add(jl2);
		centerPanel.add(falschWdhText);


		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		this.getContentPane().add(mainPanel);		

    }
	
		
}