package view.floating;

import java.util.Collections;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Chalena Scholl
 * This class extends FloatingView to provide a drop-down menu for certain workspace properties.
 */
public class WorkspaceStateView extends FloatingView {
	
	public WorkspaceStateView(Controller controller) {
		super(controller);
	}
	
	@Override
	protected void init() {
		this.getController().getLogHolder().getWorkspaceState().addSubscriber(this);
		VBox options = new VBox();
		options.setSpacing(10);
		options.setPadding(new Insets(5,5,5,5));
		options.getChildren().addAll(makeLanguagePickerBox(), makeBackgroundPickerBox());
		this.getRoot().getChildren().add(options);
	}

	@Override
	protected String title() {
		return "Workspace";
	}

	@Override
	protected double width() {
		return 300;
	}

	@Override
	protected double height() {
		return 100;
	}

	private HBox makeLanguagePickerBox() {
		ObservableList<String> options = FXCollections.observableArrayList(
				this.getLabelReader().getLabel("AvailableLanguages").split(","));
		String defaultValue = 
				this.getController().getLogHolder().getWorkspaceState().getLanguage();
		ComboBox<String> selections = makeComboBox(options, defaultValue);
		return makeSelectionBox(this.getLabelReader().getLabel("LanguageLabel"), selections, 130);
	}
	
	private ComboBox<String> makeComboBox(ObservableList<String> options, String defaultValue){
		Collections.sort(options);
		ComboBox<String> selections = new ComboBox<String>(options);
		selections.setValue(defaultValue);
		selections.setOnAction((event) -> {
			String language = selections.getSelectionModel().getSelectedItem();
			this.getController().getLogHolder().getWorkspaceState().setLanguage(language);
		    this.getController().setLanguage(language);
		});
		return selections;
	}
	
	private HBox makeBackgroundPickerBox() {
		ColorPicker picker = makeBackgroundPicker();
		return makeSelectionBox(
				this.getLabelReader().getLabel("BackgroundLabel"), picker, 130);
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

}
