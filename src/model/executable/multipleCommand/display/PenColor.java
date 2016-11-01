package model.executable.multipleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import javafx.scene.paint.Color;
import model.Executable;
import model.Palette;
import model.TurtleLog;
import model.executable.TurtleCommand;

public class PenColor extends TurtleCommand{
	
	public PenColor(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(TurtleLog log)
			throws SyntacticErrorException {
		Palette palette = this.getLogHolder().getPalette();
		Color penColor = log.peekLast().getPen().getColor();
		return (double) palette.findColorInPalette(penColor);
	}

	@Override
	public String getName() {
		return "pencolor";
	}
}