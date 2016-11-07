package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.executable.CodeBlock;
/**
 * Used to implement variable scoping
 * @author CharlesXu
 */
public class ParserContext {

	private List<Executable> instructionCacheInReverse;
	private List<Executable> pendingArgs;
	private List<Executable> codeBlock;
	private GlobalVariables vars;
	
	public ParserContext() {
		this(null);
	}
	
	public ParserContext(GlobalVariables gVars) {
		vars = (gVars == null) ? new GlobalVariables() : gVars;
		instructionCacheInReverse = new ArrayList<>();
		pendingArgs = new ArrayList<>();
		codeBlock =  new ArrayList<>();
	}

	public List<Executable> getInstructionCacheInReverse() {
		return instructionCacheInReverse;
	}

	public List<Executable> getPendingArgs() {
		return pendingArgs;
	}

	public List<Executable> getCodeBlock() {
		return codeBlock;
	}

	public void clearPendingArgs() {
		this.pendingArgs = new ArrayList<>();
	}
	
	public GlobalVariables getVars() {
		return vars;
	}
	
	public CodeBlock export() {
		Collections.reverse(instructionCacheInReverse);
		if (!pendingArgs.isEmpty()) {
			pendingArgs.addAll(instructionCacheInReverse);
			instructionCacheInReverse = pendingArgs;
		}
		return new CodeBlock(instructionCacheInReverse);
	}
}
