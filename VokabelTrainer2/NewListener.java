import java.awt.event.*;
import javax.swing.*;


public class NewListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	    String warning = "Achtung! Beim anlegen einer neuen Datei, werden nicht "+
	    				 "gespeicherte Daten gelöscht! \n"+
	    				 "Möchten Sie wirklich eine neue Liste anlegegen?";
	    int n = JOptionPane.showConfirmDialog(
	            Visual.mainFrame,
	            warning,
	            "Sicherheitsabfrage",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE
	            );
	    
	    if(n == JOptionPane.OK_OPTION)
	    {
			Values.listModel.removeAllElements();
			Visual.mainFrame.setTitle("Vokabel-Trainer   *Unbenannt*");
			Visual.bd.show();
	    }
	}
}