package model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import view.floating.AbstractPublisher;

public class WorkspaceState extends AbstractPublisher {
	
	private static final Color DEFAULT_BACKGROUND = Color.LIGHTBLUE;
	private static final String DEFAULT_LANGUAGE = "English";
	private static final int DEFAULT_STARTING = 1;
	
	private Color backgroundColor;
	private String language;
	private int startingNumber;
	private Map<Integer, Image> images;
	
	public WorkspaceState() {
		setBackgroundColor(DEFAULT_BACKGROUND);
		setLanguage(DEFAULT_LANGUAGE);
		setStartingNumber(DEFAULT_STARTING);
		images = new HashMap<>();
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.notifySubscribers();
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getStartingNumber() {
		return startingNumber;
	}

	public void setStartingNumber(int startingNumber) {
		this.startingNumber = startingNumber;
	}
	
	public Image getImage(int id) {
		return images.get(id);
	}
	
	public void setImage(int id, Image image) {
		images.put(id, image);
	}
	
}
