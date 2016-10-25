package view.floating;

import java.lang.reflect.InvocationTargetException;

import controller.Controller;

public class FloatingViewManager {

	private Controller controller;
	
	public FloatingViewManager(Controller controller) {
		this.controller = controller;
	}
	
	public void show(Class<? extends FloatingView> viewType) {
		try {
			FloatingView view = viewType
					.getDeclaredConstructor(Controller.class)
					.newInstance(controller);
			view.show();
		} catch (InstantiationException | IllegalAccessException | 
				IllegalArgumentException | InvocationTargetException | 
				NoSuchMethodException | SecurityException e) {
			// will never happen
			e.printStackTrace();
		}
	}
	
}
