package view.floating;

import java.util.Collections;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class LanguagePropertyView extends FloatingView {
	
	public LanguagePropertyView(Controller controller) {
		super(controller);
	}

	private HBox makeLanguagePickerBox() {
		ObservableList<String> options = FXCollections.observableArrayList(
				this.getLabelReader().getLabel("AvailableLanguages").split(","));
		String defaultValue = this.getLabelReader().getLabel("DefaultLanguage");
		ComboBox<String> selections = makeComboBox(options, defaultValue);
		return makeSelectionBox(this.getLabelReader().getLabel("LanguageLabel"), selections);
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

	@Override
	protected void init() {
		this.getRoot().getChildren().add(makeLanguagePickerBox());
	}

	@Override
	protected String title() {
		return "Language";
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
