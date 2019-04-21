/*
 * Created on 01.10.2004
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
import java.awt.event.*;



public class BearbeitenOkListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	    Visual.bd.setVisible(false);
		Data.saveVisualList();
	}
}
