package model.executable.singleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import javafx.scene.paint.Color;
import model.ActorState;
import model.Executable;
import model.LogHolder;
import model.Palette;
import model.TurtleLog;
import model.TurtleState;
import model.executable.MultipleCommand;
import model.executable.SingleCommand;
import model.executable.StandardCommand;

public class PenColor extends SingleCommand{
	
	public PenColor(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		Palette palette = log.getPalette();
		TurtleLog prev = log.getTurtleLog(0);
		Color penColor = prev.peekLast().getPen().getColor();
		return (double) palette.findColorInPalette(penColor);
	}

	@Override
	public String getName() {
		return "pencolor";
	}
}