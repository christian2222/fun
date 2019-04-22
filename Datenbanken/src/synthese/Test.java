package synthese;

public class Test {

	public static void main(String[] args) {
		FA one = new FA("Nummer Ort Haus","Nummer");
		FA two = new FA("PLZ","Stadt Land");
		FA three = new FA("A B","C D");
		System.out.println(one.isTrivial());
		System.out.println(one);
		FaSet set = new FaSet();
		set.add(one);
		set.add(two);
		set.add(three);
		System.out.println(two.isTrivial());
		set.list();
		
		FaSet mySet= new FaSet();
		mySet.add(new FA("A B","E"));
		mySet.add(new FA("B E","I"));
		mySet.add(new FA("E","G"));
		mySet.add(new FA("G I","H"));
		mySet.add(new FA("A B","F C"));
		boolean b = mySet.xPlus(FA.parse("A B"), FA.parse("G H"));
		System.out.println(b);
		System.out.println(mySet.toString());
		System.out.println(mySet.calcSchluessel(FA.parse("I H G F E D C B A")));
		System.out.println(FA.printAllSubsets(new FA("A B C D","E F G H").getLeftSide()));
		
		System.out.println("Leftreduction routine");
		FaSet secondSet = new FaSet();
		FA reduct = new FA("A B C","D");
		secondSet.add(new FA ("A","B"));
		secondSet.add(new FA ("B C","A"));
		secondSet.add(new FA ("C","A"));
		secondSet.add(new FA ("A","C"));
		//secondSet.add(new FA ("D","A"));
		//secondSet.add(new FA ("A","D"));
		secondSet.add(reduct);
		System.out.println(secondSet);
		secondSet.leftReductAll();
		System.out.println(secondSet);
		
		System.out.println("Rightreduction routine");
		FaSet right = new FaSet();
		right.add(new FA("A","B"));
		right.add(new FA("B","A"));
		right.add(new FA("B","C"));
		right.add(new FA("A","C"));
		right.add(new FA("C","A"));
		System.out.println(right);
		right.rightReductAll();
		System.out.println(right);
		right.deleteAllTrivials();
		System.out.println(right);
		
		System.out.println("Unification");
		FaSet unify = new FaSet();
		unify.add(new FA("A", "B"));
		unify.add(new FA("A", "C"));
		unify.add(new FA("A", "D"));
		unify.add(new FA("A", "E"));
		unify.add(new FA("A", "F"));
		unify.add(new FA("A", "G"));
		unify.add(new FA("A", "H"));
		unify.add(new FA("A", "I"));
		unify.add(new FA("A", "J"));
		unify.add(new FA("A", "K"));
		unify.add(new FA("B", "L"));
		unify.add(new FA("B", "M"));
		unify.add(new FA("B", "N"));
		unify.add(new FA("B", "O"));
		unify.add(new FA("B", "P"));
		
		System.out.println(unify);
		unify.unifyAllFas();
		System.out.println(unify);
		unify.testElements();
		System.out.println(new FA("A","B").compareTo(new FA("A","B")));
		
		FaSet trivials = new FaSet();
		trivials.add(new FA("Land Stadt","Stadt"));
		trivials.add(new FA("E G","G"));
		trivials.add(new FA("Not","Trivial"));
		trivials.add(new FA("A T","T"));
		trivials.add(new FA("B X","B"));
		trivials.add(new FA("B X T","T X"));
		trivials.add(new FA("A V",""));
		trivials.add(new FA("",""));
		System.out.println(trivials);
		trivials.removeAllTrivials();
		System.out.println(trivials);
		
		FaSet dreiNFanalyse = new FaSet();
		dreiNFanalyse.add(new FA("A", "B"));
		dreiNFanalyse.add(new FA("B", "C"));
		dreiNFanalyse.add(new FA("A", "C"));
		dreiNFanalyse.dreiNFsynthese(FA.parse("A B C D"));
		System.out.println(dreiNFanalyse.isAllIn3NF(FA.parse("A B C D")));
		
		FaSet zweiteAnalyse = new FaSet();
		zweiteAnalyse.add(new FA("Lcode","Hstadt"));
		zweiteAnalyse.add(new FA("Hstadt","Lcode"));
		zweiteAnalyse.add(new FA("Kname Lcode","Prozent"));
		zweiteAnalyse.dreiNFsynthese(FA.parse("Hstadt Lcode Kname Prozent"));
		System.out.println(zweiteAnalyse.isAllIn3NF(FA.parse("Hstadt Lcode Kname Prozent")));

	}
}
