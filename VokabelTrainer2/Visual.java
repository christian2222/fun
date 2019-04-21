import javax.swing.*;





import java.awt.event.*;

public class Visual
{
	public static OptionDialog od = new OptionDialog();
	public static BearbeitenDialog bd = new BearbeitenDialog();
	public static HinzuDialog hd = new HinzuDialog();
	public static TestDialog td = new TestDialog();
	public static JLabel statusZeile = new JLabel("Die Statuszeile");
	public static MainFrame mainFrame = new MainFrame("Vokabel-Trainer",600,400);

	
	public static JMenuBar menuBar = new JMenuBar();
	
		public static JMenu dateiMenu = new JMenu();
			public static JMenuItem mNeu = new JMenuItem();
			public static JMenuItem mOeffnen = new JMenuItem();
			public static JMenuItem mSpeichern = new JMenuItem();
			public static JMenuItem mImportieren = new JMenuItem();
			// Separator
			public static JMenuItem mBeenden = new JMenuItem();
			
		public static JMenu vokMenu = new JMenu();
			public static JMenuItem mBearbeiten = new JMenuItem();
			public static JMenuItem mTest = new JMenuItem();
		
		public static JMenu optionMenu = new JMenu();
			public static JMenuItem mEinstell = new JMenuItem();
			
	
	
	
	public static JMenuBar createMenuBar()
	{
		return menuBar;
	}
	
	public static void init()
	{
		initMenu();
	}
			
	public static void initMenu()
	{
		initDateiMenu();
		initVokMenu();
		initOptionMenu();
		menuBar.add(dateiMenu);
		menuBar.add(vokMenu);
		menuBar.add(optionMenu);
	}
	
	private static void initVokMenu()
	{
	    mBearbeiten = new JMenuItem("Bearbeiten", KeyEvent.VK_B);
	    mBearbeiten.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.SHIFT_MASK));
	    mBearbeiten.addActionListener(new BearbeitenListener());
	    
	    mTest = new JMenuItem("Test", KeyEvent.VK_T);
	    mTest.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.SHIFT_MASK));
	    mTest.addActionListener(new TestListener());
	    
	    vokMenu = new JMenu("Vokabel");
	    vokMenu.setMnemonic(KeyEvent.VK_V);
	    
	    vokMenu.add(mBearbeiten);
	    vokMenu.add(mTest);	        
	}
	
	private static void initOptionMenu()
	{
		mEinstell = new JMenuItem("Einstellungen", KeyEvent.VK_E);
		mEinstell.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		mEinstell.addActionListener(new OptionListener());
		
		optionMenu = new JMenu("Optionen");
		optionMenu.setMnemonic(KeyEvent.VK_O);
		
		optionMenu.add(mEinstell);
	}
	
	private static void initDateiMenu()
	{
		mNeu = new JMenuItem("Neu", KeyEvent.VK_N);
		mNeu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		mNeu.addActionListener(new NewListener());
		mOeffnen = new JMenuItem("Öffnen", KeyEvent.VK_F);
		mOeffnen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		mOeffnen.addActionListener(new OpenListener());
		mSpeichern = new JMenuItem("Speichern", KeyEvent.VK_S);
		mSpeichern.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mSpeichern.addActionListener(new SaveListener());
		mImportieren = new JMenuItem("Importieren",KeyEvent.VK_I);
		mImportieren.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		mImportieren.addActionListener(new ImportListener());
	
		//add Separator
		
		mBeenden = new JMenuItem("Beenden",KeyEvent.VK_E);
		mBeenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		mBeenden.addActionListener(new ExitListener());
		
		// creating FileMenu
		
		dateiMenu = new JMenu("Datei");
		dateiMenu.setMnemonic(KeyEvent.VK_D);
		
		dateiMenu.add(mNeu);
		dateiMenu.add(mOeffnen);
		dateiMenu.add(mSpeichern);
		dateiMenu.add(mImportieren);
		dateiMenu.addSeparator();
		dateiMenu.add(mBeenden);
		
	}
	
}