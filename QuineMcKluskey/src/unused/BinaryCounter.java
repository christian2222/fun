package unused;

import algorithm.Constraints;
import algorithm.MyMath;


/**
 * This class is implements a binary counter. It is useful to count
 * from 0..00, 0..01, ... ... up to 1...11.<br>
 * These outputs can be used to initialize a test set of monoms 
 * @author chrissy
 * 
 * Unused: Substituted by NaryCounter!
 *
 */
public class BinaryCounter {

    	/**
    	 * the counter array
    	 */
	int counter[] = new int[Constraints.maxIndex];
	
	/**
	 * here we check wheather an overflow has appeared
	 */
	boolean overflow = false;
	
	/**
	 * this is the value array, which counts up all numbers
	 */
	int value[] = new int[Constraints.maxIndex];
	
	public int[] getArray() {
		for(int i = 1; i < Constraints.maxIndex; i++) {
			if(this.counter[i] == 1)
				this.value[i] = 1;
			else
				this.value[i] = 2;
		}
		
		return this.value;
	}
	
	
	public BinaryCounter() {
	}
	
	public void count() {
		//if(this.overflow) this.clear();
		int k = Constraints.maxIndex-1;
		while((counter[k] == 1)&&(k > -1)) {
			k--;
		}
		this.overflow = (k==-1);
		if(!this.overflow) {
			counter[k] = 1;
			for(int j=k+1; j< Constraints.maxIndex;j++) {
				this.counter[j]=0;
			}
			
		}
		else // Overflow
		    this.clear();
	}
	
	/**
	 * senceless !?
	 * @return
	 */
	public String getSetSignature() {
		String des = "";
		for(int i = 1; i < Constraints.maxIndex; i++) {
			if(this.counter[i] == 1)
				des = des+"X";
			else
				des= des + "o";
		}
		
		return des;
	}
	
	
	public String toString() {
		String s = "";
		for(int i = 1; i < Constraints.maxIndex; i++) {
			s = s+this.counter[i];
		}
		/* Umgekehrte Augabe:
				s+="\n";
		for(int i = Constraints.maxIndex-1; i >-1; i--) {
			s = s+this.counter[i];
		}
		*/
		return s;
	}
	
	public void clear() {
		for(int i = 1; i < Constraints.maxIndex; i++) {
			this.counter[i]=0;
		}		
	}
	
	public String toDescription() {
		String s = "";
		for(int i = 1; i < Constraints.maxIndex; i++) {
			if(this.counter[i] == 1)
				s = s+"x"+(i%10);
			else
				s = s+"\\lbrack -\\rbrack ";
		}
		return s;
	}

	/**
	 * won't work for a binary counter because
	 * counter[i] is only 0 or 1.
	public String toSignature() {
	    String s = "";
	    for(int i = 1; i < Constraints.maxIndex; i++) {
		switch(this.counter[i]) {
		    case 0:
		    case 1:
			s = s+"X";
		    case 2:
		    	s = s+"-";
		    
		}
	    }
	    
	    return s;
	}
	*/
	
	public static void main(String[]args) {
		BinaryCounter bc = new BinaryCounter();
		int k = (int) MyMath.intXhochY(2, Constraints.maxIndex-1);
		for(int i = 0; i < k; i++) {
			bc.count();
			System.out.print(bc);
			System.out.println(" : "+bc.toDescription());
		}
	}
}
