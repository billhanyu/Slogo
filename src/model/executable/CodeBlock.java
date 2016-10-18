package model.executable;

import java.util.List;

import exception.SyntacticErrorException;
import exception.UseBeforeDefineException;
import model.Executable;
import model.TurtleLog;

public class CodeBlock implements Executable{
	
	private List<Executable> sequence;
	
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
		double ret = 0;
		for (Executable cmd : sequence) {
			ret = cmd.execute(log);
		}
		return ret;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Executable> unravel(int index) {
		return sequence;
	}
}
