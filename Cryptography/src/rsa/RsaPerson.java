package rsa;


import java.util.Random;

import algorithms.*;

public class RsaPerson extends Person {

	long message = 0;
	String logger = "";
	long p = 0;
	long q = 0;
	long N = 0;
	long phiN = 0;
	long e = 0;
	long d = 0;
	boolean logGeneration = false;
	
	protected long[] foreignPublicKey = {0,0};
	
	public RsaPerson(boolean logGen) {
		this.logGeneration = logGen;
		this.p = PrimeGenerator.generatePrimeNumber();
		do {
			this.q = PrimeGenerator.generatePrimeNumber();
		} while(this.p == this.q);
		//this.q = 227;
		//this.p = 229;
		this.N = this.p * this.q;
		this.phiN = (this.p-1) * (this.q-1);
		this.e = this.phiN;
		//System.out.println(this.e);
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		eea.runAlgortihm(this.e, this.phiN);
		Random r = new Random();
		//System.out.println(eea.getGcd());
		while( eea.getGcd() != 1 || this.e == 0) {
			// try this.e in [0,this,phiN) interval
			this.e = Math.abs(r.nextLong() % this.phiN);
			//System.out.println(this.e);
			eea.runAlgortihm(this.e, this.phiN);
		}
		long[] multiplicators = eea.getMultiplikators(this.e, this.phiN);
		if(eea.nonTrivialLinearKombination(multiplicators)) {
			//[0]*this.e + [2]*this.phi = gcd = 1
			//=> [0]*this.e = 1 (mod this.phiN)
			// so [0] is multiplicative inverse of this.e modulo this.phi
			// but: this.d could be negative
			this.d = multiplicators[0];
			if(this.logGeneration) this.logger += "d: "+this.d+"\n";
			if(this.logGeneration) this.logger += "e: "+this.e+"\n";
			// this.d * this.e == 1 (this.phiN)
			while(this.d < 0) {
				this.d += this.phiN;
			}
			if(this.logGeneration) this.logger += "dPLus: "+this.d+"\n";
			// does not change the congruence, but changes the linear combination
		}
		//
		this.publicKey = this.e;
		this.privateKey = this.d;
		//System.out.println(this.publicKey);
		//System.out.println(this.privateKey);
		// TODO Auto-generated constructor stub
	}
	
	public String getLogger() {
		return this.logger;
	}
	
	public String checkKeys() {
		String checked = this.privateKey+"*"+this.publicKey+"%"+this.phiN+" = ";
		long prod = this.privateKey*this.publicKey % this.phiN;
		checked += prod;
		return checked;
	}
	
	@Override
	public long decode() {
		// TODO Auto-generated method stub
		if(this.checkForeignKeyPair(this.foreignPublicKey)) {
			long foreignE = this.foreignPublicKey[0];
			long foreignN = this.foreignPublicKey[1];
			long decoded = ExponentationModuloN.calculate(this.message, foreignE,foreignN);
			this.logger += "decoding: ";
			this.logger += this.message+"^"+foreignE+"%"+foreignN+"="+decoded+"\n";
			return decoded;
		} else {
			this.logger += "Fail in ForeignKey!\n";
			return 0;
		}
	}
	
	public void setForeignKeyPair(long[] publicKeyPair) {
		if(this.checkForeignKeyPair(publicKeyPair)) {
			this.foreignPublicKey = publicKeyPair;
		}
	}
	
	protected boolean checkForeignKeyPair(long[] publicKeyPair) {
		boolean valid = true;
		valid &= publicKeyPair.length == 2;
		valid &= (publicKeyPair[0] != 0) && (publicKeyPair[1] != 0);
		return valid;
	}

	@Override
	public void encode(long message) {
		this.message = message;
		this.logger += this.message+"\n";
		//this.message = (long)Math.pow(this.message, this.privateKey) % this.n;
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		eea.runAlgortihm(message, this.N);
		this.logger += "("+this.message+","+this.N+")="+eea.getGcd()+"\n";
		long codedMessage = ExponentationModuloN.calculate(this.message, this.privateKey, this.N);
		this.logger += "encoding: ";
		this.logger += this.message+"^"+this.privateKey+"%"+this.N+"= "+codedMessage;
		this.message = codedMessage;
		

	}
	
	public void showAll() {
		
		System.out.println("Message: "+this.message);
		System.out.println("private Key: "+this.privateKey);
		System.out.println("public Key: "+this.publicKey);
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		eea.runAlgortihm(this.privateKey, this.publicKey);
		System.out.println("gcd(pub,pri): "+eea.getGcd());
		System.out.println("N: "+this.N);
		System.out.println("p: "+this.p);
		System.out.println("q: "+this.q);
		System.out.println("phiN: "+this.phiN);
	}
	
	public long[] getPublicKeyPair() {
		long[] publicPair = {this.e, this.N};
		return publicPair;
	}

	@Override
	public long send() {
		// TODO Auto-generated method stub
		return this.message;
	}

	@Override
	public void receive(long message) {
		this.message = message;
		// TODO Auto-generated method stub

	}
	
	public long getPublicKey() {
		return this.publicKey;
	}

}
