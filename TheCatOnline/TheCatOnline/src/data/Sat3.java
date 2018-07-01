package data;

import maths.Maths;

/**
 * deprecated
 * @author xy
 *
 */
public class Sat3 {

	// ToDo maximale und tatsächliche Werte zu unterscheiden
	public int variableNumber = 16;
	public int literalNumber = 90;
	public int disjClauseNumber = 30;

	/**
	 * veroeffentliche Variablen Alle Variablen des Programms werden hier
	 * hinterlegt und abgespeichert.
	 * 
	 */
	public Variable[] variables;
	public Literal[] literals;
	public DisjClause3[] clauses;

	/** eigene Instanz der Klasse
	public Variable[] testVariables;
	public Literal[] testLiterals;
	public DisjClause3[] testClauses;
	*/
	/**
	 * zweiter Konstruktor nimmt fest vorgegebene Werte
	 * 
	 * @param variableNumber
	 * @param literalNumber
	 * @param disjClauseNumber
	 */
	public Sat3(int variableNumber, int literalNumber, int disjClauseNumber) {
		// Nummern-Zuweisung
		this.variableNumber = variableNumber;
		this.literalNumber = literalNumber;
		this.disjClauseNumber = disjClauseNumber;
		// Initialisierung von arrays
		this.variables = new Variable[this.variableNumber];
		this.literals = new Literal[this.literalNumber];
		this.clauses = new DisjClause3[this.disjClauseNumber];

		this.randomInit();

	}

	/**
	 * toDo: Sat3() und mit Parameter; Aehnlichkeit
	 */
	public Sat3() {
		// Initialisierung durch Konstanten
		this.variableNumber = Constants.maxVariableNumber;
		this.literalNumber = Constants.maxLiteralNumber;
		this.disjClauseNumber = Constants.maxDisjClauseNumber;
		// Initialisierung von arrays
		this.variables = new Variable[this.variableNumber];
		this.literals = new Literal[this.literalNumber];
		this.clauses = new DisjClause3[this.disjClauseNumber];

		this.randomInit();
	}

	/**
	 * Zufaellige Konstruktion einer 3Sat-Instanz ausgerichtet an den Konstanten
	 * [variableNumber, literalNumber, ]
	 */
	public void randomInit() {


		//lokale Variablen zur Konstruktion
		//Variable vars[] = new Variable[this.variableNumber];
		//Literal lits[] = new Literal[this.literalNumber];
		//DisjClause3 disj[] = new DisjClause3[this.disjClauseNumber];

		// Initialisiere Variablen mit zufaelligem Wahrheitswert
		for (int i = 0; i < this.variableNumber; i++) {

			this.variables[i] = new Variable(i, Maths.randomIntBoolean());
		}

		// Literale mit zufaelliger Negation
		for (int i = 0; i < this.literalNumber; i++) {
			int varNumb = Maths.randomZeroAndN(this.variableNumber);
			boolean bolNumb = Maths.randomBoolean();
			// i = i % this.literalNumber; schon durch for-Schleife garantiert
			this.literals[i] = new Literal(this.variables[varNumb], bolNumb);
		}

		// Konstruktion zufaelliger disjunktiver Klauseln
		for (int i = 0; i < this.disjClauseNumber; i++) {
			int j = Maths.randomZeroAndN(literalNumber);
			int k = Maths.randomZeroAndN(literalNumber);
			int l = Maths.randomZeroAndN(literalNumber);
			// this.clauses[i] = new DisjClause3(this.literals[0],
			// this.literals[0], this.literals[0]);
			this.clauses[i] = new DisjClause3(literals[j], literals[k],
					literals[l]);
		}
		
			
	}



	/**
	 * 
	 * @return Anzahl der Loesungen
	 */
	public int durchLaufen() {

		int lsg = 0;
		long l = Maths.power(2, this.variableNumber);
		String s = "";
		char c = ' ';
		
		for(long i = 0; i < l; i++) {
			s = Maths.toBaseString(i, 2);
			// Konstruktion fuehrender Nullen
			while(s.length() < this.variableNumber) {
				s = "0" + s;
			}
			// System.out.println(s); //Kontrolle
			// zaehlen von hinten nach vorne ~> j von gross nach klein zaehlen - vgl. Binaerzaehler
			// String s hat andere Reihenfolge bzgl. binaeres Zaehlen
			// Setze Variablen in Abhaengigkeit des "richtig" durchlaufenden Strings s
			
			for(int j = s.length() - 1; j >= 0; j--) {
				c = s.charAt(j);
				if(c == '1') {
					this.variables[j].setTrue();
				} else {
					this.variables[j].setFalse();
				}
				
			}
			// System.out.println(this.toString());
			if(this.isTrue()) {
				lsg++;
				System.out.println(s);
				System.out.println(this);
			}
			
			
			
		}
		
	
		
		return lsg;
	}
	
	public boolean isTrue() {
		boolean b = true;
		for(int i = 0; i < this.disjClauseNumber; i++) {
			b = b && this.clauses[i].isTrue();
		}
		return b;
	}

	public void test() {
		long t = 2;
		for (int i = 0; i < 5; i++) {
			t = 2 * t;
		}
		System.out.println(t);
	}

	public static void main(String[] args) {
		Sat3 s = new Sat3();
		// s.test();
		System.out.println(s.durchLaufen());
		System.out.println("done!");
	}

	public String toString() {
		String s = "";
	//	System.out.println(clauses.length);
	//	System.out.println(clauses[0].toString());
		for (int i = 0; i < clauses.length; i++) {
			s += clauses[i].toString();
		}
		return s;
	}

}
