import javax.swing.*;


import java.io.*;


public class Values
{
	public static int wdh = 1;
	public static int fehlerWdh = 2;
	public static long toTest = 0;
	public static long rightVok = 0;
	public static long falseVok = 0;
	public static long tested = 0;
	
	public static String loadedFile = "";
	public static String workingDirectory = "";
	
	public static DefaultListModel listModel;
	public static JList liste;
	
	
	public static void init()
	{
	    listModel = new DefaultListModel();
	    liste = new JList(listModel);
	    liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    liste.setSelectedIndex(0);
	    loadedFile="*Unbenannt*";
	    load();
	}
	
	public static void save()
	{
		try
		{
			PrintStream target = new PrintStream(new FileOutputStream(new File("options.sav")));
			target.println("WARNUNG:");
			target.println("Dies ist eine Konfigutrations-Datei, ändern Sie die Inhalte nicht!");
			target.println("***");
			target.println("Wiederholung:"+wdh);
			target.println("***");
			target.println("Wiederholung falscher Vokabeln:"+fehlerWdh);
			target.println("***");
			target.close();
			Visual.statusZeile.setText("Einstellungen gespeichert");
		}
		catch (Exception e)
		{
			Visual.statusZeile.setText("WARNUNG: Fehler beim Speichern der Einstellungen");
		}
	}
	
	public static boolean isKorrekt(int paramWdh, int paramFehlerWdh)
	{
		return ( (paramWdh > 0)&&(paramWdh < 10) && 
					(0 < paramFehlerWdh)&&(paramFehlerWdh < 10) );
	}
	
	public static void setValues(int paramWdh, int paramFehlerWdh)
	{
		wdh = paramWdh;
		fehlerWdh = paramFehlerWdh;
		
		OptionDialog.wdhText.setText(""+wdh);
		OptionDialog.falschWdhText.setText(""+fehlerWdh);
		
		Visual.statusZeile.setText("Es wurden (neue) Einstellungen geladen:");
	}
	
	public static void load()
	{
		String s = "";
		int wdh = 0;
		int fehlerWdh = 0;

		try
		{
			FileInputStream fs = new FileInputStream(new File("options.sav"));
			BufferedReader fileInput = new BufferedReader(new InputStreamReader(fs));
			

			for(;;)  // wie: while(true)
			{
				s = fileInput.readLine();
				
				if( s.startsWith("Wiederholung:") )
				{
					s = s.substring(s.length()-1,s.length());
					wdh = Integer.parseInt(s);
				}
				
				if( s.startsWith("Wiederholung falscher Vokabeln:") )
				{
					s = s.substring(s.length()-1,s.length());
					fehlerWdh = Integer.parseInt(s);					
				}
			} // endlosSchleife
		} // try
		
		catch(Exception e) // z.B. end-of-file Exception
		{
			if( isKorrekt(wdh,fehlerWdh) )
			{
				setValues(wdh,fehlerWdh);
			}
			else
			{
				Visual.statusZeile.setText("WARNUNG: Fehler beim laden der Einstellungen");
				setValues(2,1);
				System.out.println(e.toString());
			}					
		} // catch an Exception
		
	}
}