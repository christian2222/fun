import data.*;
import java.io.*;


public class Data
{
	public static DoubleLinkedList vokList = new DoubleLinkedList();
	public static DoubleLinkedList testList = new DoubleLinkedList();
	public static Vokabel testVok = new Vokabel();

	
	public static void sort()
	{
	    Node n = null;
	    Object o = null;
	    String first = "";
	    String second = "";
	    
	    while(!isSorted())
	    {
	        if(vokList.isNotEmpty())
	        {
	            n = vokList.getHead();
	            while (n.getNextNode() != null)
	            {
		            first = ((Vokabel)n.getContent()).getWort().toLowerCase();
		            second = ((Vokabel)n.getNextNode().getContent()).getWort().toLowerCase();

		            //liegt [first] lexikographisch hinter [second] ?
	    	        if(first.compareTo(second) > 0)
	    	        {
	    	            // => nun müssen die Inhalte vertauscht werden
	    	            o = n.getContent();
	    	            n.setContent(n.getNextNode().getContent());
	    	            n.getNextNode().setContent(o);
	    	        }
	            
	        	    n = n.getNextNode();    
	            }
	        }
	    }
	}
	
	public static boolean isSorted()
	{
	    boolean sorted = true;
		Node n = null;
		String first = "";
		String second = "";
		Object o = null;
		
		
	    if(vokList.isNotEmpty())
	    {
	        n = vokList.getHead();
	        while(n.getNextNode() != null)
	        {
	            first = ((Vokabel)n.getContent()).getWort().toLowerCase();
	            second = ((Vokabel)n.getNextNode().getContent()).getWort().toLowerCase();
	            
	            sorted = sorted && (first.compareTo(second) <= 0); // ,dh. [first] liegt lexikographisch vor [second]
	            
	            n = n.getNextNode();
	        }
	    }
	    
	    return sorted;
	}
	
	public static void swipList()
	{
	    DoubleLinkedListEnumeration enumeration = new DoubleLinkedListEnumeration(vokList);
	    Vokabel vok = null;
	    
	    while(enumeration.hasMoreElements())
	    {
	        vok = (Vokabel)enumeration.nextElement();
	        vok.swip();
	    }
	}
			
	public static void loadFromDisk(File datei,boolean deleteBefore)
	{
		try
		{
		    InputStreamReader isr = new InputStreamReader(new FileInputStream(datei));
		    BufferedReader loadFile = new BufferedReader(isr);
		    String s = "loading File:\n";
		    String wortString = "";
		    String vokString = ""; 
		    boolean hasWort = false;
		    boolean hasVok = false;
		    boolean listDeleted = !deleteBefore;
		    while(s != null)
		    {
				s = loadFile.readLine();
				if(s != null)
				    s = s.trim();
				while( (s != null) && (!s.equals("<!-- Datenende -->")) )
				{
				    if(s.equals("<!-- beginBlock -->"))
				    {
				        hasWort = false;
				        hasVok = false;
				        
				        while( (s != null) && (!s.equals("<!-- endBlock -->")) )
				        {
				            if(s.startsWith("<!-- wortData -->"))
				            {
				                wortString = s.substring(17);
				                hasWort = true;
				            }
				            
				            if(s.startsWith("<!-- vokData -->"))
				            {
				                vokString = s.substring(16);
				                hasVok = true;
				            }
				            
						    // nächste Zeile holen
							s = loadFile.readLine();
							if(s != null)
							    s = s.trim();
				            
				        } // (s == null) || (s == "<!-- endBlock -->")
				        
				        if(s.equals("<!-- endBlock -->"))
				        {
				            if(hasWort && hasVok)
				            {		
				                if(!listDeleted)
				                {
				                    vokList.eraseAll();
				                    listDeleted = true;
								    Visual.mainFrame.setTitle("Vokabel-Trainer   ["+datei.getName()+"]");
				                }
			                
				                hasWort = false;
				                hasVok = false;
				                vokList.add(new Vokabel(wortString,vokString));
				            }
				        }
				    }
				    
				    // nächste Zeile holen
					s = loadFile.readLine();
					if(s != null)
					    s = s.trim();
					
				} // (s == null) || (s == "<!-- Datenende -->")
		    } // (s == null)
		} // try
		catch(Exception e)
		{
		    System.out.println(e);
		}
	}
	
