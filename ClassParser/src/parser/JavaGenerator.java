package parser;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Stack;

public class JavaGenerator extends Generator {

	
	public JavaGenerator() {
		
	}
	
	public JavaGenerator(Path p) throws Exception {
		// TODO Auto-generated constructor stub
		this.init(p);
	}
	
	@Override
	public void generateAbstractClass(String className) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".java");
		this.writer.write("public abstract class "+className+" {\n");
		System.out.println("Abstract class "+className);
		
	}
	
	@Override
	public void generateInterface(String intName) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+intName+".java");
		this.writer.write("public interface "+intName+" {\n");
		System.out.println("Interface "+intName);
	}
	
	@Override
	public void generateSimpleExtendedClass(String line, String father) throws Exception {
		String className = line;
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".java");
		this.writer.write("public class "+className+" extends "+father+" {\n");
		System.out.println("Created extended class ("+className+") from "+father);
	}
	
	@Override
	public void generateComplexExtendedCLass(String className, String father, String headLine)  throws Exception {
		headLine += "{\n";

		this.writer = new PrintWriter(this.root.toString()+"/"+className+".java");
		this.writer.write("public class "+className+" extends "+father+" "+headLine);
		System.out.println("Complex class ("+headLine+") created from "+father);
	}





	@Override
	public void generateSimpleClass(String className) throws Exception {
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".java");
		this.writer.write("public class "+className+" {\n");
   		System.out.println("Created simple class ("+className+")");
	}
	
	@Override
	public void generateComplexClass(String className, String headLine) throws Exception {
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".java");
		this.writer.write("public class "+headLine);
		System.out.println("Complex class ("+headLine+") created");
	}
 



	@Override
	public String parseParameterString(String paramLine) {
		// TODO Auto-generated method stub
		String parameters = "";
		this.initParameterStack(paramLine);
		
		while(!paramStack.isEmpty()) {
			Parameter param = paramStack.pop();
			if(!paramStack.isEmpty()) parameters += param.toString()+", ";
			else parameters += param.toString();
		}
		
		return parameters;
	}

	@Override
	public void generateConstructor(String parameters, String constructorHeader) {
		parameters += "public ";
		constructorHeader = constructorHeader.substring(0, constructorHeader.indexOf("(")) + "("+this.generateMethodParameters(constructorHeader)+")";
		constructorHeader += "{ \n";
		constructorHeader += "\t}\n\n";
		this.writer.write(parameters + constructorHeader);
	}
	
	@Override
	public void generateAbstractMethod(String parameters, String nameColonType, String headerLine) {
		parameters += "abstract " + nameColonType + " ";
		String methodName = headerLine;
		// methodName = name of method ( methodParameters );
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +";\n";
		this.writer.write(parameters);
	}
	
	@Override
	public void generateMethod(String parameters, String nameColonType, String headerLine) {
		parameters += nameColonType+" ";
		String methodName = headerLine;
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +" { \n\t}\n\n";
		this.writer.write(parameters);
	}
	
	@Override
	public void generateVariable(String parameters, String nameColonType) {
		//System.out.println(nameColonType);
		String[] varTypeAndName = nameColonType.split(":");
		// first entry is varName, second varType
		parameters += varTypeAndName[1] + " " + varTypeAndName[0]+";\n";
		//System.out.println(parameters);
		this.writer.write(parameters);
		//System.out.println("Parameters: "+parameters);
	}
	
	@Override
	public void generateAbstractVoidMethod(String parameters, String nameColonType) {
		parameters += "abstract void ";
		String methodName = nameColonType;
		// methodName = name of method ( methodParameters );
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +";\n";
		this.writer.write(parameters);
	}
	
	@Override
	public void generateVoidMethod(String parameters, String nameColonType) {
		parameters += "void ";
		String methodName = nameColonType;
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +" { \n\t}\n\n";
		this.writer.write(parameters);
	}
	
	/*
	public void generateVoidConstrutor(String parameters, String nameColonType) {
		parameters += "public void ";
		String constructorHeader = nameColonType;
		constructorHeader = constructorHeader.substring(0, constructorHeader.indexOf("(")) + "("+this.generateMethodParameters(constructorHeader)+")";
		constructorHeader += "{ \n";
		constructorHeader += "\t}\n\n";
		this.writer.write(parameters + constructorHeader);
	}
	*/


	@Override
	public void generateGetter(String type, String name) {
		// generate Getter
		String getterHeader = "\t"+"public "+ type +" get";
		String upperName = Parser.upperCaseFirstLetter(name);
		getterHeader += upperName+"() {\n";
		//System.out.println(getterHeader);
		this.writer.write(getterHeader);
		this.writer.write("\t\t"+"return this."+name+";\n");
		this.writer.write("\t"+"}\n");
	}
	
	@Override
	public void generateSetter(String type, String name) {
		//generate Setter
		String setterstring = "\t"+"public void set";
		String upperName = Parser.upperCaseFirstLetter(name);
		setterstring += upperName + "("+type+" "+name+") {\n";
		setterstring += "\t\t"+"this."+name+" = "+name+";\n";
		setterstring += "\t"+"}\n";
		//System.out.println(setterstring);
		this.writer.write(setterstring);
	}

	@Override
	public String closeLastClassAndDeleteCommentsOfTrimmedLine(String line)  {
		// TODO Auto-generated method stub
		this.writer.write("}\n");
		this.writer.close();
		
		line = line.replaceFirst("//.*/", "");
		return line;
	}


	



	
	

}
