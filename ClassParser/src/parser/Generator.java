package parser;

import java.io.PrintWriter;
import java.nio.file.Path;

public abstract class Generator {
	
	protected PrintWriter writer;
	protected Path root;
	
	public abstract void generateInterface(String iName);
	
	public abstract void generateProperties(String line);	
	
	public abstract void generateExtendedClass(String line, String father);
	
	public abstract void generateNewClass(String line);
	
	public abstract String generateMethodParameters(String line);
	
	public abstract String parseParameterString(String paramLine);
	
	public abstract void generateVisuals(String[] properties);
	
	public abstract void generateGetterOrSetter(String getterOrSetter, String nameColonType);
	
	
	public Generator(Path p) throws Exception {
		this.init(p);
	}
	
	protected void init(Path p) throws Exception {
		this.root = p;
		this.writer = new PrintWriter(this.root.toString()+"/test.txt");
		this.writer.write("Hello World");
	}
	
	
}
