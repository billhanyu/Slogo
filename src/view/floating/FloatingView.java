package view.floating;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import view.View;

public abstract class FloatingView extends View {
	
	private Stage stage;
	
	public FloatingView(Controller controller) {
		super(controller, 0, 0);
		init();
		initStage();
	}
	
	public void show() {
		stage.show();
	}
	
	public void close() {
		stage.close();
	}
	
	protected abstract void init();
	
	protected abstract String title();
	
	protected abstract double width();
	
	protected abstract double height();
	
	private void initStage() {
		stage = new Stage();
		stage.setTitle(title());
		stage.setHeight(this.height());
		stage.setWidth(this.width());
		Scene scn = new Scene(this.getRoot());
		stage.setScene(scn);
	}
	
	protected HBox makePropertyLine(String name, String value) {
		HBox box = new HBox();
		Label nameLabel = new Label(name);
		Label valueLabel = new Label(value);
		box.setSpacing(20);
		box.getChildren().addAll(nameLabel, valueLabel);
		HBox.setHgrow(nameLabel, Priority.ALWAYS);
		return box;
	}

}
