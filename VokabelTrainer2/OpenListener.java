import java.awt.event.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.*;

public class OpenListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
		Visual.statusZeile.setText("der openListener wurde aufgerufen");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new DateiFilter());
		//fc.setCurrentDirectory(); eventuell
		int i = fc.showOpenDialog(Visual.mainFrame);
		
		if( (i == JFileChooser.APPROVE_OPTION) && (fc.getSelectedFile() != null) )
		{
		    File datei = fc.getSelectedFile();

		    String frage =  "Vorsicht: Beim laden einer neuen Datei "+
		    				"gehen nicht gespeicherte Daten verloren \n"+
		    				" \n"+
		    				"Sind Sie sicher, daß Sie die Datei ["+
		 					datei.getName()+"] laden möchten?";
		    
		    int n = JOptionPane.showConfirmDialog(
		            	Visual.mainFrame,
		            	frage,
		            	"Laden einer Datei bestätigen",
		            	JOptionPane.YES_NO_OPTION,
		            	JOptionPane.INFORMATION_MESSAGE
		            	);
		    
		    if(n == JOptionPane.OK_OPTION)
		    {
				Data.loadFromDisk(datei,true);
				Data.sort();
		    }
		}
				
	}
}