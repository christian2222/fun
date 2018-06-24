import java.util.ArrayList;

import algorithms.*;
import datastructures.*;

public class Test {
	
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
		//Random random = new Random();
		//random.runAlgorithm();

		initSequences("abcdaefghijklmnobadbcdbabcdb");
		
		Random<Page> rand = new Random<Page>(randSeq);
		
		Fc<CountPage> fc = new Fc<CountPage>(countSeq);
		
		Lru<TimePage> lru = new Lru<TimePage>(timeSeq);
		
		rand.runAlgorithm();
		lru.runAlgorithm();
	
		Marking<MarkedPage> mark = new Marking<MarkedPage>(markSeq);
		mark.runAlgorithm();
		


		fc.runAlgorithm();
		
	}
}
