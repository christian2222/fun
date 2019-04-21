import java.awt.event.*;

public class ExitListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		Exit.systemExit();
	}
}