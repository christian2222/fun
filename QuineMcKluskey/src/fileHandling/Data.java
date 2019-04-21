package fileHandling;

import gui.Visual;

import java.io.*;
import java.util.Iterator;

import algorithm.*;

/**
 * The Data class contains the main methods to save and to load a file
 * to a Printstream or from a BufferedReader.
 * @author chrissy - date 28-03-2011
 *
 */
public class Data {

    /**
     * Saves the current monom-list to disk.<br>
     * Open a saved list in a browser to see the created structure
     * 
     * @param saveFile Printstream we save to
     * @param title of the saved file
     */
    public static void saveToDisk(PrintStream saveFile, String title) {
	boolean color = true;
	String farbWert = "\"#E0E0E0\"";
	saveFile.println("<!-- BeginOfFile -->");
	saveFile.println("<html>");
	saveFile.println("<head>");
	saveFile.println("<title>"+title+"</title>");
	saveFile.println("</head>");
	saveFile.println("<body>");
	saveFile.println();
	saveFile.println("<table border=1>");
	saveFile.println("<tr>");
	saveFile.println("\t<td>");
	saveFile.println("<!-- maxIndex -->");
	saveFile.println(""+(Constraints.maxIndex-1));
	saveFile.println("</td>");
	saveFile.println("</tr>");
	
	saveFile.println("<tr>");
	saveFile.println("\t<td>"+"<b>Monom</b>"+"</td>");
	saveFile.println("</tr>");
	String str = "";
	for(int i = 0; i < Visual.listModel.getSize(); i++) {
		saveFile.println("<tr>");
		saveFile.println("\t<td>");	    
		saveFile.println("<!-- Monom -->");
		str = Visual.listModel.getElementAt(i).toString();
		saveFile.println(str);	    
		saveFile.println("\t</td>");
		saveFile.println("</tr>");
	}
	saveFile.println();
	saveFile.println();
	saveFile.println("</table>");
	saveFile.println("</body>");
	saveFile.println("</html>");
	saveFile.println("<!-- EndOfFile -->");
	saveFile.close();
	Visual.statusInfo.setText("File saved sucessful");
    }

    	/**
    	 * Loads the specified file.
    	 * @param file to be loaded
    	 */
	public static void loadFromDisk(File file) {
	    MonomList ml = new MonomList();
	    Monom m;
	    BufferedReader loadFile;
	    
	    // try the whole routine because we read from a buffered reader
		try
		{
		    // loading the file
		    InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
		    loadFile = new BufferedReader(isr);
		    String s = "loading File:\n";
		    String monom = "";
		    
		    // not at the end of file <-> s != null
		    while(s != null)
		    {
			s = loadFile.readLine();
			// shrink the string to the essential part
			// with the trim()-method
			// trim not senceful !
			if(s != null)	s = s.trim();
			// not at the end of file and not at
			// the marked end of file "<!-- EndOfFile -->"
			while( (s != null) && (!s.equals("<!-- EndOfFile -->")) ) {
			    
			    // In the first part of the file (maxIndex-1)
			    // is saved after the line which contains
			    // <!-- maxIndex --> 
			    if(s.equals("<!-- maxIndex -->")) {
				// load next line
				s = loadFile.readLine();
				// parse this line to an integer
				int i = new Integer(s);
				System.out.println(i);
				// use the parsed integer to set the
				// enabled entries
				// (i is incremented by 1 inside
				// the enable()-method before
				// maxIndex is initialized
				Visual.enable(i);
				
			    }
			    // the monoms are saved as strings after the 
			    // lines which contain <!-- Monom --> 
			    if(s.equals("<!-- Monom -->")) {
				// read the next line which contains the
				// monom as a string
				s = loadFile.readLine();
				// convert the string to a monom
				m = StringWorks.binaryStringToMonom(s);
				// add it iff the conversion was successful
				// see return-value of StringWorks.binaryStringToMonom(s)
				if(m != null) {
				    ml.add(m);
				}
			    }
			    
			    // If the line neither contain <!-- maxIndex -->
			    // nor <!-- Monom --> continue with reading the next line
			    s = loadFile.readLine();
			    
			} // (not equals <!-- EndOfFile --> && (s != null)
			 // hence by deMorgan 
			 // we read "<!-- EndOfFile -->" or s == null
			 // thus we stop reading the file further
		    
		} // s != null
	
		} //end of the try-block
		catch(Exception e) {
		    // an (file) arrow occurs
		    System.out.println(e);
		    Visual.statusInfo.setText("Error while loading file.");
		}
	// we have saved all monoms inside the monomList [ml].
	// now we need to load the monoms contained in [ml] and
	// add the resulting strings to the [listModel] of our list.
	if(!ml.isEmpty()) {
	    Iterator<Monom> it;
	    Visual.listModel.clear();

	    for(it = ml.getIterator(); it.hasNext(); ) {
		m = it.next();
		Visual.listModel.addElement(m.toString());
	    }
	}
	else {
	    // the list is empty or no monom was found
	    Visual.statusInfo.setText("No data found in file.");
	    Visual.statusInfo.setText("Loading complete.");
	}
	
    } // load
	
	
}
