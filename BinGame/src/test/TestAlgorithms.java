package test;

import data.*;
import algorithms.*;
import random.*;

public class TestAlgorithms {
    
    protected static FirstFit ff = new FirstFit();
    protected static NextFit nf = new NextFit();
    protected static BestFit bf = new BestFit();
    protected static FirstFitDecreasing ffd = new FirstFitDecreasing();
    
    protected static Item[] items = new Item[20];
    
    public static void main(String[] args) {
	initItemList();
	for(int i = 0; i < 10; i++) {
	    ff.add(items[i]);
	    nf.add(items[i]);
	    bf.add(items[i]);
	    ffd.add(items[i]);
	    
	}
	System.out.println();
	System.out.println(ff.shortInfo());
	System.out.println();
	System.out.println(nf.shortInfo());
	System.out.println();
	System.out.println(bf.shortInfo());
	System.out.println();
	ffd.apply();
	System.out.println(ffd.shortInfo());
    }

    protected static void initItemList() {
	for(int i = 0; i < 10; i++) {
	    items[i] = new Item(Generation.getRandomInt(1001));
	}
    }
    
    protected static int sumItemSizes() {
	int sum = 0;
	for(int i = 0; i < 10; i++) {
	    sum = sum + items[i].getSize();
	}
	
	return sum;
    }
    
    
    
    

}
