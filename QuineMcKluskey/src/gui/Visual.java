package gui;


import java.awt.*;
import java.util.*;
import javax.swing.*;

import fileHandling.*;
import gui.buttons.*;
import gui.dialogs.*;
import gui.menuentries.*;
import algorithm.*;

/**
 * The Visual class is a static holder for all gui-items we use.
 * It contains especially the main frame [mFrame] the dialogs [optionDialog]
 * and [addDialog]. The addDialog is the special-add-dialog.<br>
 * The Visual class contains all Buttons, menu-entries and every gui-element
 * you can use.<br>
 * By the method [addListeners()] all menu items and buttons get
 * their corresponding actionlisteners.<br>
 * All gui elements are [public static]; hence they can be 
 * accessed by Visual.[name] - see the different actionlisteners in
 * gui.buttons, gui.dialogs, gui.menuentries
 * for an example.<br>
 * @author chrissy - date 28-03-2011
 *
 */
public class Visual {

    /**
     * the checkboxes in the main frame
     */
    public static JCheckBox[] boxes = new JCheckBox[11];
    /**
     * the menu entries
     */
    public static JMenuBar menuBar = new JMenuBar();

    public static JMenu fileMenu = new JMenu("File");
    public static JMenuItem open = new JMenuItem("Open...");
    public static JMenuItem save = new JMenuItem("Save...");
    public static JMenuItem exit = new JMenuItem("Exit");
    
    public static JMenu listMenu = new JMenu("List");
    public static JMenuItem clearList = new JMenuItem("Clear list");
    public static JMenuItem run = new JMenuItem("Run QMC...");
    public static JMenuItem options = new JMenuItem("Options...");
    public static JMenuItem sAdd = new JMenuItem("Special add...");
    
    /**
     * The main frame and its components
     */
    public static MainFrame mFrame = new MainFrame("QuineMcKluskey",600,400);
    
    /**
     * the monom-list
     */
    public static JList list = new JList();
    /**
     * the monom-list model to manipulate the list-entries
     */
    public static DefaultListModel listModel = new DefaultListModel();
    /**
     * a scroller for the monom-list
     */
    public static JScrollPane scroller = new JScrollPane();
    /**
     * a status-area in the south of the main-frame
     */
    public static JPanel status = new JPanel();
    /**
     * the (textual) content of the status
     */
    public static JLabel statusInfo = new JLabel();
    /**
     * the buttons
     */
    /**
     * add a monom (from the checkboxes)
     */
    public static JButton add = new JButton("Add monom");
    /**
     * remove a monom
     */
    public static JButton remove = new JButton("Remove monom");
    /**
     * invert the current input
     */
    public static JButton invert = new JButton("invert Monom");
    /**
     * clear the current entries
     */
    public static JButton clearEntries = new JButton("clear entries");
    /**
     * set the current entries
     */
    public static JButton setEntries = new JButton("set entries");
    
    
    
    /**
     * option-dialog and its componens
     */
    public static JDialog optionDialog = new JDialog(mFrame);
    /**
     * the ok-button of the option dialog
     */
    public static JButton optionOK = new JButton("Ok");
    /**
     * the cancel-button of the option dialog
     */
    public static JButton optionCancel = new JButton("Cancel");
    /**
     * the combobox where the number of (randomized) monoms
     * - or the complete powerset - are choosen
     */
    public static JComboBox comboNumber = new JComboBox();
    /**
     * the model for the randomized combobox
     */
    public static ComboBoxModel comboModel = comboNumber.getModel();

    
    /**
     * Add-Dialog and its components
     */
    public static JDialog addDialog = new JDialog(mFrame);
    /**
     * the ok-button of the Add-Dialog
     */
    public static JButton addOK = new JButton("Ok");
    /**
     * the cancel-button of the Add-Dialog
     */    
    public static JButton addCancel = new JButton("Cancel");
    /**
     * the combobox the Add-Dialog
     */    
    public static JComboBox addCombo = new JComboBox();
    /**
     * the combobox-model the Add-Dialog
     */
    public static ComboBoxModel addModel = comboNumber.getModel();
    
