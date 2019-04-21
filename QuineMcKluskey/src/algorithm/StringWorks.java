package algorithm;

import java.util.*;

/**
 * This class only exists to work with signatures inside the QMC-Algortihm.
 * Perhabs it replaced at some state of the developement. 
 * The aim is to organize the QMC algorithm to start at<br>
 * <br>
 * XXXX<br>
 * continuing on<br> 
 * -XXXX,X-XXX,XX-XX,XXX-X<br> 
 * ...<br>
 * ...<br>
 * until<br>
 * ----X,---X-,--X--,-X---,X----<br>
 * and finally<br>
 *  -----
 * <br>
 * in the case of monom lenth 5.
 * 
 * @author chrissy - date 30-03-2011
 * 
 */
public class StringWorks {

    /**
     * 
     * @param signature the signature
     * @return the number of entries (X) of this signature
     */
    public static int countSignEntries(String signature) {
	int number = 0;
	for (int i = 0; i < signature.length(); i++) {
	    if (signature.charAt(i) == 'X')
		number++;
	}

	return number;
    }
    
    /**
     * converts a complete TreeSet of monoms to a latex-output
     * @param ts generic TreeSet{Monom}
     * @return the generated String
     */
    public static String treeSettoLatex(TreeSet<Monom> ts) {
	StringBuffer sb = new StringBuffer();
	if (!ts.isEmpty()) {
	    sb.append("\\{");
	    Iterator<Monom> it;
	    Monom m;
	    for (it = ts.descendingIterator(); it.hasNext();) {
		m = it.next();
		sb.append(m.toLatex() + ",");
	    }
	    sb.append("\\}\\\\");
	} else
	    sb.append("Empty list\\\\");

	return sb.toString();
    }

    /**
     * 
     * @return the start of a tabbing environment in Latex
     */
    public static String startTabbing() {
	return "\\begin{tabbing}\n"
		+ "\\hspace{3cm}\\=\\hspace{3cm}\\=\\hspace{3cm}\\\\[1cm]";
    }

    /**
     * 
     * @return the end of an tabbing environment in latex
     */
    public static String endTabbing() {
	return "\\end{tabbing}";

    }

    /**
     * For latex-output we place a blank between each character.
     * This is important to achieve a readable output in the
     * latex compiled documents.
     * 
     * @param input
     * @return a signature to latex
     */
    public static String signatureToTex(String input) {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < input.length(); i++) {
	    sb.append(input.charAt(i) + " ");
	}

	return sb.toString();
    }
    
    /**
     * 
     * @param str
     * @return the corresponding monom to the String str
     */
    public static Monom binaryStringToMonom(String str) {
	Monom m = new Monom();
	int k = 0;
	char c = ' ';
	if(str.length() == Constraints.maxIndex-1) {
	    for(int i = 0; i < str.length(); i++) {
		c = str.charAt(i);
		if(c == '1')
		    k = 1;
		else
		    k = 0;
		m.setIntMonome(i+1, k);
	    }
	    return m; 
	}
	else 
	    return null;
	    
    }

}
