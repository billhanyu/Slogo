package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;
import model.Executable;
import model.GlobalVariables;
import model.LogHolder;
import model.SemanticsRegistry;
import util.Utils;

/**
 * A wrapper for a list of Executable
 * @author CharlesXu
 */
public class CodeBlock implements Executable {
	
	private List<Executable> sequence;
	private List<Executable> pendingArgs;
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
	public double execute(LogHolder log)
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
	
	public String toString(boolean withBrackets) {
		StringBuilder sb = new StringBuilder();
		if (withBrackets) sb.append("[").append(SPACE);
		Utils.appendList(sb, sequence, SPACE);
		if (withBrackets) sb.append("]").append(SPACE);
		return sb.toString();
	}

	@Override
	public String toString() {
		return toString(true);
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
	
	public CodeBlock setPendingArgs(List<Executable> args) {
		pendingArgs = args;
		return this;
	}
	
	public List<Executable> getPendingArgs() {
		return pendingArgs;
	}

	@Override
	public void setName(String newName) {
		name = newName;		
	}
}
