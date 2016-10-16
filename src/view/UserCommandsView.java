package view;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import model.UserCommands;

public class UserCommandsView extends View {
	
	private ListView<String> list;
	private ObservableList<String> userCommands;
	private static final String LABEL = "Commands";
	
	public UserCommandsView(Controller controller, double x, double y, double width, double height) {
		super(controller, x, y, width, height);
		init();
	}

	public void update(UserCommands cmds) {
		
	}
	
	private void init() {
		userCommands = FXCollections.observableArrayList (
				"Single", "Double", "Suite", "Family App");
		list = makeList();
		Label lbl = new Label(LABEL);
		
		VBox all = new VBox();
		all.setPadding(new Insets(5,5,5,5));
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.getChildren().addAll(lbl, list);
		this.getRoot().getChildren().add(all);
	}
	
	private ListView<String> makeList() {
		list = new ListView<String>();
        list.getSelectionModel().selectedItemProperty().addListener((ov, oldVal, newVal) -> {
        	System.out.println("Command selected: " + newVal);
        });
		list.setItems(userCommands);
		return list;
	}
}
