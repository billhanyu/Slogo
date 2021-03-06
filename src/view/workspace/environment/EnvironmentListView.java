package view.workspace.environment;

import java.util.stream.Collectors;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Environment;
import model.Executable;
import view.workspace.View;

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
		           list.scrollTo(list.getItems().size() - 1);
		        }
		    }
		});
		list.setItems(items);
		return list;
	}

}
