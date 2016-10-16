package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class EnvironmentView extends View {
	
	private GlobalVarsView globalVarsView;
	private UserCommandsView userCommandsView;
	private CommandHistoryView commandHistoryView;
	
	public EnvironmentView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
		init();
	}
	
	private void init() {
		globalVarsView = new GlobalVarsView(this.getController(), 0,0, this.getWidth(), this.getHeight() / 3);
		commandHistoryView = new CommandHistoryView(this.getController(), 0,0, this.getWidth(), this.getHeight() / 3);
		userCommandsView = new UserCommandsView(this.getController(), 0,0, this.getWidth(), this.getHeight() / 3);
		VBox all = new VBox();
		all.setPrefWidth(this.getWidth());
		all.setPadding(new Insets(5,5,5,5));
		all.getChildren().addAll(globalVarsView.getUI(), commandHistoryView.getUI(), userCommandsView.getUI());
		this.getRoot().getChildren().add(all);
	}
	
}
