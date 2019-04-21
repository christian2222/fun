package gui;


import javax.swing.JOptionPane;

/**
 * An exit-Listener for the program which asks wheather we really want to
 * close the program.
 * @author chrissy - date 28-03-2011
 * ToDo: exit "no" doesnt work! 
 */
public class Exit
{
    	/**
    	 * We build an OptionPane-Dialog which asks wheater we really want
    	 * to close the program.
    	 */
	public static void systemExit()
	{
	    String warning = "Achtung: Falls Sie das Programm beenden, "+
	    				 "gehen nicht gespeicherte Daten verloren! \n"+
	    				 " \n"+
	    				 "Möchten Sie das Programm wirklich beenden?";
	    
	    int n = JOptionPane.showConfirmDialog(
	            	Visual.mFrame,			// Vater-Fenster
	            	warning,					// Meldungsart
	            	"Wirklich beenden?",		// Titel
	            	JOptionPane.YES_NO_OPTION,	// Button-Konfiguration
	            	JOptionPane.WARNING_MESSAGE // Meldung als Warnung konfigurieren
	            );
	    
	    if(n == JOptionPane.YES_OPTION) {
		// leave program
	    	System.exit(0);
	    }
	    else {
		// do nothing
		// toDo: doesnt work !
		System.out.println("Nothing happend!");
	    }
	}
}
