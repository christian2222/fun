package rsa;

public class Test {

	public static void main(String[] args) {
		String output = "";
		int runs = 10;
		int fails = 0;
		for(int i = 0; i < runs; i++) {
			//System.out.println("*****");
			output = "*****\n";
			output += "Run ("+i+")\n";
			Translator t = new Translator();
			RsaPerson bob = new RsaPerson(false);
			RsaPerson alice = new RsaPerson(true);
			// bob notices alices public key
			bob.setForeignKeyPair(alice.getPublicKeyPair());
			output += alice.checkKeys()+"\n";
			//System.out.println(alice.getPublicKey());
			String message = "Hi Bob";
			long text = t.convert(message);
			alice.encode(text);
			long coded = alice.send();
			output += "["+coded+"]\n";
			bob.receive(coded);
			output += "["+bob.decode()+"]\n";
			output += "done.\n";
			output += alice.getLogger()+"\n";
			output += bob.getLogger();
			output += "Alice:";
			System.out.println(output);
			alice.showAll();
			if(!t.deConvert(bob.decode()).equalsIgnoreCase(message)) {
					//System.out.println(output);
					//alice.showAll();
					System.out.println("PROBLEM!");
					fails++;
 			} else
				System.out.println("SUCCESS!");
			//if(bob.decode() != "hi") alice.showAll();
			//System.out.println("Bob:");
			//bob.showAll();
			//System.out.println("*****");
		}
		System.out.println();
		System.out.println("Sucessful runs: "+ (runs-fails));
		System.out.println("Failed runs: " + fails);
	}
}
