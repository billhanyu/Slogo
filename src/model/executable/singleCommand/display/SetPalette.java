package model.executable.singleCommand.display;

import java.util.List;

import exception.SyntacticErrorException;
import javafx.scene.paint.Color;
import model.Executable;
import model.LogHolder;
import model.executable.SingleCommand;

public class SetPalette extends SingleCommand{
	
	public SetPalette(List<Executable> argv)
			throws SyntacticErrorException {
		super(argv);
	}

	@Override
	public double execute(LogHolder log)
			throws SyntacticErrorException {
		double index = this.getArgs().get(0).execute(log);
		double r = this.getArgs().get(1).execute(log);
		double g = this.getArgs().get(2).execute(log);
		double b = this.getArgs().get(3).execute(log);
		Color color = Color.color(r/256, g/256, b/256);
		//divide by 256 b/c color requires values between 0 and 1
		//and slogo doc says component values will be integers less than 256
		log.getPalette().setColor((int) index, color);
		return index;
	}

	@Override
	public String getName() {
		return "setpalette";
	}
}