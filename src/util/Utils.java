package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.GlobalVariables;
import model.executable.Variable;

public class Utils {
	
	public static final double EPSILON = 0.1;
	
	public static Object[] reverse(Object[] arr) {
        List<Object> list = Arrays.asList(arr);
        Collections.reverse(list);
        return list.toArray();
    }
	
	public static boolean doubleEqual(double d1, double d2) {
		return (Math.abs(d1 - d2) < Utils.EPSILON);
	}
	
	/**
	 * Match Variable using name. Return null if not found. 
	 * @param list
	 * @param name
	 * @return
	 */
	public static Variable listContains(Collection<Variable> list, String name) {
		for (Variable var : list) {
			if (var.getName().equals(name)) {
				return var;
			}
		}
		return null;
	}
	
	public static Variable listContains(GlobalVariables gvars, String name) {
		return listContains(gvars.getAll(), name);
	}

}
