package parser;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Stack;

public abstract class Generator {
	
	protected PrintWriter writer;
	protected Path root;
	
	String[] properties = new String[3];
	Stack<Parameter> paramStack = new Stack<Parameter>();
	
	public final int VISUALS = 0;
	public final int NAME_COLON_TYPE = 1;
	public final int GETTER_OR_SETTER = 2;
	
	
	public abstract void generateAbstractClass(String className) throws Exception;
	
	public abstract void generateInterface(String intName) throws Exception;
	
	public abstract String parseParameterString(String paramLine);
	
	public abstract void generateGetter(String type, String name);
	
	public abstract void generateSetter(String type, String name);
	
	public abstract void generateConstructor(String parameters, String constructorHeader);
	
	public abstract void generateAbstractMethod(String parameters, String nameColonType, String headerLine);
	
	public abstract void generateMethod(String parameters, String nameColonType, String headerLine);
	
	public abstract void generateVariable(String parameters, String nameColonType);
	
	public abstract void generateAbstractVoidMethod(String parameters, String nameColonType); 
	
	public abstract void generateVoidMethod(String parameters, String nameColonType);
	
	public abstract void generateSimpleClass(String className) throws Exception;
	
	public abstract void generateComplexClass(String className, String headLine) throws Exception;
	
	public abstract void generateSimpleExtendedClass(String line, String father) throws Exception; 
	
	public abstract void generateComplexExtendedCLass(String className, String father, String headLine)  throws Exception;	

	public abstract String closeLastClassAndDeleteCommentsOfTrimmedLine(String line);


	public void generateExtendedClass(String line, String father) throws Exception {
		// TODO Auto-generated method stub
		line = this.closeLastClassAndDeleteCommentsOfTrimmedLine(line);
    	
    	if(line.matches("^\\w+")) { // line contains no whitespace
    		//now we have detect a simple class beginning at the start of the line
    		this.generateSimpleExtendedClass(line, father);
    		//writer.write("}\n");
    		//writer.close();
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
    		this.generateComplexExtendedCLass(className, father, headLine);
    	}
	}
	
	
	
	public void generateNewClass(String line) throws Exception {
		// TODO Auto-generated method stub
    	line = this.closeLastClassAndDeleteCommentsOfTrimmedLine(line);
    	//System.out.println(line);
    	if(line.matches("^\\w+")) { // line contains no whitespace
    		//now we have detect a simple class beginning at the start of the line
    		this.generateSimpleClass(line);
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
    		
    		this.generateComplexClass(className, headLine);

    	}
	}
	
	public void generateVisuals() throws Exception {
		// TODO Auto-generated method stub
		String parameters = "";
		if(this.properties[2] != null) {
			String visuals = this.properties[0];
			String nameColonType = this.properties[1];
			String headerLine = this.properties[2];
			//System.out.println(visuals);
			//System.out.println(visuals.contains("p"));
			parameters +="\t"; //indent for declaration
			if(visuals.contains("p")) parameters += "public ";
			if(visuals.contains("o")) parameters += "protected ";
			if(visuals.contains("r")) parameters += "private ";
			if(visuals.contains("s")) parameters += "static ";
			if(visuals.contains("c")) { // generate constructor
				this.generateConstructor(parameters, nameColonType);
				
			} else if(visuals.contains("a")) { // abstract method of abstract class
				this.generateAbstractMethod(parameters, nameColonType, headerLine);

			} else if(visuals.contains("m")) { 
				this.generateMethod(parameters, nameColonType, headerLine);
			} else { // no method, we have a variabel with type
				this.generateVariable(parameters, nameColonType);
			}
		} else if(this.properties[1] != null) {
			String visuals = this.properties[0];
			String nameColonType = this.properties[1];
			parameters +="\t"; //indent for declaration
			if(visuals.contains("p")) parameters += "public ";
			if(visuals.contains("o")) parameters += "protected ";
			if(visuals.contains("r")) parameters += "private ";
			if(visuals.contains("s")) parameters += "static ";
			if(visuals.contains("c")) { // generate constructor
				this.generateConstructor(parameters, nameColonType);
				
			} else if(visuals.contains("a")) { // abstract method of abstract class
				this.generateAbstractVoidMethod(parameters, nameColonType);

			} else if(visuals.contains("m")) { 
				this.generateVoidMethod(parameters, nameColonType);
			} else { // no method, we have a variabel with type
				this.generateVariable(parameters, nameColonType);
			}
		} else { 
			// don't know what to do, because we have not enough parameters
			System.out.println("Warning: not enough parameters in "+properties.toString());
			System.out.print("as ");
			for(String s : properties) System.out.println(s);
		}
		
	}
	
	
	public void generateGetterOrSetter() throws Exception {
		// TODO Auto-generated method stub
		if(this.properties[this.GETTER_OR_SETTER] != null) {
			//generate Getters and Setters
			String[] varTypeAndName = this.properties[this.NAME_COLON_TYPE].split(":");
			String type = varTypeAndName[1];
			String name = varTypeAndName[0];
			
			if(this.properties[this.GETTER_OR_SETTER].contains("G")) {
				this.generateGetter(type, name);				
			}
			
			if(this.properties[this.GETTER_OR_SETTER].contains("S")) {
				this.generateSetter(type, name);
			}
		}
	}
	
