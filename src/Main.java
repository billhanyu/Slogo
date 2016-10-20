import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author billyu
 * Starter class for SLOGO project
 */

public class Main extends Application {
	
	/**
	 * Initialize a controller
	 */
	@Override
	public void start (Stage s) {
		new Controller();
		
	}
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}