	public static void saveToDisk(PrintStream saveFile, String title)
	{
	    sort();
   	    saveFile.println("<html>");
	    saveFile.println("<!-- WARNING: This is a saveFile-File, please don't modify it! -->");
	    saveFile.println("<head>");
	    saveFile.println("<title>"+title+"</title>");
	    saveFile.println("</head>");
	    saveFile.println("<body>");
	    saveFile.println("<table border=1>");
	    saveFile.println("");
	    saveFile.println("<tr>");
	    saveFile.println("\t<td>"+"<b>Wort</b>"+"</td>");
	    saveFile.println("\t<td>"+"<b>Vokabel</b>"+"</td>");
	    saveFile.println("</tr>");
	    saveFile.println("");
	    saveFile.println("<!-- Datenanfang -->");
	    saveFile.println("");
		DoubleLinkedListEnumeration enumeration = new DoubleLinkedListEnumeration(vokList);
		Vokabel vok = null;
		boolean color = true;
		String farbWert = "\"#E0E0E0\"";
		while(enumeration.hasMoreElements())
		{
		    saveFile.println("<!-- beginBlock -->");
		    vok = (Vokabel)enumeration.nextElement();
		    if(color)
		    	saveFile.println("<tr bgcolor="+farbWert+">");
			else
		    	saveFile.println("<tr>");
		    color = !color;
			    
		    saveFile.println("\t<td>");
		    saveFile.println("\t<!-- wortData -->"+vok.getWort());
		    saveFile.println("\t</td>");
		    saveFile.println("\t<td>");
		    saveFile.println("\t<!-- vokData -->"+vok.getVokabel());
		    saveFile.println("\t</td>");
		    saveFile.println("</tr>");
		    saveFile.println("<!-- endBlock -->");
		    saveFile.println("");
		    
		}
	    saveFile.println("");
	    saveFile.println("<!-- Datenende -->");
	    saveFile.println("</table>");
	    saveFile.println("");
	    saveFile.println("");
	    saveFile.println("");
	    saveFile.println("</body>");
	    saveFile.println("<!-- Next line is last line of this File -->");
	    saveFile.println("</html>");
	    saveFile.close();
	}
	
	public static void saveVisualList()
	{
	    int itemNumber = Values.listModel.getSize();
	  	String s = "";
	  	String wortPart,vokPart;
	  	vokList.eraseAll();
	    for(int i = 0; i < itemNumber; i++)
	    {
	        s = (String) Values.listModel.getElementAt(i);
	        // |~| ist Trennsymbol
	        if(s.indexOf("|~|") != -1)
	        {
		        for(int k = 0; k < s.length(); k++)
		        {
	    	        if( (s.charAt(k) =='|') && (s.charAt(k+1) =='~') && (s.charAt(k+2) =='|') )
	        	    {
	            	    wortPart = s.substring(0,k-1);
	                	vokPart = s.substring(k+3,s.length());
	                	wortPart = wortPart.trim();
	                	vokPart = vokPart.trim();

	                	vokList.add(new Vokabel(wortPart,vokPart));
	                	
		            } // if matches
		        } // durchlaufen des Strings
	        } // if trennender Substring existiert
	        
	    } // für alle Eiträge der Liste

	} // saveVisualList()
	
	public static void loadVisualList()
	{
	    Values.listModel.removeAllElements();
	    Object obj = null;
	    Vokabel vok = null;
	    if (vokList.isNotEmpty())
	    {
	        DoubleLinkedListEnumeration enumeration = 
	            				new DoubleLinkedListEnumeration(vokList);
	        
	        while(enumeration.hasMoreElements())
	        {
	            obj = enumeration.nextElement();
	            vok = (Vokabel)obj;
	            Values.listModel.addElement(vok.toString());
	        }
	        
	    }
	    
	}

}