package parser;

import java.io.PrintWriter;
import java.nio.file.Path;

public class PhpGenerator extends Generator {

	public PhpGenerator(Path p) throws Exception {
		this.init(p);
	}
	
	@Override
	public void generateAbstractClass(String className) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".php");
		this.initPhp();
		this.writer.write("abstract class "+className+" {\n");
		System.out.println("Abstract class "+className);

	}
	
	protected void initPhp() throws Exception {
		this.writer.write("<?php \n\n");
	}
	
	protected void closePhp() {
		this.writer.write("?>\n");
	}

	@Override
	public void generateInterface(String intName) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+intName+".php");
		this.initPhp();
		this.writer.write("interface "+intName+" {\n");
		System.out.println("Interface "+intName);
	}

	@Override
	public String parseParameterString(String paramLine) {
		// TODO Auto-generated method stub
		String parameters = "";
		this.initParameterStack(paramLine);
		
		while(!paramStack.isEmpty()) {
			Parameter param = paramStack.pop();
			if(!paramStack.isEmpty()) parameters += "$"+param.getName()+", ";
			else parameters += "$"+param.getName();
		}
		
		return parameters;
	}

	@Override
	public void generateGetter(String type, String name) {
		// TODO Auto-generated method stub
		// generate Getter
		String getterHeader = "\t"+"public function get";
		String upperName = Parser.upperCaseFirstLetter(name);
		getterHeader += upperName+"() {\n";
		//System.out.println(getterHeader);
		this.writer.write(getterHeader);
		this.writer.write("\t\t"+"return $this->"+name+";\n");
		this.writer.write("\t"+"}\n");
	}

	@Override
	public void generateSetter(String type, String name) {
		// TODO Auto-generated method stub
		String setterstring = "\t"+"public function set";
		String upperName = Parser.upperCaseFirstLetter(name);
		setterstring += upperName + "($"+name+") {\n";
		setterstring += "\t\t"+"$this->"+name+" = $"+name+";\n";
		setterstring += "\t"+"}\n";
		//System.out.println(setterstring);
		this.writer.write(setterstring);

	}

	@Override
	public void generateConstructor(String parameters, String constructorHeader) {
		// TODO Auto-generated method stub
		parameters += "function ";
		constructorHeader = "__construct" + "("+this.generateMethodParameters(constructorHeader)+")";
		constructorHeader += " { \n";
		constructorHeader += "\t}\n\n";
		this.writer.write(parameters + constructorHeader);
	}

	@Override
	public void generateAbstractMethod(String parameters, String nameColonType, String headerLine) {
		// TODO Auto-generated method stub
		parameters += "abstract function " + nameColonType + " ";
		String methodName = headerLine;
		// methodName = name of method ( methodParameters );
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +";\n";
		this.writer.write(parameters);
	}

	@Override
	public void generateMethod(String parameters, String nameColonType, String headerLine) {
		// TODO Auto-generated method stub
		parameters += "function  ";
		String methodName = headerLine;
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +" { \n\t}\n\n";
		this.writer.write(parameters);
	}

	@Override
	public void generateVariable(String parameters, String nameColonType) {
		// TODO Auto-generated method stub
		//System.out.println(nameColonType);
		String[] varTypeAndName = nameColonType.split(":");
		// first entry is varName, second varType
		parameters += "$" + varTypeAndName[0]+";\n";
		//System.out.println(parameters);
		this.writer.write(parameters);
		//System.out.println("Parameters: "+parameters);
	}

	@Override
	public void generateAbstractVoidMethod(String parameters, String nameColonType) {
		// TODO Auto-generated method stub
		parameters += "abstract function ";
		String methodName = nameColonType;
		// methodName = name of method ( methodParameters );
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +";\n";
		this.writer.write(parameters);
	}

	@Override
	public void generateVoidMethod(String parameters, String nameColonType) {
		// TODO Auto-generated method stub
		parameters += "function ";
		String methodName = nameColonType;
		methodName = methodName.substring(0, methodName.indexOf("(")) + "("+this.generateMethodParameters(methodName)+")";
		parameters += methodName +" { \n\t}\n\n";
		this.writer.write(parameters);
	}

	@Override
	public void generateSimpleClass(String className) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".php");
		this.initPhp();
		this.writer.write("class "+className+" {\n");
   		System.out.println("Created simple class ("+className+")");
	}

	@Override
	public void generateComplexClass(String className, String headLine) throws Exception {
		// TODO Auto-generated method stub
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".php");
		this.initPhp();
		this.writer.write("class "+headLine);
		System.out.println("Complex class ("+headLine+") created");
	}

	@Override
	public void generateSimpleExtendedClass(String line, String father) throws Exception {
		// TODO Auto-generated method stub
		String className = line;
		this.writer = new PrintWriter(this.root.toString()+"/"+className+".php");
		this.initPhp();
		this.writer.write("class "+className+" extends "+father+" {\n");
		System.out.println("Created extended class ("+className+") from "+father);
	}

	@Override
	public void generateComplexExtendedCLass(String className, String father, String headLine) throws Exception {
		// TODO Auto-generated method stub
		headLine += "{\n";

		this.writer = new PrintWriter(this.root.toString()+"/"+className+".php");
		this.initPhp();
		this.writer.write("class "+className+" extends "+father+" "+headLine);
		System.out.println("Complex class ("+headLine+") created from "+father);
	}

	@Override
	public String closeLastClassAndDeleteCommentsOfTrimmedLine(String line) {
		// TODO Auto-generated method stub
		this.writer.write("}\n?>\n\n");
		this.writer.close();
		line = line.replaceFirst("//.*", "");
		return line;
	}

}
