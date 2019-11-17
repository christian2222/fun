import java.awt.event.*;




public class OptionOkListener implements ActionListener
{
	private String sWdh, sFehlerWdh;
	private int wdh = 0;
	private int fehlerWdh = 0;
	private boolean ok = false;
	
	public void actionPerformed(ActionEvent ae)
	{
		sWdh = (String) OptionDialog.wdhBox.getSelectedItem();
		sFehlerWdh = (String) OptionDialog.fehlWdhBox.getSelectedItem();
		ok = true;
		try
		{
			wdh = Integer.parseInt(sWdh);
			fehlerWdh = Integer.parseInt(sFehlerWdh);
		}
		catch(Exception e)
		{
			ok = false;
		}
		
		if( Values.isKorrekt(wdh,fehlerWdh) && ok )
		{
			Values.wdh = wdh;
			Values.fehlerWdh = fehlerWdh;
			Values.save();
			Visual.statusZeile.setText("Einstellungen gespeichert.");
		}
		else
			Visual.statusZeile.setText("WARNUNG: Fehler beim speichern der Einstellungen.");
		
		Visual.od.setVisible(false);
		
	}
}