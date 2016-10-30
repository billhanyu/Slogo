package view.floating;

import java.util.Arrays;
import java.util.stream.Collectors;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ActorState;
import model.Pen;
import view.workspace.canvas.MainCanvas;

public class PenPropertyView extends TurtleDependentView {

	private ActorState currentState;

	public PenPropertyView(Controller controller) {
		super(controller);
	}

	@Override
	protected void init() {
		this.getRoot().getChildren().clear();
		MainCanvas canvas = this.getController().getMainView().getCanvas();
		canvas.addSubscriber(this);
		currentState = this.getCurrentSelectedState();
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,20,10,20));
		layout.setPrefWidth(width());
		layout.setSpacing(10);

		HBox penDown = makePenBox();
		HBox penThick = makeThicknessBox();
		HBox type = makeTypeBox();
		HBox penColor = makePenPickerBox();
		layout.getChildren().addAll(this.makeSelectTurtleBox(), penDown, penThick, type, penColor);
		this.getRoot().getChildren().add(layout);
	}

	@Override
	protected String title() {
		return "Pen";
	}

	@Override
	protected double width() {
		return 260;
	}

	@Override
	protected double height() {
		return 220;
	}

	private HBox makePenBox() {
		Label nameLabel = new Label("Pen Down?");
		String changeTo = currentState.getPen().isDown() ? "Up" : "Down";
		Button button = new Button(changeTo);
		button.setOnAction(e -> {
			ActorState current = this.getController().getMainView()
					.getCanvas().getCurrentState();
			boolean down = current.getPen().isDown();
			current.getPen().setDown(!down);
			button.setText(down ? "Down" : "Up");
		});
		button.setPrefWidth(60);
		return this.makeLine(nameLabel, button);
	}
	
	private HBox makeThicknessBox() {
		Label nameLabel = new Label("Thickness");
		Slider slider = new Slider(1, 5, currentState.getPen().getThickness());
		slider.valueProperty().addListener((observable, old_val, new_val) -> {
			ActorState current = this.getController().getMainView()
					.getCanvas().getCurrentState();
			current.getPen().setThickness(new_val.intValue());
		});
		slider.setPrefWidth(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		return this.makeLine(nameLabel, slider);
	}
	
	private HBox makePenPickerBox() {
		ColorPicker picker = makePenPicker();
		return makeSelectionBox(this.getLabelReader().getLabel("PenLabel"), picker);
	}
	
	private ColorPicker makePenPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setValue(currentState.getPen().getColor());
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setPenColor(picker.getValue());
		});
		return picker;
	}
	
	private HBox makeTypeBox() {
		Label nameLabel = new Label("Pen Type");
		ObservableList<String> items = FXCollections.observableArrayList(
				Arrays.asList(Pen.PenType.values())
				.stream()
				.map(e -> e.toString())
				.collect(Collectors.toList()));
		ComboBox<String> combo = new ComboBox<>(items);
		combo.getSelectionModel().select(
				this.getController().getMainView().getCanvas().getCurrentState()
				.getPen().getType().toString());
		combo.setOnAction(e -> {
			Pen.PenType type = Pen.PenType.valueOf(
					combo.getSelectionModel().getSelectedItem());
			ActorState currentState = this.getController().getMainView()
					.getCanvas().getCurrentState();
			currentState.getPen().setType(type);
		});
		return this.makeLine(nameLabel, combo);
	}

}
