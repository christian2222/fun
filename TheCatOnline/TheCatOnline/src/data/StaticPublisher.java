package data;

import java.util.Random;

import maths.Maths;

/**
 * Diese Klasse stellt verschiedene Daten oeffentlich zur Verfuegung, um darauf arbeiten zu können
 * Damit ist die Datenreferenzeirung während des Programms gewährleistet
 * @author cm
 *
 */

/*
 * ToDo: refernziere alle Anfragen an Variable xi nach variables[i]
 */
public class StaticPublisher {


	
	/*
	public static final boolean[] varNumbersUsed = 
		{true,true,true,true,true,
		true,true,true,true,true,
		false,false,false,false,false,false
		};//= new boolean[variableNumber];
		*/
	

	/**
	 * Initialisierungsmethoden werden oftmals zufaellig entschieden
	 */
	public static Random random = new Random();

	/**
	 * Standardmethode, um alle Variablen zu initialisieren.
	 */
	public static void initialize() {
		//initClauselRecursive();
		//randomInit();
	}
	
	
	 // benutze nach dieser Initialisierung von jedem Teil des Programs aus nur die Variablen hier
	
	
	/**
	 * erzeugt zufaellige Werte fuer Variablen, Literale und Klauseln
	 
	public static void randomInit() {
		// Variablen
		int truefalse = 0;
		for(int i = 0; i < variableNumber; i++) {
			truefalse = randomIntBoolean();
			if( (truefalse == Constants.isTrue) || (truefalse == Constants.isFalse) ) {
				System.out.println("Initialize Variable Number "+i);
				variables[i] = new Variable(i,truefalse);
			}
		}
		// Literale
		for(int i = 0; i < literalNumber; i++) {
			int j = 0;
			j = Maths.betweenZeroAndN(j, variableNumber);
			literals[i] = new Literal(variables[j],randomBoolean());
		}
		// Klauseln
		for(int i = 0; i < dClauseNumber; i++) {
			int j1,j2,j3;
			j1 = j2 = j3 = 0;
			
			j1 = Maths.betweenZeroAndN(j1, literalNumber);
			j2 = Maths.betweenZeroAndN(j2, literalNumber);
			j3 = Maths.betweenZeroAndN(j3, literalNumber);
			
			Literal l1 = literals[j1];
			Literal l2 = literals[j2];
			Literal l3 = literals[j3];
			
			clauses[i] = new DisjClause3(l1, l2, l3);
			testClauses[i] = new DisjClause3(l1, l2, l3);
		}
		
	}
	*/

	

	
	/**
	 * initialisiert nicht alle Literale oder Klauseln
	 * 
	 */
	/**
	 * public static void initClauselRecursive() {
	 
		for(int i = 0; i < variableNumber; i++) {
				variables[i] = new Variable(i,0);
				literals[i] = new Literal(variables[i],false);
				clauses[i] = new DisjClause3(literals[i], literals[i], literals[i]);
			}

	} 
	* 				clauses[i] = new DisjClause3(literals[i], literals[i], literals[i]);
	*			benutzt keine 3 Variablen!!! (ToDo)
	*/
	
	
	public static void main(String[] args) {
		//randomInit();
		//System.out.println(StaticPublisher.toString());
	}
}
