/*
 * Created on 10.10.2004
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestFrame extends JFrame implements ActionListener
{
	
    public JList list;
    public DefaultListModel listModel;
	public JScrollPane scroller;
	private int counter = 0;
	
	
	public TestFrame()
	{
	    super("Testing");
	    setSize(400,400);
	    setDescription();
	    listModel = new DefaultListModel();
	    list = new JList(listModel);
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    JPanel panel = new JPanel(new BorderLayout());
	 	panel.setLayout(new BorderLayout());
	 	JScrollPane listScroll = new JScrollPane(list);
	    panel.add(listScroll,BorderLayout.CENTER);
	    JButton adder = new JButton("add");
	    adder.addActionListener(this);
	    panel.add(adder,BorderLayout.SOUTH);
	    init();
	    listModel.addElement("hello");
	    listModel.addElement("world");
	    for(int i = 0; i< 100; i++)
	        listModel.addElement(""+i);
	    listModel.removeElementAt(15);
	    this.getContentPane().add(panel);
	    
	   	show();
	    
	}
	
	public void actionPerformed(ActionEvent ae)
	{
	    counter++;
	    listModel.add(0,"Element "+counter);
	}
	
	private void setDescription()
	{
	}
	
	private void init()
	{
	    
	}
	
	public static void main(String[] args)
	{
	    TestFrame tf = new TestFrame();
	    tf.show();
	}

}
