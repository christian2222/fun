import javax.swing.JOptionPane;

/*
 * Created on 11.10.2004
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
public class Exit
{
	public static void systemExit()
	{
	    String warning = "Achtung: Falls Sie das Programm beenden, "+
	    				 "gehen nicht gespeicherte Daten verloren! \n"+
	    				 " \n"+
	    				 "Möchten Sie das Programm wirklich beenden?";
	    
	    int n = JOptionPane.showConfirmDialog(
	            	Visual.mainFrame,			// Vater-Fenster
	            	warning,					// Meldung
	            	"Wirklich beenden?",		// Titel
	            	JOptionPane.YES_NO_OPTION,	// Button-Konfiguration
	            	JOptionPane.WARNING_MESSAGE // Meldung als Warnung konfigurieren
	            );
	    
	    if(n == JOptionPane.YES_OPTION)
	    {
	    	System.exit(0);
	    }	    
	}
}
