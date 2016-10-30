package model.executable.multipleCommand;

import java.util.ArrayList;
import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.CodeBlock;
import model.executable.Constant;
import model.executable.SingleCommand;

public class Tell extends SingleCommand {

	public Tell(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
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
		log.setActiveIDs(indices);
		return indices.get(indices.size() - 1);
	}

	@Override
	public String getName() {
		return "tell";
	}

}
