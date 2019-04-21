package gui.menuentries;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This AddSListener opens the Special-Add-Dialog
 * @author chrissy - date 28-03-2011
 *
 */
public class AddSListener implements ActionListener {

    @Override
    /**
     * open / show the Special-Add-Dialog
     */
    public void actionPerformed(ActionEvent e) {
	Visual.addDialog.setVisible(true);

    }

}
