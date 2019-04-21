/*
 * Created on 11.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author chrissy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 **/
import java.awt.event.*;

public class ExitIconListener extends WindowAdapter
{
	public void windowClosing(WindowEvent we)
	{
	    Exit.systemExit();
	}
}
