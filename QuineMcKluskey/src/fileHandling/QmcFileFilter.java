package fileHandling;

import javax.swing.filechooser.*;
import java.io.File;
/**
 * This file represents a file filter which chooses .qmc.html-files.<br>
 * It is important for loading and saving the monom lists to a file.
 * 
 * @author chrissy - 29-03-2011
 *
 */
public class QmcFileFilter extends FilesFilter
{
    /**
     * the end of file which is accepted
     */
    static String end =".qmc.html";
    
    		/**
    		 * accept such files
    		 */
		public boolean accept(File datei)
		{
		    return ( datei.getName().endsWith(end) );
		}
		
		public String getDescription()
		{
		    return "*"+end;
		}
		
		/**
		 * 
		 * @param file
		 * @return the right filename of file
		 */
		public static File getRightFileName(File file) {
			File testDatei = new File("tmp");
			if( !(file.getName().endsWith(end)) )
		    {	
		    	testDatei = new File(file.getPath()+end);
	    	}
		    else
		    {
		        testDatei = new File(file.getPath());
		    }
			
			return testDatei;
		}
}
