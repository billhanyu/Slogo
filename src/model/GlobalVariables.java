package model;

import model.executable.Variable;

public class GlobalVariables extends Environment<Variable> {

	public void addAll(GlobalVariables gvars) {
		if (this.getValues().isEmpty()) {
			this.getValues().addAll(gvars.getValues());
			return;
		}
		for (Variable newElement : gvars) {
			for (Variable existing : this.getImmutableValues()) {
				if (newElement.getName().equals(existing.getName())) {
					newElement.setExpression(existing);
				} else {
					add(newElement);
				}
			}
		}
	}
	
}
