/*
 * Created on 05.10.2004
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
import javax.swing.filechooser.*;
import java.io.File;

public class DateiFilter extends FileFilter
{
		public boolean accept(File datei)
		{
		    return ( datei.getName().endsWith(".vok.html") );
		}
		
		public String getDescription()
		{
		    return "*.vok.html";
		}
}
