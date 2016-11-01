package model.executable.multipleCommand;

import java.util.ArrayList;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.StaticCommand;

/**
 * @author billyu
 * abstract class extracted to have getAcitiveIDs method
 * unravel the tell [ constant ] and ask [ constant ] brackets
 */
public abstract class TellAskProto extends StaticCommand {

	public TellAskProto(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
	/**
	 * @param log
	 * @return list of Ids specified by tell or ask command
	 * @throws SyntacticErrorException
	 */
	protected List<Integer> getActiveIDs(LogHolder log) 
			throws SyntacticErrorException {
		Executable arg = this.getArgs().get(0);
		if (!(arg instanceof CodeBlock)) {
			throw new SyntacticErrorException();
		}
		CodeBlock block = (CodeBlock) arg;
		List<Executable> inners = block.getPendingArgs();
		List<Integer> indices = new ArrayList<>();
		for (Executable inner : inners) {
			if (!(inner instanceof Constant)) {
				throw new SyntacticErrorException();
			}
			Constant index = (Constant) inner;
			indices.add((int)index.execute(log));
		}
		return indices;
	}

}
