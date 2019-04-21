import java.awt.event.ActionEvent;

/*
 * Created on 03.10.2004
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

public class HinzuOkListener implements ActionListener
{
    private String wortStr,vokStr;
    
	public void actionPerformed(ActionEvent ae)
	{
	    wortStr = Visual.hd.getWort();
	    vokStr = Visual.hd.getVokabel();
	    Values.listModel.addElement(wortStr+" |~| "+vokStr);
	    Visual.hd.setVisible(false);
	    Values.liste.setSelectedIndex(Values.listModel.getSize()-1);
	    Values.liste.ensureIndexIsVisible(Values.listModel.getSize()-1);
	    

	    Visual.hd.setWort("");
	    Visual.hd.setVokabel("");
	}
}