	public void generateInterfaceOrAbstractClass(String line) throws Exception {
		// TODO Auto-generated method stub
    	if(line.matches("^<I.*|^<A.*")) {
    		
    		line = this.closeLastClassAndDeleteCommentsOfTrimmedLine(line);
        	
    		if(line.matches("^<I.*")) {
    			// create Interface
    			String iName = line.replaceAll("^<I\\s", ""); // delete leading interface marker with empty space at the end
    			this.generateInterface(iName);
    			
    			
    		}
    		if(line.matches("^<A.*")) {
    			// create abstract class
    			String aName = line.replaceAll("^<A\\s", ""); // delete leading abstract class
    			this.generateAbstractClass(aName); 
    		}
    	 }
	}
	

	
	
	public void initParameterStack(String paramLine) {
		paramLine = paramLine.trim();
		this.paramStack = new Stack<Parameter>();
		String parameters = "";
		while(paramLine != "") {
			CharSequence comma = ",";
			if(paramLine.contains(comma)) {
				int start = paramLine.lastIndexOf(',');
				int end = paramLine.length();
				String subString = paramLine.substring(start+1, end);
				//System.out.println(subString);
				Parameter p = new Parameter(subString);
				String init = p.getInitNotation();
				if(subString.equals(init)) {
					this.paramStack.push(p);
					paramLine = paramLine.substring(0, start);
				}
			} else { // first parameter
				Parameter p = new Parameter(paramLine);
				String init = p.getInitNotation();
				if( (paramLine.equals(init)) && p.notNull() ) {
					this.paramStack.push(p);
					paramLine = "";
				}
			}
		}
	}
	
	public Generator() {
		
	}
	
	public Generator(Path p) throws Exception {
		this.init(p);
	}
	
	protected void init(Path p) throws Exception {
		this.root = p;
		this.writer = new PrintWriter(this.root.toString()+"/test.txt");
		this.writer.write("Hello World");
		// init with nulls
		for(String prop: properties) prop = null;
	}
	
	public String generateMethodParameters(String line) {
		String methodParameters = "";
		if(line.matches(".*(.+).*")) {
			line = line.trim();
			int start = line.indexOf('(')+1; //right from (
			int end = line.indexOf(')'); // left from ) - note substring method does not include end char itself
			if(start < end) {
				String parameterString = line.substring(start, end);
				methodParameters = this.parseParameterString(parameterString);
				
			}
		}
			return methodParameters;
	}
	
	public void deleteProperties() {
		for(int i = 0; i < this.properties.length; i++) {
			this.properties[i] = null;
		}
	}
	
	public String[] generateProperties(String line) throws Exception {		
		
		 // start with public,protected,private,constructor,static,method,abstract method
		if(line.matches("^p.*|^o.*|^r.*|^c.*|^s.*|^m.*|^a.*")) {
    		//System.out.println(line);
    		String[] params = line.split("\\s");
    		//for(String param: params)System.out.println(param);
    		//System.out.println(params.length);
    		int counter = 0;
    		boolean getterOrSetter = false;
    		this.deleteProperties();
    		while(counter < params.length) {
    			if(counter == 0) this.properties[VISUALS] = params[counter];
    			if(counter == 1) this.properties[NAME_COLON_TYPE] = params[counter];
    			if(counter == 2)  {
    				getterOrSetter = params[counter].matches("^(G.*|S.*)"); // match G or S at the beginning
      				this.properties[GETTER_OR_SETTER] = params[counter];
    			}
    			counter++;
    		}
    		//System.out.println(visuals+nameColonType+getterOrSetter);
    		counter = 0;

    		
        	this.generateVisuals();
    		if(getterOrSetter) this.generateGetterOrSetter();

		}
		
		return properties;

	}
	
	
}