    /**
     * this method initializes and organizes the complete menu
     * in the menubar of the main frame.
     */
    protected static void initMenu() {
	// construct fileMenu
	fileMenu.add(open);
	fileMenu.add(save);
	fileMenu.add(options);
	fileMenu.add(exit);
	menuBar.add(fileMenu);
	
	// construct listMenu
	listMenu.add(run);
	listMenu.add(options);
	listMenu.add(clearList);
	listMenu.add(sAdd);
	menuBar.add(listMenu);
	
	// add the menubar tot the main frame
	mFrame.setJMenuBar(menuBar);
    }
    

    /**
     * initializes the monom-list and its model 
     * and adds a scroller to the list
     */
    protected static void initList() {
	list = new JList(listModel);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setLayoutOrientation(JList.VERTICAL);
	scroller = new JScrollPane(list);
	scroller.setPreferredSize(new Dimension(300,80));
    }
    
    /**
     * Turns the current checkboxes into a String
     * @return this String which can be added to the monom-list
     */
    public static String ChecksToString() {
	StringBuffer sb = new StringBuffer();
	for(int i = 1; i < Constraints.maxIndex; i++) {
	    if(boxes[i].isSelected()) 
		sb.append("1");
	    else
		sb.append("0");
	}
	
	return sb.toString();
    }
    
    /**
     * initializes the status-bar in the south of the main frame
     */
    public static void initStatus() {
	status.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	status.setPreferredSize(new Dimension(300,20));
	status.setLayout(new BorderLayout());
	status.add(statusInfo,BorderLayout.CENTER);
	statusInfo.setText("Program started");
    }
    
    /**
     * Initializes the option-dialog where we can choose how long
     * our monoms should be. The number of entries is between 2 and 10.
     */
    public static void initOptionDialog() {
	String[] numbers = {"2","3","4","5","6","7","8","9","10"};
	optionDialog.setTitle("Change number of entries");
	optionDialog.setSize(new Dimension(220,90));
	comboNumber = new JComboBox(numbers);;
	optionDialog.setLayout(new BorderLayout());
	optionDialog.add(comboNumber,BorderLayout.CENTER);
	JPanel south = new JPanel(new FlowLayout());
	south.add(optionCancel);
	south.add(optionOK);
	optionDialog.add(south,BorderLayout.SOUTH);
	
    
    }
    
    /**
     * Initializes the Special-Add-Dialog.
     * The number of the randomized monoms is between
     * 5,10,20,100,...,5000 and the complete powerset
     */
    public static void initSpecialAddDialog() {
	String[] numbers = {"5","10","20","50","100","200","500",
			    "1000","2000","5000","complete powerset",};
	addCombo = new JComboBox(numbers);
	addDialog.setTitle("Add randomized monoms (or complete Poweset)");
	addDialog.setSize(new Dimension(220,90));
	addDialog.setLayout(new BorderLayout());
	addDialog.add(addCombo, BorderLayout.CENTER);
	JPanel south = new JPanel(new FlowLayout());
	south.add(addCancel);
	south.add(addOK);
	addDialog.add(south,BorderLayout.SOUTH);
    }
    
