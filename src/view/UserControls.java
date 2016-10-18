package view;

import java.io.File;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class UserControls extends View {
	
	private static final String NO_IMAGE_CHOSEN = "No Image Chosen";
	private static final String TURTLE_IMAGE_UPDATED = "Turtle Image updated!";
	private static final String SELECT_TURTLE_IMAGE = "Select Turtle Image";
	private static final String BACKGROUND_LABEL = "Background Color";
	private static final String PEN_LABEL = "Pen Color";

	public UserControls(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	private void init() {
		HBox backgroundBox = makeBackgroundPickerBox();
		HBox penBox = makePenPickerBox();
		Button changeImageButton = makeChangeImageButton();
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5,5,5,5));
		box.setSpacing(20);
		box.setPrefWidth(this.getWidth());
		box.getChildren().addAll(backgroundBox, penBox, changeImageButton);
		this.getRoot().getChildren().add(box);
	}
	
	private HBox makeBackgroundPickerBox() {
		ColorPicker picker = makeBackgroundPicker();
		return makePickerBox(BACKGROUND_LABEL, picker);
	}
	
	private HBox makePenPickerBox() {
		ColorPicker picker = makePenPicker();
		return makePickerBox(PEN_LABEL, picker);
	}

	private ColorPicker makeBackgroundPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setBackgroundColor(picker.getValue());
		});
		return picker;
	}
	
	private ColorPicker makePenPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setPenColor(picker.getValue());
		});
		return picker;
	}
	
	private HBox makePickerBox(String label, ColorPicker picker) {
		HBox pickerBox = new HBox();
		pickerBox.setAlignment(Pos.CENTER);
		Label pickerLabel = new Label(label);
		pickerBox.getChildren().addAll(pickerLabel, picker);
		return pickerBox;
	}

	private Button makeChangeImageButton() {
		Button btn = new Button(SELECT_TURTLE_IMAGE);
		btn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(SELECT_TURTLE_IMAGE);
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				Image newImage = new Image(imageFile.toURI().toString());
				this.getController().getMainView().getCanvas().getTurtleView().
					setImage(newImage);
				this.getController().getMainView().getConsole().
					appendText(TURTLE_IMAGE_UPDATED, TextType.Success);
			}
			else {
				this.getController().getMainView().getConsole().
					appendText(NO_IMAGE_CHOSEN, TextType.Error);
			}
		});
		return btn;
	}

}
