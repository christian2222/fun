package fileHandling;

import gui.Visual;

import java.awt.event.*;
import javax.swing.*;

import java.io.*;

/**
 * The actionlistener to save a file with a filechooser
 * @author chrissy - date 29-03-2011
 *
 */
public class SaveListener implements ActionListener
{
    /**
     * the accepted file end
     */
    String end = ".qmc.html";
    
    	/**
    	 * saves data to a file with a filechooser
    	 */
	public void actionPerformed(ActionEvent ae)
	{
	    	String title;
        	String frage = "";
		int n = 0;
		boolean exists = false;

		// Inform the user
		Visual.statusInfo.setText("Saving file(s) - please wait...");
		// create a filechooser
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new QmcFileFilter());
		
		int i = fc.showSaveDialog(Visual.mFrame);
		
		// save the selected file
		File datei = fc.getSelectedFile();
		File testDatei = fc.getSelectedFile();
		
		
		if(datei != null) {
		// if the file exists
		
		    // construct the full file name
		    if( !(datei.getName().endsWith(end)) ) {	
			testDatei = new File(datei.getPath()+end);
		    }
		    else
			testDatei = new File(datei.getPath());

		    // does the file already exist?
		    try {
			exists = testDatei.exists();
		    }
		    catch(Exception e) {
			// error occured
			exists = false;
		    }
		    
		} // if (datei != null)
		
		
		if( (i == JFileChooser.APPROVE_OPTION) && (fc.getSelectedFile() != null) )
		{
		    // dialog confirmed and file exists
			if(exists) {
			// the well known "are you sure?"-dialog
	        	frage =  "Attention: This file already exists. \n"+
		    	  		 "The data contained in this file will be overwritten! \n"+
		    			 " \n"+
		    			 "Are you sure to save the actual data to["
		    			 +datei.getName()+"]?";
		    
		    	n = JOptionPane.showConfirmDialog(
		            	Visual.mFrame,
		           		frage,
		           		"Speicher in eine Datei",
		           		JOptionPane.YES_NO_OPTION,
			           	JOptionPane.ERROR_MESSAGE
		           	);
			}
			
			if( (!exists) || (exists && (n == JOptionPane.YES_OPTION)) ) {
			// file doesnt exist yet or "are you sure"-dialog confirmed
			    try
			    {
				    // correct possibly missing file-end
				    if( !(datei.getName().endsWith(end)) )
				    {
				        title = datei.getName();
				    	datei = new File(datei.getPath()+end);
				    }
				    else
					title = datei.getName().substring(0,datei.getName().length()-1);
				    
				    // really save the file
				    PrintStream save = new PrintStream(new FileOutputStream(datei));
				    Data.saveToDisk(save,"QuineMcKlitschko");
				    Visual.mFrame.setTitle("QuineMcKlitschko   ["+datei.getName()+"] ");
			    }
			    catch(Exception e) {
				// some errors occured
			        Visual.statusInfo.setText("Warning: Error while saving file(s)");
			    }

			} // if( (!exists) || (exists && (n == JOptionPane.YES_OPTION)) )

		} //if (i == JFileChooser.APPROVE_OPTION)

	
	} // actionPerformed(ActionEvent ae)


} // class