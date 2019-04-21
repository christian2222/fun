package gui.menuentries;

import gui.Visual;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;

import algorithm.*;

/**
 * This listener Runs the QMC-algorithm beside a selected file and
 * produces/overwrites existing files [out.tex],[report.txt],
 * [test.txt] and [log.txt] 
 * @author chrissy - date 28-03-2011
 *
 */
public class RunListener implements ActionListener {

    @Override
    /**
     * In the file-dialog of this listener you choose a file.
     * The output files are placed in the same directory as the selected
     * file. If the files already exist in the directory, they'll
     * be overwritten.
     */
    public void actionPerformed(ActionEvent arg0) {

	// construct the FileChooser
	JFileChooser chooser = new JFileChooser();
	chooser.setCurrentDirectory(new File("."));
	chooser.setDialogTitle("Run QuineMcKlitschko in the same Directory as...");
	chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	// enable "All Files"-Option
	chooser.setAcceptAllFileFilterUsed(true);
	// show the dialog
	if(chooser.showSaveDialog(Visual.mFrame)==JFileChooser.APPROVE_OPTION) {
	    // save button have been pressed
	    File f = chooser.getCurrentDirectory();
	    // save the current directory in a string
	    String directory = chooser.getCurrentDirectory().toString();
	    
	    // shows a Yes/No Dialog: Are you sure?
	    Object options[] = {"Yes","No"};
	    int n= JOptionPane.showOptionDialog(chooser, 
			"Warning: The files [test.txt],[report.txt],"+
			"[log.txt] and [out.tex] in the "+
			"directory "+directory+"\\ will be overwritten.\n",
			"Are you sure?",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE,
			null,
			options,
			options[1]);
	    
	    if(n == JOptionPane.YES_OPTION) {
		// only if the Yes-Button is pressed:
		// run the QMC-algorithm in the choosen direcory
		Visual.statusInfo.setText("Starting the algorithm in "+directory+"\\ - please wait...");
		// Streams are initialized to produce and write the different files
		Streams.initDirectory(directory);
		Streams.init();
		// run the complete QuineMcKluskey-Algorithm
		// with the monomList we get from the Visual class
		// (this is the monomlist which is in the northern list
		//  of the main frame)
		QMCAlgorithm.completeAlgorithm(Visual.getMonomList());
		// show in which directory the files were written
		Visual.statusInfo.setText("Output to "+directory+"\\");
		// close the streams that arent System.out
		Streams.close();
	    }
	  }
	else {
	// we didnt choose the Save-Button in the FileChooser-dialog
	    //Do nothing
	}
	    
    } // actionPerformed
    
}