    /**
     * Important: the "Add"-Listener for a monom doesnt add
     * monoms which are already in the list.<br>
     * Hence a created monom is only added iff its a new one.<br>
     * Attention: This also has to be true for the monom-iteration at
     * the end of this method - see (*)
     * 
     * @param str describes the number of the randomized monoms that 
     * <b>tried to be</b> added.<br>
     * Usually not 10 monoms are added iff str=="10" but 10
     * randomized monoms are created and tried to add to the
     * monom-list. If, for example, the monom-list already contains
     * 3 monoms of these 10 monoms ONLY THE 7 MONOMS NOT ALREADY IN
     * THE monom-list ARE ADDED. 
     */
    public static void addSpecialAddNumber(String str) {
	int k = 0;
	if (str.equals("5")) 
	    k=5;
	if (str.equals("10")) 
	    k=10;
	if (str.equals("20")) 
	    k=20;
	if (str.equals("50")) 
	    k=50;
	if (str.equals("100")) 
	    k=100;
	if (str.equals("200")) 
	    k=200;
	if (str.equals("500")) 
	    k=500;
	if (str.equals("1000")) 
	    k=1000;
	if (str.equals("2000")) 
	    k=2000;
	if (str.equals("5000")) 
	    k=5000;
	
	MonomList ml = new MonomList();
	
	if(k != 0) {
	    ml = TestingFactory.createBinaryRandomizedMonomList(k);
	    Visual.statusInfo.setText(k+" randomized monoms added.");
	}

	if(str.equals("complete powerset")) {
	    ml = TestingFactory.constructThePowerSet();
	    Visual.statusInfo.setText("Complete Powerset of monoms of length "+
		    (Constraints.maxIndex-1)+" added.");
	}
	
	// add the Monoms to the ComboBox
	// (*) dont add already existing monoms because of the loop
	// and the checks for the boolean variable [alreadyExists]!
	Monom monom;
	Iterator<Monom> it;
	String string = "";
	boolean alreadyExists = false;
	for(it = ml.getIterator(); it.hasNext(); ) {
	    alreadyExists = false;
	    monom = it.next();
	    string = monom.toString();
	    // ToDo: pack this alreadyExists-check() into a
	    // own method to use it twice: once here and once
	    // in the AddListener-method.
	    for(int i = 0; i < Visual.listModel.getSize(); i++) {
		if(Visual.listModel.get(i).equals(string)) {
		    alreadyExists = true;
		    k = i;
		}
	    }
	    if(!alreadyExists) {
		Visual.listModel.addElement(monom.toString());
		System.out.println(monom.toString()+" Monom added by special.");
	    }
	    else {
		System.out.println("Monom "+monom.toString()+" already exists.");
	    }
	}
    }
    
    /**
     * initializes all components:
     * first the actionlisteners, then the dialogs, the statusbar,
     * the list and the complete menu.<br>
     * The checkboes are initialized dependend on maxIndex 
     * {@see enable()}-method and the main-frame is constructed 
     */
    public static void init() {
	addListeners();
	initSpecialAddDialog();
	initOptionDialog();
	initStatus();
	initList();
	initMenu();
	//initializes the Checkboxes
	//Attention: we use only boxes[1] to boxes[10]
	for(int i = 0; i < 11; i++) {
	    boxes[i] = new JCheckBox("x"+i);
	}
	enable(Constraints.maxIndex-1);
	mFrame.add(createMainPanel());
    }
    
    /**
     * Enables the Checkboxes for the monoms entries
     * and also changes the maxIndex to its right corresponding value.
     * @param k number of enabled checkboxes
     */
    public static void enable(int k) {
	//Disable all entries
	for(int i = 1; i < 11; i++) {
	    boxes[i].setSelected(false);
	    boxes[i].setEnabled(false);
	}
	
	//ensure a valid value; iff it isnt valid set it to 10 hence index
	// is 11.
	if( (k >=10) || (k < 2) ) {
	    k = 10;
	}
	int index = k+1;
	
	//Enable selected entries
	for(int i = 1; i < index; i++) {
	    boxes[i].setEnabled(true);
	}
	
	//Change also maxIndex
	Constraints.maxIndex = index;
    }
    
