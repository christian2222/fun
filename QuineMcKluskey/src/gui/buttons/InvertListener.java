package gui.buttons;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import algorithm.Constraints;
/**
 * Inverts the check-boxes
 * 
 * @author chrissy - 28-03-2011
 *
 */
public class InvertListener implements ActionListener {

    @Override
    /**
     * invert the current check-boxes by using negation
     */
    public void actionPerformed(ActionEvent e) {
	boolean b = true;
	for(int i = 1; i < Constraints.maxIndex; i++) {
	    b = Visual.boxes[i].isSelected();
	    Visual.boxes[i].setSelected(!b);	    
	}
    }

}
