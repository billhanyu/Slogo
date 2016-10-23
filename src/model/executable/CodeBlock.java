package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;
import model.Executable;
import model.GlobalVariables;
import model.SemanticsRegistry;
import model.TurtleLog;
import util.Utils;

public class CodeBlock implements Executable{
	
	private List<Executable> sequence;
	private GlobalVariables localVarRefs;
	private GlobalVariables globalVarRefs;
	private SemanticsRegistry semantics;
	private String name;
	
	public CodeBlock(List<Executable> sequence) {
		this.sequence = sequence;
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
		Variable match;
		for (Variable var : localVarRefs) {
			if (var.getExpression() == null &&
					(match = Utils.listContains(globalVarRefs, var.getName())) != null) {
				var.setExpression(match.getExpression());
			}
		}
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
	public GlobalVariables getLocalVarRefs() {
		return localVarRefs;
	}
	
	public GlobalVariables getGlobalVarRefs() {
		return globalVarRefs;
	}
	
	public CodeBlock setVarRefs(GlobalVariables localVars, GlobalVariables globalVars) {
		this.localVarRefs = localVars;
		this.globalVarRefs = globalVars;
		return this;
	}
	
	public SemanticsRegistry getSemantics() {
		return semantics;
	}
	
	public CodeBlock setSemantics(SemanticsRegistry semantics) {
		this.semantics = semantics;
		return this;
	}

	@Override
	public void setName(String newName) {
		name = newName;		
	}
}
