package gui.buttons;


import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action performed by the "Remove Monom"-Entry.<br>
 * <br>
 * @author chrissy
 *
 */
public class RemoveListener implements ActionListener {

    @Override
    /**
     * We remove the monom which is actually selected 
     * 
     */
    public void actionPerformed(ActionEvent arg0) {
	int k = 0;
	String str = "";
	
	if(!Visual.listModel.isEmpty())  {
	    k = Visual.list.getSelectedIndex();
	    str = Visual.listModel.get(k).toString(); 
	    Visual.listModel.remove(k);
	    Visual.statusInfo.setText("Monom ["+str+"] removed.");
	}
	else
	    Visual.statusInfo.setText("No more elements.");

    }

}
