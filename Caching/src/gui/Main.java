package gui;

import java.util.ArrayList;

import javax.swing.JFrame;

import algorithms.Fc;
import algorithms.Lru;
import algorithms.Marking;
import algorithms.Random;
import communicate.ByHand;
import communicate.StaticHolder;
import datastructures.CountPage;
import datastructures.MarkedPage;
import datastructures.Page;
import datastructures.TimePage;

public class Main {

	protected static ArrayList<CountPage> countSeq = new ArrayList<CountPage>();
	protected static ArrayList<TimePage> timeSeq = new ArrayList<TimePage>();
	protected static ArrayList<MarkedPage> markSeq = new ArrayList<MarkedPage>();
	protected static ArrayList<Page> randSeq = new ArrayList<Page>();
	
	public static void initSequences(String alpha) {
		int length = alpha.length();
		countSeq = new ArrayList<CountPage>(length);
		timeSeq = new ArrayList<TimePage>(length);
		markSeq = new ArrayList<MarkedPage>(length);
		randSeq = new ArrayList<Page>(length);
		for(int j = 0; j < alpha.length(); j++) {
			char c = alpha.charAt(j);
			countSeq.add(j, new CountPage(c));
			timeSeq.add(j, new TimePage(c));
			markSeq.add(j, new MarkedPage(c));
			randSeq.add(j, new Page(c));
		}
	}	
	
	public static void main(String[] args) {
		StaticHolder.mainWindow = new MainWindow();
		StaticHolder.mainWindow.setVisible(true);
		StaticHolder.algortihm.runAlgorithm();
		initSequences(StaticHolder.input);
		
		Random rand = new Random(randSeq);
		Fc fc = new Fc<CountPage>(countSeq);		
		Lru lru = new Lru<TimePage>(timeSeq);
		Marking mark = new Marking<MarkedPage>(markSeq);
		
		rand.runAlgorithm();
		lru.runAlgorithm();
		mark.runAlgorithm();
		fc.runAlgorithm();
		
		System.out.println("***");
		System.out.println("sequence length: "+StaticHolder.input.length());
		System.out.println("page faults(random): "+ rand.getPagefaults());
		System.out.println("page faults(lru): "+ lru.getPagefaults());
		System.out.println("page faults(marking): "+ mark.getPagefaults());
		System.out.println("page faults(fc): "+ fc.getPagefaults());
		System.out.println("page faults(You): "+ StaticHolder.algortihm.getPagefaults());

		

	}
	

}
