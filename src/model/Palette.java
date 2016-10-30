package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.paint.Color;

public class Palette {

	private Map<Integer, Color> colors;
	
	public Palette() {
		colors = new HashMap<>();
		colors.put(1, Color.BLACK);
		colors.put(2, Color.WHITE);
		colors.put(3, Color.RED);
		colors.put(4, Color.ORANGE);
		colors.put(5, Color.YELLOW);
		colors.put(6, Color.GREEN);
		colors.put(7, Color.CYAN);
		colors.put(8, Color.BLUE);
		colors.put(9, Color.PURPLE);
	}
	
	public Color getColor(int index) {
		return colors.get(index);
	}
	
	public Collection<Integer> getAllIndices() {
		return colors.keySet();
	}
	
	public Collection<Color> getAllColors() {
		return colors.values();
	}
	
	public void setColor(int index, Color color) {
		colors.put(index, color);
	}
	
	public int findColorInPalette(Color color) {
		for (Entry<Integer, Color> e : colors.entrySet()) {
			if (color.equals(e.getValue())) {
				return e.getKey();
			}
		}
		return 0;
	}
	
}
