import javax.swing.*;



public class MainFrame extends JFrame
{
	public MainFrame(String s,int width,int heigth)
	{
		super(s);
		setSize(width,heigth);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new ExitIconListener());
		setDefaultLookAndFeelDecorated(true);
	}
}