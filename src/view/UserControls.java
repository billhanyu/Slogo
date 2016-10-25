package view;

import java.io.File;
import java.util.Collections;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.TurtleState;
import view.canvas.Canvas;

public class UserControls extends View {

	public UserControls(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	private void init() {
		VBox backgroundBox = makeBackgroundPickerBox();
		VBox penBox = makePenPickerBox();
		VBox languageBox = makeLanguagePickerBox();
		VBox userButtons = makeUserButtons();
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5,5,5,30));
		box.setSpacing(20);
		box.setPrefWidth(this.getWidth());
		box.getChildren().addAll(userButtons, backgroundBox, penBox, languageBox);
		this.getRoot().getChildren().add(box);
	}
	
	private VBox makeUserButtons() {
		VBox buttonBox = new VBox();
		buttonBox.setSpacing(10);
		Button changeImageButton = makeChangeImageButton();
		Button helpPageButton = makeHelpPageButton();
		buttonBox.getChildren().addAll(helpPageButton, changeImageButton);
		return buttonBox;
		
	}

	private VBox makeBackgroundPickerBox() {
		ColorPicker picker = makeBackgroundPicker();
		return makePickerBox(this.getLabelReader().getLabel("BackgroundLabel"), picker);
	}
	
	private VBox makePenPickerBox() {
		ColorPicker picker = makePenPicker();
		return makePickerBox(this.getLabelReader().getLabel("PenLabel"), picker);
	}
	
	private VBox makeLanguagePickerBox() {
		ObservableList<String> options = FXCollections.observableArrayList(
				this.getLabelReader().getLabel("AvailableLanguages").split(","));
		String defaultValue = this.getLabelReader().getLabel("DefaultLanguage");
		ComboBox<String> selections = makeComboBox(options, defaultValue);
		return makeSelectionBox(this.getLabelReader().getLabel("LanguageLabel"), selections);
	}

	private ColorPicker makeBackgroundPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setValue(Canvas.BACKGROUND_COLOR);
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setBackgroundColor(picker.getValue());
		});
		return picker;
	}
	
	private ColorPicker makePenPicker() {
		ColorPicker picker = new ColorPicker();
		picker.setValue(TurtleState.DEFAULT_PEN_COLOR);
		picker.setOnAction(e -> {
			this.getController().getMainView().
				getCanvas().setPenColor(picker.getValue());
		});
		return picker;
	}
	
	private VBox makePickerBox(String label, ColorPicker picker) {
		VBox pickerBox = new VBox();
		Label pickerLabel = new Label(label);
		pickerBox.getChildren().addAll(pickerLabel, picker);
		return pickerBox;
	}
	
	private ComboBox<String> makeComboBox(ObservableList<String> options, String defaultValue){
		Collections.sort(options);
		ComboBox<String> selections = new ComboBox<String>(options);
		selections.setValue(defaultValue);
		selections.setOnAction((event) -> {
		    this.getController().setLanguage(selections.getSelectionModel().getSelectedItem());
		});
		return selections;
	}
	
	private VBox makeSelectionBox(String label, ComboBox<String> selections){
		VBox selectionBox = new VBox();
		//selectionBox.setAlignment(Pos.CENTER);
		Label selectionLabel = new Label(label);
		selectionBox.getChildren().addAll(selectionLabel, selections);
		return selectionBox;
		
	}
	
	private Button makeHelpPageButton() {
		Button btn = this.makeButton(this.getLabelReader().getLabel("HelpPageButton"), e -> {
			showHelpPage();;
		});
		return btn;
	}

	private Button makeChangeImageButton() {
		Button btn = new Button(this.getLabelReader().getLabel("TurtleImageSelector"));
		btn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(this.getLabelReader().getLabel("TurtleImageSelector"));
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			File imageFile = fileChooser.showOpenDialog(null);
			if (imageFile != null) {
				Image newImage = new Image(imageFile.toURI().toString());
				this.getController().getMainView().getCanvas().getTurtleView().
					setImage(newImage);
				this.getController().getMainView().getConsole().
					appendText(this.getLabelReader().getLabel("TurtleImageUpdated"), TextType.Success);
			}
			else {
				this.getController().getMainView().getConsole().
					appendText(this.getLabelReader().getLabel("NoImageChosen"), TextType.Error);
			}
		});
		return btn;
	}
	
	private void showHelpPage(){
		Stage helpPage = new Stage();
        Scene scene = new Scene(new Group());
        VBox root = new VBox();     
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser); 
        scrollPane.setFitToWidth(true);;
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
		String url = MainView.class.getResource(this.getController().getValueReader().getLabel("HelpPagePath")).toExternalForm();
        webEngine.load(url);
        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);
        helpPage.setScene(scene);
        helpPage.show();
	}

}
