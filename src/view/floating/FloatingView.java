package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
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
		Label nameLabel = new Label(name);
		Label valueLabel = new Label(value);
		return makeLine(nameLabel, valueLabel);
	}
	
	protected HBox makePropertyColorLine(String name, Color color) {
		Label nameLabel = new Label(name);
		HBox rectBox = new HBox();
		rectBox.setPadding(new Insets(5, 0, 0, 0));
		Rectangle rect = new Rectangle(20, 10, color);
		rectBox.getChildren().add(rect);
		return makeLine(nameLabel, rectBox);
	}
	
	protected HBox makeLine(Node name, Node value) {
		HBox box = new HBox();
		Region empty = new Region();
		box.setSpacing(20);
		HBox.setHgrow(empty, Priority.ALWAYS);
		box.getChildren().addAll(name, empty, value);
		return box;
	}
	
	protected HBox makePickerBox(String label, ColorPicker picker) {
		HBox pickerBox = new HBox();
		Label pickerLabel = new Label(label);
		pickerLabel.setPrefWidth(130);
		pickerBox.setSpacing(50);
		pickerBox.getChildren().addAll(pickerLabel, picker);
		return pickerBox;
	}

}
