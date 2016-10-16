package model.executable;

import java.util.List;

import model.Executable;
import model.TurtleLog;

public abstract class Command implements Executable{
	
	protected List<Executable> argv;
	
	public Command(List<Executable> argv) {
		this.argv = argv;
	}
	
	@Override
	public abstract double execute(TurtleLog log);
	
	@Override
	public abstract String getName();
}
