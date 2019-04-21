package gui.buttons;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithm.Constraints;

/**
 * Clears all checkbox-entries which are availible
 * @author chrissy - date 28-03-2011
 *
 */
public class ClearEntriesListener implements ActionListener {

    @Override
    /**
     * clear the current checked entries which are availible
     */
    public void actionPerformed(ActionEvent e) {
	for(int i = 1; i < Constraints.maxIndex; i++) {
	    Visual.boxes[i].setSelected(false);	    
	}
    }

}
