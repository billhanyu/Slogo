package view;

import controller.Controller;
import javafx.scene.control.TextArea;

public class Console extends View {
	
	private TextArea textArea;
	
	public Console(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
		init();
	}

	public void setText(String text) {
		
	}
	
	private void init() {
		textArea = new TextArea();
		
	}

}
