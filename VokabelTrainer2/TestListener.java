/*
 * Created on 07.10.2004
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
import data.*;

public class TestListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	    DoubleLinkedListEnumeration enumeration = new DoubleLinkedListEnumeration(Data.vokList);
	    Vokabel vok;
	    
	    while(enumeration.hasMoreElements())
	    {
	        vok = (Vokabel)enumeration.nextElement();
	        vok.setWdh(Values.wdh);
	    }
	    
	    Values.toTest = Data.vokList.countItems()*Values.wdh;
	    Values.rightVok = 0;
	    Values.falseVok = 0;
	    Values.tested = 0;
	    
	    Data.testList.eraseAll();
	    if(Data.vokList.isNotEmpty())
	    {
	        Data.testList = (DoubleLinkedList)Data.vokList.clone();
	        Data.testVok = (Vokabel)Data.testList.getRandomObject();
	        
	        Visual.td.setVorgabe(Data.testVok.getWort());
	        Visual.td.enableEingabe();
            Visual.td.setEingabe("");
            Visual.td.setOkButtonLabel("Ok");
            Visual.td.enableOkButton();
            Visual.td.setCancelButtonLabel("Abbruch");
	        Visual.td.setInfo(
	                "Willkommen beim Vokabel-Test \n"+
	                "Die Wörter der aktuellen Liste werden Ihnen vorgegeben.\n"+
	                "Geben Sie die zugehörigen Vokabel ein. \n"+
	                "\n "+
	                "Viel Spass :)"
		                		 );
	        
	        Visual.td.setVisible(true);
	    }
	    else
	    Visual.statusZeile.setText("FEHLER: Keine Vokabeln zum Abfragen vorhanden.");
	}
}
