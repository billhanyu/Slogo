package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import model.executable.CodeBlock;

public class Marshaller {
	
	//TODO cx15 I18N
	
	public static final String SPACE = " ";

	public String load(String srcPath)
			throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(srcPath))) {
		    StringBuilder sb = new StringBuilder();
		    String line = null;
		    while ( (line = br.readLine()) != null) {
		        sb.append(line);
		        sb.append(SPACE);
		    }
		    return sb.toString();
		}
	}
	
	public void store(CommandHistory commandHistory, 
					  String srcPath) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(CodeBlock e : commandHistory) {
			sb.append(e.toString(false));
		}
		try (Writer writer = new BufferedWriter(new FileWriter(srcPath))) {
			writer.write(sb.toString());
		}
	}
	
	public static void main(String[] args) {
		Marshaller m = new Marshaller();
		try {
			System.out.println(m.load("data/examples/loops/circle.logo"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
