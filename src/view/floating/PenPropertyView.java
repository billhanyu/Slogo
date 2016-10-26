package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ActorState;

public class PenPropertyView extends FloatingView {
	
	private ActorState currentState;

	public PenPropertyView(Controller controller) {
		super(controller);
	}

	@Override
	protected void init() {
		currentState = this.getController()
				.getMainView()
				.getCanvas()
				.getCurrentState();
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,20,10,20));
		layout.setPrefWidth(width());
		layout.setSpacing(10);

		HBox penDown = makePenBox();
		HBox penThick = makeThicknessBox();

		layout.getChildren().addAll(penDown, penThick);
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
		return 200;
	}

	private HBox makePenBox() {
		Label nameLabel = new Label("Pen Down?");
		String changeTo = currentState.getPen().isDown() ? "Up" : "Down";
		Button button = new Button(changeTo);
		button.setOnAction(e -> {
			boolean down = currentState.getPen().isDown();
			currentState.getPen().setDown(!down);
			button.setText(down ? "Down" : "Up");
		});
		button.setPrefWidth(60);
		return this.makeLine(nameLabel, button);
	}
	
	private HBox makeThicknessBox() {
		Label nameLabel = new Label("Thickness");
		Slider slider = new Slider(1, 5, currentState.getPen().getThickness());
		slider.valueProperty().addListener((observable, old_val, new_val) -> {
			currentState.getPen().setThickness(new_val.intValue());
		});
		slider.setPrefWidth(100);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		return this.makeLine(nameLabel, slider);
	}

}
