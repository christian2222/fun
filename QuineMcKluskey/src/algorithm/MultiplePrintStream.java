package algorithm;

import java.io.*;

/**
 * This class is used to copy one Printstream to multiple outputs. You find an
 * example in the main-method which prints parallel to a file and the System.out
 * PrintStream.<br>
 * <b>Note:</b> You have to run the init-method before using a
 * {@link MultiplePrintStream}.
 * 
 * @author chrissy - date 30-03-2011
 * 
 */
public class MultiplePrintStream {

    /**
     * This array contains all Printstreams. A "println()" call prints once on
     * each PrintStream inside the array
     */
    protected PrintStream[] psArray;

    /**
     * This is a standard PrintStream initialized with System.out.<br>
     * An exceptions can be thrown by the initialization of all PrintStreams -
     * ie. they are a file or something similar. Iff at least one
     * PrintSream-Initialization failes all PrintStreams are redirected to this
     * standard PrintStream
     * 
     */
    protected PrintStream standard;

    /**
     * The constructor initializes the standard PrintStream with System.out.
     * because this PrintStream is save to print on it.
     */
    public MultiplePrintStream() {
	this.standard = System.out;
    }

    /**
     * This method initializes the inside PrintStream-Array <i>psArray</i> with
     * k different Printstreams.<br>
     * Each PrintStream is initialized by the <i>standard</i> PrintStream of the
     * class.
     * 
     * @param k
     *            number of different PrintStreams
     */
    protected void init(int k) {
	this.psArray = new PrintStream[k];
	for (int i = 0; i < this.psArray.length; i++) {
	    this.psArray[i] = this.standard;
	}
    }

    /**
     * This method is used to delegate the PrintStream with index k to antoher
     * Stream. For example you can delegate such a stream to a file or something
     * else.<br>
     * For more details of such a file delegation see the {@link algorithm} -method.
     * 
     * @param k
     *            index of the PrintStream to set to <i>ps</i>
     * @param ps
     *            PrintStream you set the kth PrintStream to
     * @return a boolean that reports the success of this operation
     */
    public boolean setPrintStream(int k, PrintStream ps) {
	boolean success;
	if ((k < this.psArray.length) && (ps != null)) {
	    this.psArray[k] = ps;
	    success = true;
	} else {
	    success = false;
	    /*
	     * if(k >= this.psArray.length) throw new
	     * ArrayIndexOutOfBoundsException("Falscher Index"); if(ps == null)
	     * throw new
	     * NullPointerException("Kein gültiger Printstream-Parameter");
	     */
	}

	return success;
    }

    /**
     * print through all streams
     * 
     * @param o
     *            printed generic object
     */
    public void print(Object o) {
	for (int i = 0; i < this.psArray.length; i++) {
	    this.psArray[i].print(o);
	}
    }

    /**
     * print through all streams and begin a new line
     * 
     * @param o
     *            printed generic object
     */
    public void println(Object o) {
	for (int i = 0; i < this.psArray.length; i++) {
	    this.psArray[i].println(o);
	}
    }

    /**
     * print through all streams to begin a new line
     */
    public void println() {
	for (int i = 0; i < this.psArray.length; i++) {
	    this.psArray[i].println();
	}
    }

    /**
     * close all Streams that are not System.out
     */
    public void close() {
	for (int i = 0; i < this.psArray.length; i++) {
	    /*
	     * We don't close System.out, because iff we would close System.out
	     * we cannot write on the console any more! Hence we only close all
	     * other Printstreams like files etc...
	     */
	    if (this.psArray[i] != System.out) {
		this.psArray[i].close();
	    }
	}
    }

    /**
     * example to print parallel to a file and the standard output<br>
     * Inside you can change the mps.print and mps.println methods to see the
     * new effect.<br>
     * Note: The MultiplePrintStream - as a single Pintstream - also have to be
     * closed.
     * 
     * @param args
     */
    public static void main(String[] args) {
	// use of MultiplePrintStream
	MultiplePrintStream mps = new MultiplePrintStream();
	// init 2 PrintStreams
	mps.init(2);
	boolean success = true;
	FileOutputStream fos;
	PrintStream file = new PrintStream(System.out);
	// constructing a file
	try {
	    fos = new FileOutputStream(new File("copyOutput.txt"));
	    file = new PrintStream(fos);
	} catch (Exception e) {
	    System.out.println("Warning: An error has occured");
	}
	/*
	 * delegate the MultiplePrintStream to the file and the standard output
	 */
	success = mps.setPrintStream(0, System.out);
	success = success && mps.setPrintStream(1, file);

	/*
	 * Iff everthing was successful we write on the MultiplePrintStream,
	 * hence writing parallel to the standard output and the file
	 */
	if (success) {
	    mps.print("Hello guys, ");
	    mps.print("how are you? ");
	    mps.println("fine.");
	    mps.println();
	    mps.println();
	    mps.println("Goodbye!");
	    mps.close();
	}
	/*
	 * Attention: Iff you close the System.out Stream you cannot write on
	 * the console any more.
	 * 
	 * Since we have closen only the Streams that are not System.out you can
	 * see the following lines on the command line but not inside the file
	 * we've created.
	 */
	System.out.println("huhu");
    }

}
