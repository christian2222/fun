package SingleLinkedList;
/**
 * diese Klasse repr�sentiert eine SingleLinkedList.
 * 
 * @author C.Marquardt
 *
 */
public class SingleLinkedList {

    SLLNode head;
    
    public SingleLinkedList() {
	this.head = null;
    }
    public SingleLinkedList(SLLNode head) {
	this.head = head;
    }

    /**
     * f�gt eine neue Node am Ende der Liste ein
     * @param node einzuf�gende Node
     */
    public void addNode(SLLNode node) {
	
	SLLNode tmp;
	
	// wenn kein Kopf existiert legen wir einen an!
	if(this.head == null) {
	    this.head = node;
	    this.head.setNext(null);
	}
	else {
	    // lauf zum letzten listenelement
	    // direkt dahinter steht null
	    tmp = this.head;
	    while(tmp.getNext() != null) {
	    	tmp = tmp.getNext();
	    }
	    // am Ende der Schleife ist tmp.getNext() == null
	    // wir sind am Ende der linearen Liste.
	    tmp.setNext(node);
	    tmp = tmp.getNext();
	    tmp.setNext(null);
	}
    }
    
    /**
     * L�sche das erste Vorkommen von Objekt o
     * @param content zu l�schendes Objekt
     */
    public void removeFirstDetected(Object content) {
	boolean deleted = false;
	SLLNode tmp,tmpNext;
	if(this.isNotEmpty()) {
	    // mu� head entfernt werden?
	    if (this.head.getContent().equals(content) ) {
		// l�sche den Kopf (head)
		tmp = this.head;
		this.head = this.head.getNext();
		deleted = true;
	    }
	    // ist das zu enfernende Element im
	    // Rest der Liste?
	    else {
		// beginnne bei Head,
		// der nun nicht ! gel�scht wird.
		tmp = this.head;
		// f�hre einen Zeiger auf tmp.getNextNode();
		// setze und untersuche die n�chste Node
		tmpNext = tmp.getNext();
		
		// solange noch nichts gel�scht wurde und
		// tmpNext nicht das ende der liste [=null]
		// erreicht hat...
		while(tmpNext != null) {
		    // ist der content von tmpNext
		    // der zu l�schende?
		    if(tmpNext.getContent().equals(content)
			    && !deleted) {
			// entferne tmpNext aus der Liste
			// indem der Zeiger von tmp
			// auf das �bern�chste Element
			// gelegt wird
			tmp.setNext(tmpNext.getNext());
			deleted = true;
		    }
		    else {
			// bewege beide Zeiger jeweils eine
			// Position weiter vor
			tmp = tmp.getNext();
			tmpNext = tmpNext.getNext();
		    }
		    
		} // while(tmpNext != null)


	    } // else (this.head.getContent().equals(content))
	} // else (isNotEmpty)
    } // 
    
    /**
     * �berpr�ft auf ein in der Liste vorhandenes Objekt
     * @param o
     * @return
     */
    public boolean contains(Object o) {
	boolean contained = false;
	SLLNode tmp;
	if(this.isNotEmpty()) {
	    tmp = this.head;
	    
	    while(tmp != null) {
		contained = contained ||
				tmp.getContent().equals(o);
		tmp = tmp.getNext();
	    }
	}
	return contained;
    }
    
    /**
     * l�scht alle elemente der liste, indem
     * der head-Zeiger auf [null] gesetzt wird.
     * Die Nodes die vorher in der Liste waren
     * werden sp�ter vom Java-System aus dem
     * Speicher gel�scht (schlagwort: garbage collector)
     */
    public void eraseAll() {
	this.head = null;
    }
    
    /**
     * Gibt eine String-Repr�sentation der SingleLinkedList
     * zur�ck. Zu beachten ist hierbei, da� die Klasse
     * StringBuffer verwendet wird. Sie wird von Java
     * vorgeschlagen um mehrere Strings effizient zu
     * verkn�pfen (siehe 
     * Buch: Effizient/Perfomant Java programmieren)
     */
    public String toString() {
	SLLNode tmp = null;
	StringBuffer sb = new StringBuffer();
	if(this.isNotEmpty()) {
	    sb.append("[head] = ");
	    tmp = this.head;
	    sb.append(tmp.getContent().toString()+" -> ");
	    while(tmp.getNext() != null) {
		tmp = tmp.getNext();
		sb.append(tmp.getContent().toString()+" -> ");
	    } // am Ende ist tmp.getNext == null
	    sb.append("[null]");
	}
	return sb.toString();
	
    }
    
    public SLLNode getHead() {
	return this.head;
    }
    /**
     * Eine leere Liste wird dadurch charakterisiert,
     * da� der head-Zeiger = null ist.
     * @return boolean der angibt, ob die Liste leer ist
     */
    public boolean isEmpty() {
	return (this.head == null);
    }
    
    public boolean isNotEmpty() {
	return (!this.isEmpty());
    }
    

}
