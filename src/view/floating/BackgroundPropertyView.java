package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.canvas.MainCanvas;

public class BackgroundPropertyView extends FloatingView{
	
	public BackgroundPropertyView(Controller controller) {
		super(controller);
	}

	private HBox makeBackgroundPickerBox() {
		ColorPicker picker = makeBackgroundPicker();
		return super.makePickerBox(this.getLabelReader().getLabel("BackgroundLabel"), picker);
	}
	
	private ColorPicker makeBackgroundPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setValue(MainCanvas.BACKGROUND_COLOR);
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setBackgroundColor(picker.getValue());
		});
		return picker;
	}

	@Override
	protected void init() {
		
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,20,10,20));
		layout.setPrefWidth(width());
		layout.setSpacing(10);
		layout.getChildren().addAll(makeBackgroundPickerBox());
		this.getRoot().getChildren().add(layout);
		
	}

	@Override
	protected String title() {
		return "Background";
	}

	@Override
	protected double width() {
		return 300;
	}

	@Override
	protected double height() {
		return 100;
	}

}
