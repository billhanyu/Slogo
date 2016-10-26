package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
		stage.setResizable(false);
	}
	
	protected HBox makePropertyLine(String name, String value) {
		HBox box = new HBox();
		Label nameLabel = new Label(name);
		Label valueLabel = new Label(value);
		Region empty = new Region();
		box.setSpacing(20);
		HBox.setHgrow(empty, Priority.ALWAYS);
		box.getChildren().addAll(nameLabel, empty, valueLabel);
		return box;
	}
	
	protected HBox makePropertyColorLine(String name, Color color) {
		HBox box = new HBox();
		Label nameLabel = new Label(name);
		HBox rectBox = new HBox();
		rectBox.setPadding(new Insets(5, 0, 0, 0));
		Rectangle rect = new Rectangle(20, 10, color);
		rectBox.getChildren().add(rect);
		Region empty = new Region();
		box.setSpacing(20);
		HBox.setHgrow(empty, Priority.ALWAYS);
		box.getChildren().addAll(nameLabel, empty, rectBox);
		return box;
	}

}
