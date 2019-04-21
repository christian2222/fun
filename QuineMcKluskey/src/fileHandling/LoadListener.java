package fileHandling;

import gui.Visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 * This actionlistener loads the maxIndex and the monoms contained in a file.
 * The file-statdard save format is .qmc.html and the contents can be viewed
 * in an browser
 * @author chrissy - date 29-03-2011
 *
 */
public class LoadListener implements ActionListener {

    @Override
    /**
     * loads the file with a filechooser
     */
    public void actionPerformed(ActionEvent e) {

	// update the status
	Visual.statusInfo.setText("Loading a file...");
	// create the file chooser 
	JFileChooser fc = new JFileChooser();
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fc.setFileFilter(new FilesFilter());
	// shows the file-open dialog
	int i = fc.showOpenDialog(Visual.mFrame);

	if( (i == JFileChooser.APPROVE_OPTION) && (fc.getSelectedFile() != null) )
	{
	 // if we confirm loading a file which isnt null
	    // load the file
	    File datei = fc.getSelectedFile();

	    // the "Are you sure"-dialog
	    String frage =  "Attention: If you load a new file"+
	    				"all unsaved data will be lost \n"+
	    				" \n"+
	    				"Are you sure to load["+datei.getName()+"]?";
	    
	    int n = JOptionPane.showConfirmDialog(
	            	Visual.mFrame,
	            	frage,
	            	"Laden einer Datei bestätigen",
	            	JOptionPane.YES_NO_OPTION,
	            	JOptionPane.INFORMATION_MESSAGE
	            	);
	    
	    // If we confirm we load the file using the Data-class
	    if(n == JOptionPane.OK_OPTION)
	    {
		Data.loadFromDisk(datei);
	    }
	}

    }
}
