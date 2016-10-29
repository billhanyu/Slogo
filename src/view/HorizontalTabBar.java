package view;

import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HorizontalTabBar{
	private TabPane tabs;
	private Node root;
	private Tab workspaceTab;
	private int tabNum;
	private boolean isFirst;
	
	public HorizontalTabBar(double prefWidth, double prefHeight) {
		tabs = new TabPane();
		tabs.setPrefSize(prefWidth, prefHeight);
		root = tabs;
		tabNum = 1;
		isFirst = true;
		addNewWorkspaceTab();
	}

	public void newWorkspace(Controller newCtrl, String text){
		 
		 Tab toAdd = new Tab();
		 toAdd.setContent(newCtrl.getMainView().getRoot());
		 toAdd.setText(text + " #" + tabNum);
		 tabNum++;
		 if (isFirst){
			 toAdd.setClosable(false);
			 isFirst = false;
		 }
		 addTab(toAdd);
	}
	
	private void addTab(Tab toAdd){
		 tabs.getTabs().add(toAdd);
		 tabs.getSelectionModel().select(toAdd);
		 tabs.setTabMinWidth(20);
		
	}
	
	
	private void addNewWorkspaceTab(){
		workspaceTab = new Tab();
		workspaceTab.setText("New SLogo");
		workspaceTab.setOnSelectionChanged(event -> {
			newWorkspace(new Controller(), "SLogo");	
        });
		tabs.getTabs().add(workspaceTab);
	}
	
	public Node getRoot(){
		return root;
	}
	
	

}
