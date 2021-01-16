package rsa;

public class Translator {

	protected int base = 30;
	
	public long convert(String message) {
		message = message.toLowerCase();
		long translation = 0;
		for(int i = message.length()-1; i >=0; i--) {
			translation += this.translateChar(message.charAt(i)) * Math.pow(this.base, i); 
		}
		
		return translation;
	}
	
	public String deConvert(long encoded) {
		String decoded = "";
		long toDecode = encoded;
		long div = 0;
		long rest = 0;
		while(toDecode > 0) {
			rest = toDecode % this.base;
			toDecode = toDecode /this.base;
			decoded = decoded + this.translateInt((int) rest);
		}
		return decoded;
	}
	
	public char translateInt(int i) {
		switch(i) {
			case 1: return ' ';
			case 2: return ',';
			case 3: return 'a';
			case 4: return 'b';
			case 5: return 'c';
			case 6: return 'd';
			case 7: return 'e';
			case 8: return 'f';
			case 9: return 'g';
			case 10: return 'h';
			case 11: return 'i';
			case 12: return 'j';
			case 13: return 'k';
			case 14: return 'l';
			case 15: return 'm';
			case 16: return 'n';
			case 17: return 'o';
			case 18: return 'p';
			case 19: return 'q';
			case 20: return 'r';
			case 21: return 's';
			case 22: return 't';
			case 23: return 'u';
			case 24: return 'v';
			case 25: return 'w';
			case 26: return 'x';
			case 27: return 'y';
			case 28: return 'z';
		
			default: return 'X';
		}
	}
	
	public int translateChar(char c) {
		switch(c) {
			case ' ': return 1;
			case ',': return 2;
			case 'a': return 3;
			case 'b': return 4;
			case 'c': return 5;
			case 'd': return 6;
			case 'e': return 7;
			case 'f': return 8;
			case 'g': return 9;
			case 'h': return 10;
			case 'i': return 11;
			case 'j': return 12;
			case 'k': return 13;
			case 'l': return 14;
			case 'm': return 15;
			case 'n': return 16;
			case 'o': return 17;
			case 'p': return 18;
			case 'q': return 19;
			case 'r': return 20;
			case 's': return 21;
			case 't': return 22;
			case 'u': return 23;
			case 'v': return 24;
			case 'w': return 25;
			case 'x': return 26;
			case 'y': return 27;
			case 'z': return 28;
		
			default: return 0;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(2/3);
		Translator t = new Translator();
		long l = t.convert("Hello Bert");
		System.out.println(l);
		System.out.println(t.deConvert(l));
	}
}
