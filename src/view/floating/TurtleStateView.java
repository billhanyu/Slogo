package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.ActorState;
import view.workspace.canvas.MainCanvas;

public class TurtleStateView extends FloatingView {

	public TurtleStateView(Controller controller) {
		super(controller);
	}

	@Override
	protected void init() {
		MainCanvas canvas = this.getController().getMainView().getCanvas();
		canvas.addSubscriber(this);
		ActorState currentState = canvas.getCurrentState();
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,20,10,20));
		layout.setPrefWidth(width());
		HBox turtleImage = makeTurtleImageBox(currentState);
		HBox positionX = this.makePropertyLine("Position X", 
				"" + currentState.getPositionX());
		HBox positionY = this.makePropertyLine("Position Y",
				"" + currentState.getPositionY());
		HBox heading = this.makePropertyLine("Heading",
				"" + currentState.getHeading() % 360);
		HBox pen = this.makePropertyLine("Pen Down?",
				"" + currentState.getPen().isDown());
		HBox color = this.makePropertyColorLine("Pen Color",
				currentState.getPen().getColor());
		layout.getChildren().addAll(
				turtleImage, 
				positionX, 
				positionY, 
				heading, 
				pen, 
				color);
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
	
	private HBox makeTurtleImageBox(ActorState currentState) {
		HBox imgBox = new HBox();
		imgBox.setAlignment(Pos.CENTER);
		imgBox.setPadding(new Insets(10, 5, 20, 5));
		ImageView turtleImage = new ImageView(this.getController()
				.getMainView()
				.getCanvas()
				.getTurtleView()
				.getImageView()
				.getImage());
		turtleImage.setRotate(currentState.getHeading());
		imgBox.getChildren().add(turtleImage);
		return imgBox;
	}

}
