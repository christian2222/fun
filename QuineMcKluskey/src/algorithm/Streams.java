package algorithm;

import java.io.*;

/**
 * This class controls all streams which are used by the program.<br>
 * There are different streams for an comprehensive report, for a file, for the
 * output-TeXfile for the console and for further aspects.<br>
 * See the documentation of the different streams.<br>
 * Note that we also pack streams together; meaning we report some aspects to
 * more than one stream.<br>
 * <b>Note:</b> You have to call the init-Method of this class to force the
 * things going right.<br>
 * <br>
 * Since streams can't have 2 or more outputs we need to copy commands we send
 * to one stream also to print on the other stream.<br>
 * The MultiplePrintStream class provides such a possibility by using an
 * array-implementation of a PrintStream.
 * 
 * @author chrissy - date 30-03-2011
 * 
 * 
 */
public class Streams {

    /**
     * This stream is used to represent the (short) console-output of this
     * program
     */
    public static PrintStream out = System.out;

    /**
     * This stream is used for a comprehesive report of all activities of this
     * program
     */
    public static PrintStream report = System.out;

    /**
     * This is a stream for a log file where some unimportant logs are written
     */
    public static PrintStream log = System.out;

    /**
     * This stream is used for a file output of this program
     */
    public static PrintStream file = System.out;

    /**
     * This stream is used for creating and reporting the TeXFile which is the
     * main intention of this program
     */
    public static PrintStream texFile = System.out;

    /**
     * the <i>directory</i> is really the directory where all
     * files of the different file-streams are stored
     */
    protected static String directory = "";

    /**
     * initializes the directory
     * @param dir the directroy
     */
    public static void initDirectory(String dir) {
	directory = dir+"\\\\";
    }
    
    /**
     * set all streams to the printstream <i>ps</i>
     * @param ps
     */
    public static void setAllStreams(PrintStream ps) {
	out = ps;
	report = ps;
	file = ps;
	texFile = ps;
	log = ps;
    }

    /**
     * close all streams that are not System.out<br>
     * If we would close System.out we couldnt write on the console
     * any more.
     */
    public static void close() {
	if (out != System.out)
	    out.close();
	if (report != System.out)
	    report.close();
	if (file != System.out)
	    file.close();
	if (texFile != System.out) {
	    texFile.println("\\end{document}");
	    texFile.close();
	}
	if (log != System.out)
	    log.close();
    }

    /**
     * filestream for the test-file
     */
    public static FileOutputStream fos;
    /**
     * filestream for the report-file
     */
    public static FileOutputStream fos1;
    /**
     * filestream for the log-file
     */
    public static FileOutputStream logFos;
    /**
     * filestream for the latex-file
     */
    public static FileOutputStream texFos;

    /**
     * initializes all streams of this class<br>
     * reports "problem(s) with file(s)" if something went wrong
     */
    public static void init() {
	setAllStreams(System.out);
	String testFile = "test.txt";
	String reportFile = "report.txt";
	String logF = "log.txt";
	String outF = "out.tex";
	if(directory !="") {
	    testFile = directory+testFile;
	    reportFile = directory+reportFile;
	    logF = directory+logF;
	    outF = directory+outF;
	}
	File file = new File(testFile);
	File repFile = new File(reportFile);
	File logFile = new File(logF);
	File tFile = new File(outF);
	try {
	    fos = new FileOutputStream(file);
	    fos1 = new FileOutputStream(repFile);
	    logFos = new FileOutputStream(logFile);
	    texFos = new FileOutputStream(tFile);
	    PrintStream ps = new PrintStream(fos);
	    PrintStream psRep = new PrintStream(fos1);
	    PrintStream logS = new PrintStream(logFos);
	    PrintStream texS = new PrintStream(texFos);
	    out = ps;
	    report = psRep;
	    log = logS;
	    texFile = texS;
	    initTexFile();
	} catch (Exception e) {
	    System.out.println("problem(s) with file(s)");
	}
	initMultiplePrintStreams();
    }

    /**
     * provides a latexHead written to the <i>texFile</i>
     */
    public static void initTexFile() {
	texFile.println("\\documentclass{article}");
	texFile.println("\\usepackage{multicol}");
	texFile.println("\\renewcommand{\\em}{\\bf}");
	texFile.println("\\begin{document}");
	texFile.println("");

    }

    /**
     * Now we want to join different streams in different names using the
     * MultiplePrintStream-class<br>
     * Since we have also single PrintStream like <i>report</i> or <i>file</i>
     * inside this class, we mark with the m at the begining a multiple
     * PrintStream.
     */
    /**
     * MPS for report and latex
     */
    public static MultiplePrintStream mRepAtex = new MultiplePrintStream();
    /**
     * MPS for report and out
     */
    public static MultiplePrintStream mRepAout = new MultiplePrintStream();
    /**
     * MPS for all streams (including System.out)
     */
    public static MultiplePrintStream mAll = new MultiplePrintStream();

    /**
     * this boolean is used to control the successful creation of the
     * MultiplePrintStreams
     */
    protected static boolean mpsSuccess;

    /**
     * initializes all MultiplePrintStreams
     */
    public static void initMultiplePrintStreams() {
	initMALLPrintStreams();
	initMREPATEXPrintStream();
	initMREPAOUTPrintStream();
    }

    /**
     * initializes report and out
     */
    public static void initMREPAOUTPrintStream() {
	mRepAout.init(2);
	mpsSuccess = mRepAout.setPrintStream(0, report);
	mpsSuccess = mpsSuccess && mRepAout.setPrintStream(1, out);
	reportSuccess("REPAOUT");
    }

    /**
     * initializes report and latex
     */
    public static void initMREPATEXPrintStream() {
	mRepAtex.init(2);
	mpsSuccess = mRepAtex.setPrintStream(0, report);
	mpsSuccess = mpsSuccess && mRepAtex.setPrintStream(1, texFile);
	reportSuccess("REPATEX");

    }

    /**
     * This method has to be called before using the ALL-PrintStream
     */
    public static void initMALLPrintStreams() {
	mAll.init(5);
	mpsSuccess = mAll.setPrintStream(0, out);
	mpsSuccess = mpsSuccess && mAll.setPrintStream(1, file);
	mpsSuccess = mpsSuccess && mAll.setPrintStream(2, report);
	mpsSuccess = mpsSuccess && mAll.setPrintStream(3, texFile);
	mpsSuccess = mpsSuccess && mAll.setPrintStream(4, log);
	reportSuccess("ALL");
    }

    /**
     * reports for every MultiplePrintStream if it was sucessful or not
     * @param mpsName name of the MultiplePrintStream
     */
    public static void reportSuccess(String mpsName) {
	if (mpsSuccess) {
	    System.out.println("The [" + mpsName + "]-PrintStream is ready");
	} else {
	    System.out.println("ATTENTION: [" + mpsName
		    + "]-PrintStream not availible");
	}
    }

}
