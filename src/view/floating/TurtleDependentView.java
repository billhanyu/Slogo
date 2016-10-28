package view.floating;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import model.ActorState;

public abstract class TurtleDependentView extends FloatingView {
	
	private int currentID;

	public TurtleDependentView(Controller controller) {
		super(controller);
		currentID = (int) controller.getLogHolder().getAllIDs().toArray()[0];
	}
	
	protected int getCurrentID() {
		return currentID;
	}
	
	protected ActorState getCurrentSelectedState() {
		return this.getController().getMainView()
				.getCanvas().getCurrentStates().get(currentID);
	}
	
	protected HBox makeSelectTurtleBox() {
		Label name = new Label("Turtle ID");
		ObservableList<Integer> ids = FXCollections.observableArrayList(
				this.getController().getLogHolder().getAllIDs());
		Label id = new Label("" + currentID);
		ComboBox<Integer> selection = new ComboBox<>(ids);
		selection.setOnAction(e -> {
			currentID = selection.getSelectionModel().getSelectedItem();
			id.setText("" + currentID);
			this.init();
		});
		HBox box = new HBox();
		box.getChildren().addAll(id, selection);
		box.setSpacing(10);
		return makeLine(name, box);
	}
	
}
