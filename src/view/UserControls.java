package view;

import java.io.File;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class UserControls extends View {
	
	private static final String PICKER_LABEL = "Background Color";

	public UserControls(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	private void init() {
		VBox pickerBox = makePickerBox();
		Button changeImageButton = makeChangeImageButton();
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5,5,5,5));
		box.setSpacing(30);
		box.setPrefWidth(this.getWidth());
		box.getChildren().addAll(pickerBox, changeImageButton);
		this.getRoot().getChildren().add(box);
	}
	
	private VBox makePickerBox() {
		VBox pickerBox = new VBox();
		pickerBox.setAlignment(Pos.CENTER);
		Label pickerLabel = new Label(PICKER_LABEL);
		ColorPicker picker = makeBackgroundPicker();
		pickerBox.getChildren().addAll(pickerLabel, picker);
		return pickerBox;
	}

	private ColorPicker makeBackgroundPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> {
			this.getController().getMainView().
			getCanvas().setBackgroundColor(picker.getValue());
		});
		return picker;
	}

	private Button makeChangeImageButton() {
		Button btn = new Button("Select Turtle Image");
		btn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				Image newImage = new Image(imageFile.toURI().toString());
				this.getController().getMainView().getCanvas().getTurtleView().setImage(newImage);
			}
			else {
				//TODO: error handling
			}
		});
		return btn;
	}

}
