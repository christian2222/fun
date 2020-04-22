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
import java.util.Stack;

public class Parser {
	
	public PrintWriter printWriter;
	public Stack<String> classStack = new Stack<String>();
	public Stack<Integer> tabsCount = new Stack<Integer>();
	
	public final int VISUALS = 0;
	public final int NAME_COLON_TYPE = 1;
	public final int GETTER_OR_SETTER = 2;
	
	// use File.separator; as / or \ on Windows
	public Parser() {
		Path path = Paths.get("source");
		System.out.println(path.toString());
		String ich = "hello";
		boolean exists = Files.exists(path,LinkOption.NOFOLLOW_LINKS);
		if(exists) { // directory already exists
			System.out.println("Subdirectory [source] already exists");
			//System.exit(0);
		} else {
			try {
				Files.createDirectories(path);
				System.out.println("Succefully created directory ["+path.toString()+"]");
				this.createFiles(path);
				
				
			} catch (IOException e) {
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
			    		this.generateNewClass(p, line);
			    		// clear stacks and push new class
			    		this.classStack.removeAllElements();
			    		this.classStack.push(line);
			    		this.tabsCount.removeAllElements();
			    		this.tabsCount.push(this.countTabs(line)); // should be 0

			    } else { // start with a small letter or < (no capublic int al letter)
			    	// check properties and interface or abstract class
			    	this.generateProperties(line);
			    	this.generateInterfaceOrAbstractClass(p,line);

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
			    					 this.generateExtendedClass(p, line, oldClass);
			    					 // add actual class to stacks
			    					 this.classStack.push(line);
			    					 this.tabsCount.push(tabsCount);
			    				 } else {
			    					 //toDo: Reason ???
					    			 System.out.println("No class found but property: "+line);
					    			 this.generateProperties(line);
					    			 this.generateInterfaceOrAbstractClass(p, line);
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
			    				 this.generateExtendedClass(p, line, father);
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
			    					 this.generateExtendedClass(p, line, father);
			    					 // push father back to stack for further inheritance
			    					 this.classStack.push(father);
			    					 this.tabsCount.push(fatherInt);
			    					 
			    					 
			    				 } else { // line starts with small letter
			    					 System.out.println("found property "+line);
			    					 this.generateProperties(line);
			    					 // extend interfaces?
			    					 this.generateInterfaceOrAbstractClass(p, line);
			    				 }
			    			 }
			    		 } else { // no father class availible
			    			 line = line.trim(); // remove tabs
			    			 System.out.println("No class found but line: "+line);
			    			 this.generateProperties(line);
			    			 this.generateInterfaceOrAbstractClass(p, line);
			    		 }
		
			    	 }
			    			
			    		 
			     }
			    
			    
			  } // while br.readLine != null
			  // close the current file
			  this.printWriter.write("}\n");
			  this.printWriter.close();
			  
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

	public void generateInterfaceOrAbstractClass(Path p, String line) throws Exception {
    	if(line.matches("^<I.*|^<A.*")) {
        	line = line.replaceFirst("//.*", ""); // kill all lineending comments (starting with //)
        	line = line.trim();
        	// close previous class or interface
        	this.printWriter.write("}\n");
        	this.printWriter.close();
        	
    		if(line.matches("^<I.*")) {
    			// create Interface
    			line = line.replaceAll("^<I\\s", ""); // delete leading interface marker with empty space at the end
    			String iName = line;
    			this.printWriter = new PrintWriter(p.toString()+"/"+iName+".java");
    			this.printWriter.write("public interface "+iName+" {\n");
    			System.out.println("Interface "+line);
    		}
    		if(line.matches("^<A.*")) {
    			// create abstract class
    			line = line.replaceAll("^<A\\s", ""); // delete leading abstract class
    			String aName = line;
    			this.printWriter = new PrintWriter(p.toString()+"/"+aName+".java");
    			this.printWriter.write("public abstract class "+aName+" {\n");
    			System.out.println("Abstract class "+line);
    		}
    	 }
	}
	
	public String[] generateProperties(String line) throws Exception {
		String[] properties = new String[3];
		// init with nulls
		for(String prop: properties) prop = null;
		
		if(line.matches("^p.*|^o.*|^r.*")) {
    		//System.out.println(line);
    		String[] params = line.split("\\s");
    		//for(String param: params)System.out.println(param);
    		//System.out.println(params.length);
    		int counter = 0;

    		while(counter < params.length) {
    			if(counter == 0) properties[VISUALS] = params[counter];
    			if(counter == 1) properties[NAME_COLON_TYPE] = params[counter];
    			if(counter == 2) properties[GETTER_OR_SETTER] = params[counter];
    			counter++;
    		}
    		//System.out.println(visuals+nameColonType+getterOrSetter);
    		counter = 0;

    		this.generateVisuals(properties[VISUALS], properties[NAME_COLON_TYPE]);
    		
    		this.generateGetterOrSetter(properties[GETTER_OR_SETTER], properties[NAME_COLON_TYPE]);
		}
		
		return properties;

	}

	public void generateExtendedClass(Path p, String line, String father) throws Exception {
    	// close and end last file
    	this.printWriter.write("}\n");
    	this.printWriter.close();
    	line = line.replaceFirst("//.*", ""); // kill all lineending comments (starting with //)
    	line = line.trim(); // delete surrounding whitespaces
    	
    	if(line.matches("^\\w+")) { // line contains no whitespace
    		//now we have detect a simple class beginning at the start of the line
    		String className = line;
    		this.printWriter = new PrintWriter(p.toString()+"/"+className+".java");
    		this.printWriter.write("public class "+className+" extends "+father+"{\n");
    		//writer.write("}\n");
    		//writer.close();
    		System.out.println("Created extended class ("+className+") from "+father);
    	} else { // line contains whitespaces
    		String[] params = line.split("\\s"); // split params by whitespace
    		String className = params[0];
    		String headLine = "";
    		for(String param: params) {
    			switch(param) {
    				case "e":
    					// attention: double inheritance is forbidden
    					//headLine += "extends ";
    					break;
    				case "i":
    					headLine += "implements ";
    					break;
    				default:
    					// dont insert classname because class will be extended
    					if(param != className) headLine += param + " ";
    					break;
    			}
    		}
    		headLine += "{\n";

    		this.printWriter = new PrintWriter(p.toString()+"/"+className+".java");
    		this.printWriter.write("public class "+className+" extends "+father+" "+headLine);
    		System.out.println("Complex class ("+headLine+") created from "+father);
    	}
    	
	}
	
	public void generateNewClass(Path p, String line) throws Exception {
    	// close and end last file
    	this.printWriter.write("}\n");
    	this.printWriter.close();

    	line = line.replaceFirst("//.*", ""); // kill all lineending comments (starting with //)
    	line = line.trim(); // delete surrounding whitespaces
    	//System.out.println(line);
    	if(line.matches("^\\w+")) { // line contains no whitespace
    		//now we have detect a simple class beginning at the start of the line
    		String className = line;
    		this.printWriter = new PrintWriter(p.toString()+"/"+className+".java");
    		this.printWriter.write("public class "+className+" {\n");
    		//writer.write("}\n");
    		//writer.close();
    		System.out.println("Created simple class ("+className+")");
    	} else { // line contains whitespaces
    		String[] params = line.split("\\s"); // split params by whitespace
    		String className = params[0];
    		String headLine = "";
    		for(String param: params) {
    			switch(param) {
    				case "e":
    					headLine += "extends ";
    					break;
    				case "i":
    					headLine += "implements ";
    					break;
    				default:
    					headLine += param + " ";
    					break;
    			}
    		}
    		headLine += "{\n";

    		this.printWriter = new PrintWriter(p.toString()+"/"+className+".java");
    		this.printWriter.write("public class "+headLine);
    		System.out.println("Complex class ("+headLine+") created");
    	}
	}
	
	
	public void generateVisuals(String visuals, String nameColonType) throws Exception {
		String parameters = "";
		if(visuals != null) {
			//System.out.println(visuals);
			//System.out.println(visuals.contains("p"));
			parameters +="\t"; //indent for declaration
			if(visuals.contains("p")) parameters += "public ";
			if(visuals.contains("o")) parameters += "protected ";
			if(visuals.contains("r")) parameters += "private ";
			if(visuals.contains("c")) parameters += "public ";
			if(visuals.contains("s")) parameters += "static ";
			if(visuals.contains("a")) { // abstract method of abstract class
				parameters += "void abstract ";
				String methodName = nameColonType;
				parameters += methodName +";\n";
				this.printWriter.write(parameters);

			} else if(visuals.contains("m")) { 
				parameters += "void ";
				String methodName = nameColonType;
				parameters += methodName +" { \n\t}\n\n";
				this.printWriter.write(parameters);
			} else { // no method, we have a variabel with type
				//System.out.println(nameColonType);
				String[] varTypeAndName = nameColonType.split(":");
				// first entry is varName, second varType
				parameters += varTypeAndName[1] + " " + varTypeAndName[0]+";\n";
				//System.out.println(parameters);
				this.printWriter.write(parameters);
				//System.out.println("Parameters: "+parameters);
			}
		} else { // no method, we have a variabel with type
			// Note: duplicated code, but only two lines
			//System.out.println(nameColonType);
			String[] varTypeAndName = nameColonType.split(":");
			// first entry is varName, second varType
			parameters += varTypeAndName[1] + " " + varTypeAndName[0]+";\n";
			//System.out.println(parameters);
			this.printWriter.write(parameters);
			//System.out.println("Parameters: "+parameters);
		}
		
	}
	
	public void generateGetterOrSetter(String getterOrSetter, String nameColonType) throws Exception {
		if(getterOrSetter != null) {
			//generate Getters and Setters
			String[] varTypeAndName = nameColonType.split(":");
			String type = varTypeAndName[1];
			String name = varTypeAndName[0];
			
			if(getterOrSetter.contains("G")) {
				// generate Getter
				String getterHeader = "\t"+"public get";
				String upperName = Parser.upperCaseFirstLetter(name);
				getterHeader += upperName+"() {\n";
				//System.out.println(getterHeader);
				this.printWriter.write(getterHeader);
				this.printWriter.write("\t\t"+"return this."+name+";\n");
				this.printWriter.write("\t"+"}\n");
				
				
			}
			if(getterOrSetter.contains("S")) {
				//generate Setter
				String setterstring = "\t"+"public set";
				String upperName = Parser.upperCaseFirstLetter(name);
				setterstring += upperName + "("+type+" "+name+") {\n";
				setterstring += "\t\t"+"this."+name+" = "+name+";\n";
				setterstring += "\t"+"}\n";
				//System.out.println(setterstring);
				this.printWriter.write(setterstring);
			}
		}
	}
	
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
		
	}

}
