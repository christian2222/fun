/*
 * Created on 16.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author chrissy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.event.*;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImportListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		Visual.statusZeile.setText("der importListener wurde aufgerufen");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new DateiFilter());
		//fc.setCurrentDirectory(); eventuell
		int i = fc.showOpenDialog(Visual.mainFrame);
		
		if(i == JFileChooser.APPROVE_OPTION)
		{
		    File datei = fc.getSelectedFile();

		    String frage =  "Wollen Sie wirklich den Inhalt der Datei["+
		    				datei.getName()+"] \n"+
		    				"an die vorhandene Vokabelliste anhängen?";
		    
		    int n = JOptionPane.showConfirmDialog(
		            	Visual.mainFrame,
		            	frage,
		            	"Importieren einer Datei bestätigen",
		            	JOptionPane.YES_NO_OPTION,
		            	JOptionPane.QUESTION_MESSAGE
		            	);
		    
		    if(n == JOptionPane.OK_OPTION)
		    {
				Data.loadFromDisk(datei,false);
				Data.sort();
		    }
		}
				
	}
}
