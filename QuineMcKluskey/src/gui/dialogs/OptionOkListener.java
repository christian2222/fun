package gui.dialogs;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import algorithm.Constraints;
/**
 * Changes the number of entries (represented by the checkboxes)
 * and thus deleting also an (possibly) unsaved list.<br>
 * 
 * @author chrissy - date 28-03-2011
 *
 */
public class OptionOkListener implements ActionListener {

    @Override
    /**
     * sets the new entries using the convertFromString()-method
     */
    public void actionPerformed(ActionEvent arg0) {
	Object o = Visual.comboNumber.getSelectedItem();
	String s = (String) o;
	convertFromString(s);
	Visual.optionDialog.setVisible(false);
	
    }
    
    /**
     * converts the inputString of the option dialog checkbox
     * to an integer<br>
     * this integer is used to reset the number of checkboxes which are
     * availible
     * @param s input of checkbox
     * @return integer with the value specified by s
     */
    public void convertFromString(String s) {
	int k = Constraints.maxIndex-1;
	if(
		(s == "2") || (s == "3") || (s == "4") ||
		(s == "5") || (s == "6") || (s == "7") ||
		(s == "8") || (s == "9") || (s == "10")
	  ) {
	    // convert String to int
	    if(s=="2") k = 2;
	    if(s=="3") k = 3;
	    if(s=="4") k = 4;
	    if(s=="5") k = 5;
	    if(s=="6") k = 6;
	    if(s=="7") k = 7;
	    if(s=="8") k = 8;
	    if(s=="9") k = 9;
	    if(s=="10") k = 10;
	    
	    // does the new entry really change something?
	    if(k != Constraints.maxIndex-1) {
		// an option-dialog which asks if we are really sure
		Object[] options = {"Yes","No"};
		
		int n= JOptionPane.showOptionDialog(Visual.optionDialog, 
			"Warning: The actual list will be lost "+
			"(if it wasnt saved)",
			"Really change enties?",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null,
			options,
			options[1]);
		
		if(n == JOptionPane.OK_OPTION) {
		    // clear list and set new availible checkbox-entries
		    Visual.listModel.clear();
		    Visual.enable(k);
		    Visual.statusInfo.setText("Monom legnth changed to "+k);
		}
	    }
	    else {
		// only report, because the entry doesnt really change
		Visual.statusInfo.setText("Monom legnth didnt change");
		
	    }
	    
	}
	// s is not one of "2","3",....,"9","10"
	else {
	    // do nothing
	}
	
    } // end of convertFromString

}
