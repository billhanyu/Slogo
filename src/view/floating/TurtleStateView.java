package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ActorState;

public class TurtleStateView extends FloatingView {

	public TurtleStateView(Controller controller) {
		super(controller);
	}

	@Override
	protected void init() {
		ActorState currentState = this.getController()
				.getMainView()
				.getCanvas()
				.getCurrentState();
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,10,10,10));
		layout.setPrefWidth(width());
		HBox turtleImage = makeTurtleImageBox();
		HBox positionX = this.makePropertyLine("Position X: ", 
				""+currentState.getPositionX());
		layout.getChildren().addAll(turtleImage, positionX);
		this.getRoot().getChildren().add(layout);
	}
	
	@Override
	protected String title() {
		return "Turtle State";
	}

	@Override
	protected double width() {
		return 200;
	}

	@Override
	protected double height() {
		return 200;
	}
	
	private HBox makeTurtleImageBox() {
		HBox imgBox = new HBox();
		imgBox.setAlignment(Pos.CENTER);
		ImageView turtleImage = new ImageView(this.getController()
				.getMainView()
				.getCanvas()
				.getTurtleView()
				.getImageView()
				.getImage());
		imgBox.getChildren().add(turtleImage);
		return imgBox;
	}

}
