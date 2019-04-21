package gui.menuentries;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Action performed by the "Clear"-Option in the List-Menu.<br>
 * @author chrissy - date 28-03-2011
 *
 */
public class ClearListListener implements ActionListener {

    @Override
    /**
     * delete the complete list by deleting the listModel.
     */
    public void actionPerformed(ActionEvent e) {
	Visual.listModel.clear();
	Visual.statusInfo.setText("No more elements.");
    }

}
