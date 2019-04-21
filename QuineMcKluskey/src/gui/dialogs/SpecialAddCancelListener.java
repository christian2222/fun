package gui.dialogs;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * implements the Cancel-Listener in the Special-Add-Dialog 
 * @author chrissy- 28-03-2011
 *
 */
public class SpecialAddCancelListener implements ActionListener {

    @Override
    /**
     * only close the Special-Add-Dialog
     */
    public void actionPerformed(ActionEvent e) {
	Visual.addDialog.setVisible(false);

    }

}
