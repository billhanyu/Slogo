package view.floating;

import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

public class BackgroundPropertyView extends FloatingView {
	
	public BackgroundPropertyView(Controller controller) {
		super(controller);
	}

	private HBox makeBackgroundPickerBox() {
		ColorPicker picker = makeBackgroundPicker();
		return makeSelectionBox(
				this.getLabelReader().getLabel("BackgroundLabel"), picker);
	}
	
	private ColorPicker makeBackgroundPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setValue(
				this.getController().getLogHolder()
					.getWorkspaceState().getBackgroundColor());
		picker.setOnAction(e -> {
			this.getController().getLogHolder()
				.getWorkspaceState().setBackgroundColor(picker.getValue());
			this.getController().getMainView().
				getCanvas().setBackgroundColor(picker.getValue());
		});
		return picker;
	}

	@Override
	protected void init() {
		this.getController().getLogHolder().getWorkspaceState().addSubscriber(this);
		this.getRoot().getChildren().add(makeBackgroundPickerBox());
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
