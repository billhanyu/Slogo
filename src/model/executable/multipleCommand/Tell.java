package model.executable.multipleCommand;

import java.util.List;

import exception.SyntacticErrorException;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class Tell extends SingleCommand {

	public Tell(List<Executable> argv) throws SyntacticErrorException {
		super(argv);
	}
	
	@Override
	public double execute(LogHolder log) throws SyntacticErrorException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}