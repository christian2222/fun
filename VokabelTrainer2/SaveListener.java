import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class SaveListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
        String title = "";
		String frage = "";
		int n = 0;
		boolean exists = false;

		Visual.statusZeile.setText("der saveListener wurde aufgerufen");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new DateiFilter());
		int i = fc.showSaveDialog(Visual.mainFrame);
		
	    File datei = fc.getSelectedFile();
		File testDatei = fc.getSelectedFile();
		
		if(datei != null)
		{
			// Konstruktion des vollständigen Datei-Namens
			if( !(datei.getName().endsWith(".vok.html")) )
		    {	
		    	testDatei = new File(datei.getPath()+".vok.html");
	    	}
		    else
		        testDatei = new File(datei.getPath());

			// existiert die ausgewählte Datei schon?
			try
	    	{
		        exists = testDatei.exists();
		    }
	    	catch(Exception e)
		    {
		        // Fehler ist aufgetreten
	    	    exists = false;
		    }
		} // if (datei != null)
		
	    // wurde der Dialog bestätig?
		if( (i == JFileChooser.APPROVE_OPTION) && (fc.getSelectedFile() != null) )
		{
		    // existiert die Datei
			if(exists)
			{
	        	frage =  "WARNUNG: Diese Datei existiert bereits. \n"+
		    	  		 "Die Inhalte einer alten Datei gehen verloren! \n"+
		    			 " \n"+
		    			 "Sind Sie sicher, daß Sie die aktuelle Liste "+
		 				 "als Datei ["+datei.getName()+"] speichern möchten?";
		    
		    	n = JOptionPane.showConfirmDialog(
		            	Visual.mainFrame,
		           		frage,
		           		"Speicher in eine Datei",
		           		JOptionPane.YES_NO_OPTION,
			           	JOptionPane.ERROR_MESSAGE
		           	);
			}
			
			if( (!exists) || (exists && (n == JOptionPane.YES_OPTION)) )
			{
			
			    try
			    {
				    if( !(datei.getName().endsWith(".vok.html")) )
				    {
				        title = datei.getName();
				    	datei = new File(datei.getPath()+".vok.html");
				    }
				    else
			        	title = datei.getName().substring(0,datei.getName().length()-1);
				    PrintStream save = new PrintStream(new FileOutputStream(datei));
				    Data.saveToDisk(save,title);
				    Visual.mainFrame.setTitle("Vokabel-Trainer   ["+datei.getName()+"]");
			    }
			    catch(Exception e)
		    	{
			        Visual.statusZeile.setText("WARNUNG: Fehler beim speichern der Datei");
			    }
			} // if( (!exists) || (exists && (n == JOptionPane.YES_OPTION)) )
		} //if (i == JFileChooser.APPROVE_OPTION)
	} // actionPerformed(ActionEvent ae)
} // class