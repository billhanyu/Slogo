package view;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserControls extends View {

	public UserControls(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	private void init() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPrefWidth(this.getWidth());
		Label animateSpeed = new Label("Animation Speed");
		box.setAlignment(Pos.TOP_LEFT);
		box.setSpacing(5);
		box.getChildren().addAll(animateSpeed, makeSpeedBar());
		
		VBox allControls = new VBox();
		allControls.getChildren().addAll(box, makeControlButtons());
		allControls.setSpacing(10);
		this.getRoot().getChildren().addAll(allControls);
	}
	
	private HBox makeControlButtons(){
		HBox controlButtons = new HBox();
		Button pause = this.makeButton("Pause", e -> {
			this.getController().getMainView().getCanvas().pauseAnimation();
		});
		
		Button play  = this.makeButton("Play", e -> {
			this.getController().getMainView().getCanvas().playAnimation();
		});
		
		Button stop  = this.makeButton("Stop", e -> {
			this.getController().getMainView().getCanvas().stopAnimation();
		});
		controlButtons.getChildren().addAll(play, pause, stop);
		controlButtons.setSpacing(40);
		return controlButtons;
		
	}
	
	private ScrollBar makeSpeedBar(){
		ScrollBar speedBar = new ScrollBar();
		speedBar.setMin(0);
		speedBar.setMax(5000);
		speedBar.setValue(3000);
		
        speedBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number oldValue, Number newValue) {
            	getController().getMainView().getCanvas().setDuration(speedBar.getMax() - newValue.doubleValue());
            	
            }
        });
        
        return speedBar;
	}

}
