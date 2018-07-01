package combine;

import javax.swing.*;

import data.*;

import java.awt.*;
import java.awt.event.*;

public class TestRoom extends JFrame implements MouseListener {


	// public TimeTimer tt; doesnt work yet
	
	public TestRoom() {
		super();
		// TODO Auto-generated constructor stub
		//c.paint(this.getGraphics());
		this.addMouseListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		/*this.setLayout(new BorderLayout());
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		JLabel timeLabel = new JLabel("xxxxxx"); 
		north.add(new JButton("Hello World"),BorderLayout.WEST);
		north.add(new JButton("CENTER"),BorderLayout.CENTER);		
		north.add(timeLabel,BorderLayout.EAST);
		//tt = new TimeTimer(0,5,50,50,timeLabel);
		this.add(north, BorderLayout.NORTH);
		*/


	}
	
	
	public void paint(Graphics g) {

/*
		Literal l1 = new Literal(new Variable(1,Constants.isTrue),false);
		Literal l2 = new Literal(new Variable(1,Constants.isTrue),true);
		Literal l3 = new Literal(new Variable(1,Constants.isTrue),false);
		Room room = new Room(80,80,5);
		LiteralLight ll1 = new LiteralLight(l1, 85, 105);
		LiteralLight ll2 = new LiteralLight(l2, 130, 105);
		LiteralLight ll3 = new LiteralLight(l3, 175, 105);
		room.setLiteralLight(0, ll1);
		room.setLiteralLight(1, ll2);
		room.setLiteralLight(2, ll3);
		room.paint(g);
		
		room = new Room(240,80,2);
		room.paint(g);
		*/
		Room[] rooms = new Room[30];
		DisjClause3[] clauses = new DisjClause3[30];
		Literal l1 = new Literal(new Variable(0, Constants.isFalse), Constants.notNegated);
		Literal l2 = new Literal(new Variable(1, Constants.isTrue), Constants.notNegated);
		Literal l3 = new Literal(new Variable(2, Constants.isFalse), Constants.notNegated);
		for(int j = 0; j < 5; j++) {
			for(int i= 0; i < 6; i++) {
				clauses[5*i+j] = new DisjClause3(l1, l2, l3);
				rooms[i*5+j] = new Room(clauses[i*5+j],40+160*i,140+120*j,i*5+j);
			}
		}

		

		for(int j = 0; j < 5; j++) {
			for(int i= 0; i < 6; i++) {
				rooms[i*5+j].paint(g);
			}
		}		

	}
	

	public static void main(String[] args) {
		TestRoom tr = new TestRoom();
		tr.setSize(1024,768);
		tr.setTitle("Graphics Test");
		tr.setVisible(true);


	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
