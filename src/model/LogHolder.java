package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogHolder {
	
	private Map<Integer, TurtleLog> turtleLogs;
	private WorkspaceState workspaceState;
	private Set<Integer> activeTurtles;
	
	public LogHolder() {
		turtleLogs = new HashMap<>();
		workspaceState = new WorkspaceState();
		activeTurtles = new HashSet<>();
		setActiveIDs(Arrays.asList(0));
	}
	
	public void setActiveIDs(Collection<Integer> actives) {
		activeTurtles.clear();
		for (int activeID : actives) {
			if (!turtleLogs.containsKey(activeID)) {
				turtleLogs.put(activeID, new TurtleLog());
			}
			activeTurtles.add(activeID);
		}
	}
	
	public Collection<Integer> getActiveIDs() {
		return activeTurtles;
	}
	
	public TurtleLog getTurtleLog(int id) {
		return turtleLogs.get(id);
	}
	
	public Collection<TurtleLog> getActiveLogs() {
		return activeTurtles
				.stream()
				.map(id -> turtleLogs.get(id))
				.collect(Collectors.toList());
	}
	
	public Collection<Integer> getAllIDs() {
		return turtleLogs.keySet();
	}
	
	public Collection<TurtleLog> getAllLogs() {
		return turtleLogs.values();
	}
	
	public WorkspaceState getWorkspaceState() {
		return workspaceState;
	}

}
