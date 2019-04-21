import java.awt.event.*;



public class OptionCancelListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		Visual.od.setVisible(false);
	}
}