package view;

import java.util.stream.Collectors;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Environment;
import model.Executable;

public abstract class EnvironmentListView extends View {
	
	private ListView<String> list;
	private ObservableList<String> items;

	public EnvironmentListView(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}
	
	abstract public String getLabelString();
	
	public void update(Environment<? extends Executable> environment) {
		items = FXCollections.observableArrayList(
					environment.getImmutableValues().stream()
					.map(exec -> exec.getName())
					.collect(Collectors.toList())); 
		list.setItems(items);
	}
	
	abstract protected void onDoubleClickItem(String selected);
	
	private void init() {
		items = FXCollections.observableArrayList();
		list = makeList();
		Label lbl = new Label(getLabelString());
		
		VBox all = new VBox();
		all.setPadding(new Insets(5,5,5,5));
		all.setPrefWidth(this.getWidth());
		all.setPrefHeight(this.getHeight());
		all.getChildren().addAll(lbl, list);
		this.getRoot().getChildren().add(all);
	}
	
	private ListView<String> makeList() {
		list = new ListView<String>();
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent click) {
		        if (click.getClickCount() == 2) {
		           String selected = list.getSelectionModel().getSelectedItem();
		           onDoubleClickItem(selected);
		        }
		    }
		});
		list.setItems(items);
		return list;
	}

}
