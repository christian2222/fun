package gui;


/**
 * This Adapter provides the ExitListener if we close the program by
 * clicking on the X in the upper right edge.
 * 
 * ToDo: Ask if we are sure.
 * Seperate this ask in an own class ?
 * 
 * @author chrissy - 28-03-2011
 *
 *
 **/

import java.awt.event.*;


public class ExitIconListener extends WindowAdapter
{
    	/**
    	 * really closes the program - without ask anything.
    	 */
	public void windowClosing(WindowEvent we)
	{
	    Exit.systemExit();
	}
}