    /**
     * Adds the actionlisteners of the gui package to its corresponding
     * buttons and menu entries.
     */
    protected static void addListeners() {
	// add actionlisteners to the buttons in the south
	// of the main frame
	add.addActionListener(new AddListener());
	remove.addActionListener(new RemoveListener());
	clearEntries.addActionListener(new ClearEntriesListener());
	setEntries.addActionListener(new SetEntriesListener());
	invert.addActionListener(new InvertListener());
	
	// add actionlisteners for the list menu
	run.addActionListener(new RunListener());
	clearList.addActionListener(new ClearListListener());
	options.addActionListener(new OptionListener());
	sAdd.addActionListener(new AddSListener());
	
	// add actionlisteners for the option-dialog
	optionOK.addActionListener(new OptionOkListener());
	optionCancel.addActionListener(new OptionCancelListener());
	
	// add actionlisteners for the specialAdd-dialog
	addOK.addActionListener(new SpecialAddOKListener());
	addCancel.addActionListener(new SpecialAddCancelListener());
	
	// add actionlisteners to the Filemenu
	save.addActionListener(new SaveListener());
	open.addActionListener(new LoadListener());
    }
    
    /**
     * Attention: run init() first to be sure that the boxes
     * are initialized!
     * @return the panel with the checkboxes
     */
    public static JPanel createCheckPanel() {
	JPanel panel = new JPanel(new GridLayout());
	for(int i = 1; i < 11; i++) {
	    panel.add(boxes[i]);
	}
	return panel;
    }
    
    /**
     * 
     * @return the panel with the monmom-list
     */
    public static JPanel createListPanel() {
	JPanel panel = new JPanel();
	panel.add(scroller);
	return panel;
    }
    
    /**
     * 
     * @return the button-panel in the south
     */
    public static JPanel createButtonPanel() {
	JPanel panel = new JPanel(new FlowLayout());
	panel.add(add);
	panel.add(remove);
	panel.add(invert);
	panel.add(clearEntries);
	panel.add(setEntries);
	return panel;
    }
    
    /**
     *  
     * @return the innerPanel without statusbar
     */
    public static JPanel createInnerPanel() {
	createListPanel();
	createCheckPanel();
	createButtonPanel();
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(createListPanel(),BorderLayout.NORTH);
	panel.add(createCheckPanel(),BorderLayout.CENTER);
	panel.add(createButtonPanel(),BorderLayout.SOUTH);
	return panel;
    }
    
    /**
     * 
     * @return the mainPanel including the status panel
     */
    public static JPanel createMainPanel() {
	JPanel panel = new JPanel(new BorderLayout());
	panel.add(createInnerPanel(),BorderLayout.CENTER);
	panel.add(status,BorderLayout.SOUTH);
	return panel;
    }
    
    /**
     * 
     * @return an string-array which consists of the strings inside
     * the monom-list
     */
    protected static String[] getList() {
	int k = Visual.listModel.getSize();
	String mList[] = new String[k];
	for(int i = 0; i < k; i++) {
	    mList[i] = Visual.listModel.getElementAt(i).toString();
	}
	return mList;
    }

    /**
     * 
     * @return a MonomList consisting of all entries/monoms
     * of the monom-list in the north of the main frame
     */
    public static MonomList getMonomList() {
	    
	MonomList monomList = new MonomList();
	String inputList[] = getList();
	for(int i = 0; i < inputList.length; i++) {
	    monomList.add(getMonom(inputList[i]));
	}
	return monomList;
    }

    /**
     * 
     * @param s input string
     * @return a monom created from the input string;<br>
     * null iff the input string hasnt the correct length.
     */
    protected static Monom getMonom(String s) {
	Monom m = new Monom();
	int k = 0;
	char c = ' ';
	if(s.length() == Constraints.maxIndex-1) {
	    for(int i = 0; i< Constraints.maxIndex -1; i++) {
		c = s.charAt(i);
		if(c == '1')
		    m.setIntMonome(i+1, 1);
		if(c == '0')
		    m.setIntMonome(i+1, 0);
	    }
	    
	    return m;
	}
	else
	    return null;
    }
    
}
