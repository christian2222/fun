package gui.buttons;


import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Action performed by the "Add monom"-Button.<br>
 * <br>
 * Important: The listener dont add monoms which are already in the list.
 * @author chrissy - date 28-03-2011
 *
 */
public class AddListener implements ActionListener {

    @Override
    /**
     * Check weather the monom (represented by the checkboxes) 
     * we want to add - given as a string [s] -
     * is already in the list; more exactly in the [listModel] since
     * the list is only a representation of its model.
     */
    public void actionPerformed(ActionEvent arg0) {
	//create a string out of the Checkboxes
	String s = Visual.ChecksToString();
	// check weather the created String is already in the list(Model)
	// spearate this "check" to an own method of the Visual-class.
	boolean alreadyExists = false;
	int k = 0;
	for(int i = 0; i < Visual.listModel.getSize(); i++) {
	    if(Visual.listModel.get(i).equals(s)) {
		alreadyExists = true;
		k = i;
	    }
	}
	/*
	 *  iff s isnt in the model we add it,
	 *  otherwise we report by the statusInfo-text that this
	 *  specific monom already exists.
	 */
	if(!alreadyExists)
	    Visual.listModel.addElement(s);
	else {
	    Visual.statusInfo.setText("Monom ["+s+"] already exists.");
	    Visual.list.setSelectedIndex(k);
	}
    }

}
