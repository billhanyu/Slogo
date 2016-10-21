package model;

import java.util.ArrayList;
import java.util.List;

public class ParserContext {

	private List<Executable> instructionCacheInReverse;
	private List<Executable> pendingArgs;
	private List<Executable> codeBlock;
	
	public ParserContext() {
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

	public void setInstructionCacheInReverse(List<Executable> instructionCacheInReverse) {
		this.instructionCacheInReverse = instructionCacheInReverse;
	}

	public void clearPendingArgs() {
		this.pendingArgs = new ArrayList<>();
	}

	public void setCodeBlock(List<Executable> codeBlock) {
		this.codeBlock = codeBlock;
	}
	
}
