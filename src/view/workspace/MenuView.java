package view.workspace;

import java.io.File;
import java.io.IOException;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Marshaller;
import view.floating.FloatingViewManager;
import view.floating.PaletteView;
import view.floating.PenPropertyView;
import view.floating.TurtleStateView;
import view.floating.WorkspaceStateView;

/**
 * @author Chalena Scholl
 * This class makes all the menu items displayed in the UI.
 */
public class MenuView extends View {
	
	private MenuBar bar;
	private FloatingViewManager floatingManager;
	private FileChooser fileChooser;
	private Marshaller marshaller;

	public MenuView(Controller controller, double width, double height) {
		super(controller, width, height);
		floatingManager = new FloatingViewManager(controller);
		fileChooser = new FileChooser();
		marshaller = new Marshaller();
		init();
	}
	
	private void init() {
		bar = new MenuBar();
		bar.setPrefWidth(this.getWidth());
		Menu menuFile = makeMenuFile();
		Menu menuWorkspace = makeMenuWorkspace();
		Menu menuProperties = makeMenuProperties();
		Menu menuRun = makeMenuRun();
		Menu menuHelp = makeMenuHelp();
		bar.getMenus().addAll(menuFile, menuWorkspace, menuProperties, menuRun, menuHelp);
		this.getRoot().getChildren().add(bar);
		HBox.setHgrow(bar, Priority.ALWAYS);
	}
	
	private Menu makeMenuFile() {
		Menu menu = new Menu("File");
		MenuItem load = makeMenuItem("Load...", e -> {
			fileChooser.setTitle(this.getLabelReader().getLabel("TurtleImageSelector"));
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Logo files", "*.logo"));
			File logoFile = fileChooser.showOpenDialog(null);
			if (logoFile != null) {
				String path = logoFile.toURI().toString();
				String filepath = path.substring(5, path.length());
				try {
					this.getController().putScript(marshaller.load(filepath));
				} catch (IOException e1) {
					this.getController().getMainView().getConsole()
						.appendText("Error reading file", TextType.Error);
				}
			}
		});
		MenuItem save = makeMenuItem("Save", e -> {
			fileChooser.setTitle("Save");
			File saveFile = fileChooser.showSaveDialog(null);
			if (saveFile != null) {
				String path = saveFile.toURI().toString();
				String filepath = path.substring(5, path.length());
				try {
					marshaller.store(this.getController().getCommandHistory(), this.getController().getInterpreter(), filepath);
				} catch (IOException e1) {
					this.getController().getMainView().getConsole()
						.appendText("Error saving file", TextType.Error);
				}
			}
		});
		MenuItem turtleImage = makeMenuItem(this.getController().getValueReader().getLabel("TurtleImageSelector"), 
				e -> {
					fileChooser.setTitle(this.getLabelReader().getLabel("TurtleImageSelector"));
					fileChooser.getExtensionFilters().addAll(
							new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
					File imageFile = fileChooser.showOpenDialog(null);
					if (imageFile != null) {
						Image newImage = new Image(imageFile.toURI().toString());
						this.getController().getMainView().getCanvas().getTurtleViews()
							.values()
							.stream()
							.forEach(view -> view.setImage(newImage));
						this.getController().getMainView().getConsole().
							appendText(this.getLabelReader().getLabel("TurtleImageUpdated"), TextType.Success);
					}
					else {
						this.getController().getMainView().getConsole().
							appendText(this.getLabelReader().getLabel("NoImageChosen"), TextType.Error);
					}
				});
		menu.getItems().addAll(load, save, turtleImage);
		return menu;
	}
	
	private Menu makeMenuWorkspace() {
		Menu menu = new Menu("Workspace");
		MenuItem workspaceState = makeMenuItem("Workspace",
				e -> floatingManager.show(WorkspaceStateView.class));
		MenuItem palette = makeMenuItem("Palette",
				e -> floatingManager.show(PaletteView.class));
		menu.getItems().addAll(workspaceState, palette);
		return menu;
	}
	
	private Menu makeMenuProperties() {
		Menu menu = new Menu("Properties");
		MenuItem turtleState = makeMenuItem("Turtle State",
				e -> floatingManager.show(TurtleStateView.class));
		MenuItem penState = makeMenuItem("Pen",
				e -> floatingManager.show(PenPropertyView.class));
		menu.getItems().addAll(turtleState, penState);
		return menu;
	}
	
	private Menu makeMenuRun() {
		Menu menu = new Menu("Run");
		MenuItem run = makeMenuItem("Run Script", 
				e -> this.getController().getMainView().getEditor().runScript());
		menu.getItems().add(run);
		return menu;
	}
	
	
	private Menu makeMenuHelp() {
		Menu menu = new Menu("Help");
		MenuItem run = makeMenuItem("Access Help Page", 
				e -> new DisplayPage().showPage("HelpPagePath", this.getController().getValueReader()));
		menu.getItems().add(run);
		return menu;
	}
	
	private MenuItem makeMenuItem(String name, EventHandler<ActionEvent> handler) {
		MenuItem item = new MenuItem(name);
		item.setOnAction(handler);
		return item;
	}

}
