import javafx.application.Application;
import javafx.stage.Stage;
import view.MainWindow;

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
		new MainWindow();
	}
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		
		launch(args);
	}
}