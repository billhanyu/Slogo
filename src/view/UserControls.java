package view;

import java.io.File;
import java.util.Collections;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Pen;
import view.canvas.MainCanvas;

public class UserControls extends View {

	public UserControls(Controller controller, double width, double height) {
		super(controller, width, height);
		init();
	}

	private void init() {
		VBox userButtons = makeUserButtons();
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(5,5,5,30));
		box.setSpacing(20);
		box.setPrefWidth(this.getWidth());
		box.getChildren().addAll(userButtons);
		this.getRoot().getChildren().add(box);
	}
	
	private VBox makeUserButtons() {
		VBox buttonBox = new VBox();
		buttonBox.setSpacing(10);
		ScrollBar speedBar = makeSpeedBar();
		buttonBox.getChildren().addAll(speedBar);
		return buttonBox;
		
	}
	
	private ScrollBar makeSpeedBar(){
		ScrollBar speedBar = new ScrollBar();
		speedBar.setMin(0);
		speedBar.setMax(5000);
		speedBar.setValue(3000);
		
        speedBar.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number oldValue, Number newValue) {
            	System.out.println(speedBar.getMax() - newValue.doubleValue());
            	getController().getMainView().getCanvas().setDuration(speedBar.getMax() - newValue.doubleValue());
            	
            }
        });
        
        return speedBar;
	}

}
