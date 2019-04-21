package gui.buttons;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithm.Constraints;

/**
 * Sets all checkbox-entries which are availible
 * @author chrissy - date 28-03-2011 
 *
 */
public class SetEntriesListener implements ActionListener {

    @Override
    /**
     * Set the check-boxes that are currently availible
     */
    public void actionPerformed(ActionEvent e) {
	for(int i = 1; i < Constraints.maxIndex; i++) {
	    Visual.boxes[i].setSelected(true);	    
	}
    }

}
