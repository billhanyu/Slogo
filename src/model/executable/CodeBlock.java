package model.executable;

import java.util.ArrayList;
import java.util.List;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;
import model.Executable;
import model.TurtleLog;
import model.executable.Variable;

public class CodeBlock implements Executable{
	
	private List<Executable> sequence;
	private List<Variable> varRefs;
	private String name;
	
	public CodeBlock(List<Executable> sequence) {
		this.sequence = sequence;
		varRefs = new ArrayList<>();
	}

	/**
	 * Execute the sequence in order, return the final value
	 * from the last Executable in list, or 0 if sequence is empty
	 * @param log
	 * @return
	 * @throws UseBeforeDefineException 
	 */
	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		double ret = 0;
		for (Executable cmd : sequence) {
			ret = cmd.execute(log);
		}
		return ret;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public List<Executable> unravel() {
		return sequence;
	}
	
	/**
	 * return the list of all the variables references included in this codeblock
	 * @return
	 */
	public List<Variable> getVarRefs() {
		return varRefs;
	}
	
	// TODO cx15: USED THIS IN PARSER
	public void addVarRef(Variable ref) {
		varRefs.add(ref);
	}

	@Override
	public void setName(String newName) {
		name = newName;		
	}
}
