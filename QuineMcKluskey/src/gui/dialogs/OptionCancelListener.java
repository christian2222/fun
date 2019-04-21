package gui.dialogs;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides the action-listener for the Cancel-Button in the
 * option dialog
 * 
 * @author chrissy - date 28-03-2011
 *
 */
public class OptionCancelListener implements ActionListener {

    @Override
    /**
     * Just close/hide the optionDialog and do nothing else
     */
    public void actionPerformed(ActionEvent arg0) {
	Visual.optionDialog.setVisible(false);

    }

}
