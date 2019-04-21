package gui;

import javax.swing.*;

/**
 * This class provides the main frame of the application. Its easy
 * to handle because we add an ExitListener and set some options
 * 
 * @author chrissy - date 28-03-2011
 *
 */


public class MainFrame extends JFrame
{
    	/**
    	 * Constructs the main frame with an default closing operation,
    	 * an ExitIconListener and the default look and feel.
    	 * @param str the title
    	 * @param width the widht of the frame
    	 * @param heigth the height of the frame
    	 */
	public MainFrame(String str,int width,int heigth)
	{
		super(str);
		setSize(width,heigth);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new ExitIconListener());
		setDefaultLookAndFeelDecorated(true);
	}
}