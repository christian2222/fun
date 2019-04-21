package gui.menuentries;

import gui.Visual;

import java.awt.event.*;

/**
 * shows the Option-Dialog
 * @author chrissy - date 28-03-2011
 *
 */
public class OptionListener implements ActionListener {

    @Override
    /**
     * shows the JDialog [optionDialog]
     */
    public void actionPerformed(ActionEvent arg0) {
	Visual.optionDialog.setVisible(true);
    }

}
