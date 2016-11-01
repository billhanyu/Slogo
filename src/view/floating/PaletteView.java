package view.floating;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Palette;

public class PaletteView extends FloatingView {

	public PaletteView(Controller controller) {
		super(controller);
	}

	@Override
	protected void init() {
		this.getController().getLogHolder().getPalette().addSubscriber(this);
		VBox list = new VBox();
		list.setSpacing(10);
		Palette palette = this.getController().getLogHolder().getPalette();
		for (int id : palette.getAllIndices()) {
			Label idLabel = new Label("" + id);
			ColorPicker picker = makePalettePicker(palette, id);
			list.getChildren().add(this.makeLine(idLabel, picker));
		}
		list.setPadding(new Insets(5,5,5,5));
		ScrollPane pane = new ScrollPane(list);
		pane.setStyle("-fx-background: rgb(255,255,255);");
		pane.setFitToWidth(true);
		pane.setPrefHeight(this.height() - 20);
		this.getRoot().getChildren().add(pane);
	}

	@Override
	protected String title() {
		return "Palette";
	}

	@Override
	protected double width() {
		return 200;
	}

	@Override
	protected double height() {
		return 300;
	}
	
	private ColorPicker makePalettePicker(Palette palette, int id) {
		ColorPicker picker = new ColorPicker();
		picker.setValue(palette.getColor(id));
		picker.setOnAction(e -> {
			palette.setColor(id, picker.getValue());
		});
		return picker;
	}
	
}
