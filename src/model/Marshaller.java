package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;

import model.executable.CodeBlock;

public class Marshaller {

	public static final String SPACE = " ";

	/**
	 * Load the text file located at srcPath and return the text in String
	 * @param srcPath
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Store CommandHistory to a file at srcPath
	 * Script translated to user selected language
	 * @param commandHistory
	 * @param interpreter
	 * @param srcPath
	 * @throws IOException
	 */
	public void store(CommandHistory commandHistory,
			Interpreter interpreter,
			String srcPath) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(CodeBlock e : commandHistory) {
			sb.append(e.toString(false));
		}
		try (Writer writer = new BufferedWriter(new FileWriter(srcPath))) {
			String output = interpreter.getTranslator().translateBackUserLang(sb.toString(), SPACE);
			writer.write(output);
		}
	}

	/**
	 * @return leaders as an ArrayList
	 * @throws ClassNotFoundException 
	 */
	public WorkspaceState loadWorkspace(String srcPath) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(srcPath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		WorkspaceState result = (WorkspaceState) ois.readObject();
		ois.close();
		return result;
	}

	/**
	 * save the workspace data to file
	 * @throws IOException 
	 */
	public void store(WorkspaceState workspace,
			String srcPath) throws IOException {
		FileOutputStream fout = new FileOutputStream(srcPath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(workspace);
		oos.close();
	}

	public static void main(String[] args) {
		Marshaller m = new Marshaller();
		try {
			System.out.println(m.load("/Users/billyu/Documents/classes/CS-308/slogo_team07/data/examples/loops/circle.logo"));
			WorkspaceState state = new WorkspaceState();
			m.store(state, "data/test.state");
			state = m.loadWorkspace("data/test.state");
			System.out.println(state.getLanguage());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
