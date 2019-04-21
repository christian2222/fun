package fileHandling;

import javax.swing.filechooser.*;
import java.io.File;

/**
 * This file represents a file filter which chooses .qmc.html-files.<br>
 * It is important for loading and saving the monom lists to a file.
 * 
 * @author chrissy - 28-02-2011
 *
 */
public class FilesFilter extends FileFilter
{
		public boolean accept(File datei)
		{
		    return ( datei.getName().endsWith(".qmc.html") );
		}
		
		public String getDescription()
		{
		    return "*.qmc.html";
		}
}
