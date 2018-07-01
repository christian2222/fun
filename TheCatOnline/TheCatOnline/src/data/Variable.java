package data;

import javax.swing.SpringLayout.Constraints;

public class Variable {
	/**
	 * number ist die Nummer der Variablen. 
	 */
	int number;
	/**
	 * der boolsche Wahrheitswert wird hier durch einen Integer ersetzt, um für später mehr Möglichkeiten zu haben
	 *
	 */
	int value;
	
	/**
	 * Standard-Konstruktor
	 * @param number - 
	 * @param value
	 */
	public Variable(int number, int value) {
		super();
		this.number = number;
		this.value = value;
	}
	
	/**
	 * einige Getter und Setter Methoden (Kapselung)
	 * setNumber() ist verboten, damit die Variable die gleiche bleibt.
	 */
	public int getNumber() {
		return number;
	}


	public int getValue() {
		return this.value;
	}
		
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * inverteie den Vriablenwert
	 */
	
	public void invert() {
		if(this.value == Constants.isTrue) { 
			this.value = Constants.isFalse; 
		} else {
			if(this.value == Constants.isFalse) {
				this.value = Constants.isTrue; 
			}
		}
	}
	
	/**
	 * Einige Methoden, um boolsche Wahrheitswerte zurück zu geben
	 * @return boolscher Wert
	 */
	public boolean isTrue() {
		return (this.value == Constants.isTrue);
	}
	
	public boolean isFalse() {
		return (this.value == Constants.isFalse);
	}
	
	public boolean isDefined() {
		return (this.value != Constants.isUndefined);
	}
	
	/**
	 * Methoden, um den Wert der Variablen zu setzen
	 */
	public void setTrue() {
		this.value = Constants.isTrue;
	}
	
	public void setFalse() {
		this.value = Constants.isFalse;
	}
	
	/**
	 * nicht wirkich notwendig; vielleicht spaeter einmal... ?
	 * @return wahr, wenn die Variable undefiniert ist
	 */
	public boolean isUndefined() {
	 
		return (this.value == Constants.isUndefined);
	}
	
	
	/**
	 * Repräsentiert die Variable als String.
	 */
	public String toString() {
		return ("x"+this.number);
	}
	
	

	
}
