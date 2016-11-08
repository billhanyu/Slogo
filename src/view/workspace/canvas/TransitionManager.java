package view.workspace.canvas;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.SequentialTransition;


/**
 * @author Chalena Scholl
 */
public class TransitionManager {
	
	private Map<Integer, SequentialTransition> transitions;
	
	public TransitionManager() {
		transitions = new HashMap<>();
	}
	
	public void clear() {
		transitions.values().stream()
			.forEach(transition -> transition.getChildren().clear());
	}
	
	public SequentialTransition get(int index) {
		if (!transitions.containsKey(index)) {
			transitions.put(index, new SequentialTransition());
		}
		return transitions.get(index);
	}

	public void play() {
		transitions.values().stream().forEach(transition -> transition.play());
	}

	public void pause() {
		transitions.values().stream().forEach(transition -> transition.pause());
	}
	
	public void stop() {
		transitions.values().stream().forEach(transition -> transition.stop());
	}

}
