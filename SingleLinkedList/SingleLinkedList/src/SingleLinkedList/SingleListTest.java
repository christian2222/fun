package SingleLinkedList;

/**
 * Hier wird die SingleLinkedList mit ihren Funktionen getestet.
 * Wir können die main-methode beliebig verändern und so
 * immer wieder die Liste manipulieren.
 * 
 * Notiz: Objekte/Klassen die eine toString()-methode haben
 * rufen diese implizit auf, wenn Objekte o dieser Klassen
 * an System.out.println gesendet werden. Somit sind beide
 * folgenden Aurufe äquivalent:
 * 
 * System.out.println(sll);
 * System.out.println(sll.toString());
 * 
 * Aufgabe 1: Erkläre die Verwendung der linearen Liste in
 * der main()-methode. Schreibe nach jeder Anweisung die
 * Repräsentation der linearen Liste auf ein Blatt Papier.
 * Zur Notation siehe beigefügte Präsentation:
 * SaschaSSL
 * 
 * Aufgabe 2: Diskutiere in einer Gruppe die methoden und
 * die Wirkung von SingleLinkedList.java und SLLNode.java
 * 
 * 
 * @author C.Marquardt
 * @date 22.10.2011	
 */
public class SingleListTest {
    
    
    private static SingleLinkedList sll = new SingleLinkedList();
    
    private static String example = "something";
    
    public static void main(String[] args) {
	sll.addNode(new SLLNode("Hello"));
	sll.addNode(new SLLNode("World!"));
	sll.addNode(new SLLNode("How",null));
	sll.addNode(new SLLNode("are"));
	sll.addNode(new SLLNode("you"));
	System.out.println("start:");
	System.out.println(sll);
	sll.removeFirstDetected("Hello");
	System.out.println("<Hello> deleted");
	System.out.println(sll);
	sll.addNode(new SLLNode("dazu"));
	sll.removeFirstDetected("are");	
	sll.addNode(new SLLNode("end",null));
	System.out.println("<dazu> added; <are> deleted; " +
			"<end> added");
	System.out.println(sll);
	sll.removeFirstDetected("end");
	boolean contains = sll.contains(example);
	System.out.println("delete last element and search " +
			"for <"+ example +"> element not in " +
			"the list");
	System.out.println(sll);
	System.out.println();
	if(contains) {
	    System.out.println("<"+example +"> is in the list");
	}
	else {
	    System.out.println("<"+example +"> is not in the list");
	    
	}
	
	System.out.println();
	//sll.eraseAll();
	System.out.println();
	if(sll.isEmpty()) {
	    System.out.println("Die Liste ist leer");
	}
	else {
	    System.out.println("Die Liste ist nicht leer");
	}
	
	
    }
}
