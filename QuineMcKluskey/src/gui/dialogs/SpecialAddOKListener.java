package gui.dialogs;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * implements the Add-Listener in the Special-Add-Dialog 
 * @author chrissy- 28-03-2011
 *
 */
public class SpecialAddOKListener implements ActionListener {

    @Override
    /**
     * dependend on the selected Item of the addComboBox we
     * add a specific number of randomized monoms or
     * add the complete powerset {@see Visual.addSpecialAddNumber(str)}<br>
     * Note: If we add x monoms there are x tries to add a 
     * randomly produced monom. If the monom already exists the current
     * try has no effect. This is the reason, why you'll not
     * get x different monoms in the list.
     * Thus the actual number is almost lower!  
     */
    public void actionPerformed(ActionEvent arg0) {
	// take the selected item as a string
	String str = Visual.addCombo.getSelectedItem().toString();
	// do the main part
	Visual.addSpecialAddNumber(str);
	// close the dialog
	Visual.addDialog.setVisible(false);

    }

}
