package view.floating;

import java.util.Collections;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LanguagePropertyView extends FloatingView{
	
	
	public LanguagePropertyView(Controller controller) {
		super(controller);
	}


	private VBox makeLanguagePickerBox() {
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
	
	private VBox makeSelectionBox(String label, ComboBox<String> selections){
		VBox selectionBox = new VBox();
		Label selectionLabel = new Label(label);
		selectionBox.getChildren().addAll(selectionLabel, selections);
		return selectionBox;
		
	}


	@Override
	protected void init() {
		VBox layout = new VBox();
		layout.setPadding(new Insets(10,20,10,20));
		layout.setPrefWidth(width());
		layout.setSpacing(10);
		layout.getChildren().addAll(makeLanguagePickerBox());
		this.getRoot().getChildren().add(layout);
		
	}


	@Override
	protected String title() {
		return "Language";
	}


	@Override
	protected double width() {
		// TODO Auto-generated method stub
		return 300;
	}


	@Override
	protected double height() {
		// TODO Auto-generated method stub
		return 100;
	}

}
