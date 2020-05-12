package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Parser {
	
	public PrintWriter printWriter;
	public Stack<String> classStack = new Stack<String>();
	public Stack<Integer> tabsCount = new Stack<Integer>();
	//public JavaGenerator java;
	//public PhpGenerator php;
	public ArrayList<Generator> genList = new ArrayList<Generator>();
	
	public void initGenerators() throws Exception {
		String phpString = "source/php";
		String javaString = "source/java";
		Path phpPath = Paths.get(phpString);
		Path javaPath = Paths.get(javaString);
		Files.createDirectories(javaPath);
		Files.createDirectories(phpPath);
		
		genList.add(new JavaGenerator(javaPath));
		genList.add(new PhpGenerator(phpPath));
	}
	
	public void allNewClass(String line) throws Exception {
		for(Generator g: this.genList) {
			g.generateNewClass(line);
		}
	}
	
	public void allProperties(String line) throws Exception {
		for(Generator g: this.genList) {
			g.generateProperties(line);
		}
	}
	
	public void allInterfaceOrAbstractClass(String line) throws Exception {
		for(Generator g: this.genList) {
			g.generateInterfaceOrAbstractClass(line);
		}
	}
	
	public void allExtendedClass(String line, String father) throws Exception {
		for(Generator g: this.genList) {
			g.generateExtendedClass(line, father);
		}
		
	}
	
	public void allClose() {
		for(Generator g: this.genList) {
			g.closeLastClassAndDeleteCommentsOfTrimmedLine("");
		}
	}
	

	
	// Note: construtoc, abstract class and method are very similar -> into one method? 
	// use File.separator; as / or \ on Windows
	public Parser() {
		Path path = Paths.get("source");
		Path javaPath = Paths.get("source/java");
		System.out.println(path.toString());
		String ich = "hello";
		boolean exists = Files.exists(path,LinkOption.NOFOLLOW_LINKS);
		if(exists) { // directory already exists
			System.out.println("Subdirectory [source] already exists");
			//System.exit(0);
		} else {
			try {
				Files.createDirectories(path);
				this.initGenerators();
				System.out.println("Succefully created directory ["+path.toString()+"]");
				this.createFiles(path);
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("Error: cannot create root-directory [source]!");
				//System.exit(0);
			}
		}
		
		System.out.println("huhu");
	}
	
	
	public void createFiles(Path p) {
		File file = new File("example.cls"); 
		try {
			this.printWriter = new PrintWriter(p.toString()+"/Test.java", "UTF-8");
			this.printWriter.write("public class Test {\n");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			  
			  String line;
			  while ((line = br.readLine()) != null) {
				line = line.replaceFirst("//.*", ""); // delete endline comment

			    if(line.matches("^[A-Z].*")) { // start of line with capital character, hence start a new class (and close the old one
			    		// parse new class
			    		this.allNewClass(line);
			    		// clear stacks and push new class
			    		this.classStack.removeAllElements();
			    		this.classStack.push(line);
			    		this.tabsCount.removeAllElements();
			    		this.tabsCount.push(this.countTabs(line)); // should be 0

			    } else { // start with a small letter or < (no capital letter)
			    	// check properties and interface or abstract class
			    	this.allProperties(line);
			    	this.allInterfaceOrAbstractClass(line);

					// we are in else part of ^[A-Z].*
			    	// stack works correctly
			    	 if(line.matches("^\t.*")) { // extend per tabulator
			    		 if(!this.classStack.isEmpty()) {
			    			 System.out.println(this.classStack);
			    			 int tabsCount = this.countTabs(line);
			    			 line = line.trim(); // shrink surrounding whitespace after tabscount
			    			 String  oldClass = this.classStack.pop();
			    			 //System.out.println(oldClass);
			    			 int oldTabsCount = this.tabsCount.pop();
			    			 if(tabsCount > oldTabsCount) { // create new child class and save old valuies before

			    				 if(line.matches("^[A-Z].*")) { // handle class
			    					 // save old values
			    					 this.classStack.push(oldClass);
			    					 this.tabsCount.push(oldTabsCount);
			    					 // create new class with father above
			    					 this.allExtendedClass(line, oldClass);
			    					 // add actual class to stacks
			    					 this.classStack.push(line);
			    					 this.tabsCount.push(tabsCount);
			    				 } else {
			    					 //toDo: Reason ???
					    			 System.out.println("No class found but property: "+line);
					    			 this.allProperties(line);
					    			 this.allInterfaceOrAbstractClass(line);
			    				 }
			    			 }
			    			 if(oldTabsCount > tabsCount){
			    				 // remove sibling
			    				 //String sibling = this.classStack.pop();
			    				 //this.tabsCount.pop();
			    				 //System.out.println("removed sibling "+sibling+" for "+line);
			    				 // consume old father class
			    				 // take father from the top of the stack
			    				 String father = this.classStack.firstElement();
			    				 //int fatherInt = this.tabsCount.pop();
			    				 System.out.println("found Father "+father+" for "+line);
			    				 //System.out.println("XXX");
			    				 this.allExtendedClass(line, father);
			    				 // save father class (back) for futher inheritance
			    				 //this.tabsCount.push(fatherInt);
			    				 //this.classStack.push(father);
			    			 }
			    			 if(tabsCount == oldTabsCount) {
			    				 // save old classes
			    				 this.classStack.push(oldClass);
			    				 this.tabsCount.push(oldTabsCount);

			    				 if(line.matches("[A-Z].*")) {
			    					 // pop sibling from stack
			    					 this.classStack.pop();
			    					 this.tabsCount.pop();
			    					 // pop father from stack
			    					 String father = this.classStack.pop();
			    					 int fatherInt = this.tabsCount.pop();
			    					 this.allExtendedClass(line, father);
			    					 // push father back to stack for further inheritance
			    					 this.classStack.push(father);
			    					 this.tabsCount.push(fatherInt);
			    					 
			    					 
			    				 } else { // line starts with small letter
			    					 System.out.println("found property "+line);
			    					 this.allProperties(line);
			    					 // extend interfaces?
			    					 this.allInterfaceOrAbstractClass(line);
			    				 }
			    			 }
			    		 } else { // no father class availible
			    			 line = line.trim(); // remove tabs
			    			 System.out.println("No class found but line: "+line);
			    			 this.allProperties(line);
			    			 this.allInterfaceOrAbstractClass(line);
			    		 }
		
			    	 }
			    			
			    		 
			     }
			    
			    
			  } // while br.readLine != null
			  // close the current files
			  this.allClose();
			  
		} catch (Exception e) { // try catch
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end of createFil
	
	public int countTabs(String line) {
		int tabs = 0;
		while(line.matches("^\t.*")) {
			line = line.replaceFirst("\t", ""); // delete first tabulator
			tabs++;
		}
		return tabs;
	}
/*
	public void generateInterfaceOrAbstractClass(Path p, String line) throws Exception {

	}

	public String[] generateProperties(String line) throws Exception {

	}

	public void generateExtendedClass(Path p, String line, String father) throws Exception {

    	
	}

	
	public void generateNewClass(Path p, String line) throws Exception {

	}

	

	
	public String parseParameterString(String paramLine) {

	}
*/
	
/*
	public void generateVisuals(String[] properties) throws Exception {

	}
*/
/*
	public void generateGetterOrSetter(String getterOrSetter, String nameColonType) throws Exception {

	}
*/
	
	public static String upperCaseFirstLetter(String text) {
		char capitalLetter = text.charAt(0);
		capitalLetter = Character.toUpperCase(capitalLetter);
		String ret = "";
		ret += capitalLetter;
		for(int i = 1; i < text.length(); i++) {
			ret += text.charAt(i);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Parser p = new Parser();
		System.out.println("juchu");
		System.out.println("Working dir: " + System.getProperty("user.dir"));
		//System.out.println("GS".matches("^(G.*|S-*)"));
		
	}

}
