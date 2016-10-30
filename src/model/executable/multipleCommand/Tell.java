package model.executable.multipleCommand;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;

/**
 * @author billyu
 * set active IDs to the list in [ ] 
 * number of IDs is arbitrary
 */
public class Tell extends TellAskProto {

	public Tell(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		List<Integer> indices = this.getActiveIDs(log);
		log.setActiveIDs(indices);
		return indices.get(indices.size() - 1);
	}

	@Override
	public String getName() {
		return "tell";
	}

}
