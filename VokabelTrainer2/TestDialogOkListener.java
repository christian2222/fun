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

public class TestDialogOkListener implements ActionListener
{
	public void actionPerformed(ActionEvent ae)
	{
	    double quote = 0;
	    double note = 0;
	    String statistics = "";
	    String fehlerMeldung = "";
	    boolean mitBravour = false;
	    Vokabel vok;
	    Node n = null;
	    String input = "";
	    String solution = "";
	    if(Data.testList.isNotEmpty())
	    {
	        input = Visual.td.getEingabe();
	        input = input.trim();
	        solution = Data.testVok.getVokabel();
	        
	        if(input.equals(solution))
	        {
	            Values.rightVok++;
	            Data.testVok.setWdh(Data.testVok.getWdh()-1);
	            if(!Data.testVok.isToWdh())
	                Data.testList.erase(Data.testVok);
	            fehlerMeldung = "Richtig! \n"+
	            				" \n";
	        }
	        else
	        {
	            fehlerMeldung = "Falsch: \n"+
	            				Data.testVok.getWort()+" ~ "+Data.testVok.getVokabel()+"\n"+
	            				" \n";
	            Values.falseVok++;
	            n = Data.testList.getNodeOfObject(Data.testVok);
	            vok = (Vokabel)n.getContent();
	            Values.toTest = Values.toTest - vok.getWdh() +1; //diese Vokabel wurde gerade getestet!
	            vok.setWdh(Values.fehlerWdh);
	            Values.toTest += Values.fehlerWdh;
	        }
	        
	        Values.tested++;
 
	        quote = 100*(((double)Values.rightVok)/Values.tested);
	        quote = Math.round(100*quote)/100;
	        note = 6-(5.0*quote)/100;
	        note = Math.round(100*note)/100;
			mitBravour = (Values.falseVok < 1);

	        statistics = 	"STATISTIK \n"+
        					"********* \n"+
        					" \n"+	        					
        					" Richitge Antworten: "+Values.rightVok+"\t"+"Falsche Antworten: "+Values.falseVok+"\n"+	        					
        					" Prozentual richitg: "+quote+"\t"+" Prozentual falsch: "+(100-quote)+"\n"+	        					
        					" \n"+	        					
        					" Note: "+note      					
        					;
			if(mitBravour)
			    statistics += " *** \t (mit Auszeichnung)";
			
			statistics += 	" \n";
			
			statistics += 	" \n"+
							" \n";

			statistics += 	" Verbleibende Abfragen: "+(Values.toTest-Values.rightVok-Values.falseVok)+"\n";


			Data.testVok = (Vokabel)Data.testList.getRandomObject();
			
	        if(Data.testVok != null)
	        {
	        	Visual.td.setVorgabe(Data.testVok.getWort());
	        	Visual.td.setEingabe("");
	        }
	        else
	        {
	            
	            Visual.td.setVorgabe("*** Herzlichen Gl�ckwunsch ***");
	            Visual.td.setEingabe("Test abgeschlossen");
	            Visual.td.disableEingabe();
	            Visual.td.disableOkButton();
	            Visual.td.setOkButtonLabel("Fertig");
	            Visual.td.setCancelButtonLabel("Schliessen");
	        }

			Visual.td.setInfo(fehlerMeldung+statistics);
			
			Visual.td.setToTopPosition();
	    }
	}
}
