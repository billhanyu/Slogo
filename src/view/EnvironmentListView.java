package view;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public abstract class EnvironmentListView extends View {
	
	private ListView<String> list;
	private ObservableList<String> items;

	public EnvironmentListView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
		init();
	}
	
	abstract protected ChangeListener<String> getChangeListener();
	
	abstract String getLabelString();
	
	private void init() {
		items = FXCollections.observableArrayList (
				"Single", "Double", "Suite", "Family App");
		list = makeList(getChangeListener());
		Label lbl = new Label(getLabelString());
		
		VBox all = new VBox();
		all.setPadding(new Insets(5,5,5,5));
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.getChildren().addAll(lbl, list);
		this.getRoot().getChildren().add(all);
	}
	
	private ListView<String> makeList(ChangeListener<String> listener) {
		list = new ListView<String>();
        list.getSelectionModel().selectedItemProperty().addListener(listener);
		list.setItems(items);
		return list;
	}

}
