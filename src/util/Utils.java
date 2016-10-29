package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import model.Environment;
import model.Executable;
import model.executable.Variable;

public class Utils {
	
	public static final double EPSILON = 0.1;
	
	public static Object[] reverse(Object[] arr) {
        List<Object> list = Arrays.asList(arr);
        Collections.reverse(list);
        return list.toArray();
    }
	
	/**
	 * Two doubles are equal if their absolute different is bounded
	 * by a small EPSILON
	 * @param d1
	 * @param d2
	 * @return
	 */
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
	
	/**
	 * Match Variable using name. Return null if not found. 
	 * @param gvars
	 * @param name
	 * @return
	 */
	public static Variable listContains(Environment<Variable> gvars, String name) {
		return listContains(gvars.getImmutableValues(), name);
	}
	
	/**
	 * append to the StringBuilder the String representation of
	 * each element in the list, separated using delimiter
	 * @param sb
	 * @param list
	 * @param delimiter
	 */
	public static void appendList(StringBuilder sb, List<Executable> list, String delimiter) {
		for(Executable e : list) {
			sb.append(e.toString()).append(delimiter);
		}
	}
	
	/**
	 * Collapse multiple consecutive
	 * {white spaces, tabs, new lines} into one white space
	 * @param script
	 * @return
	 */
	public static String senitize(String script) {
		return script.trim()
					 .replaceAll(" +", " ")
					 .replaceAll("\t+", " ")
					 .replaceAll("\n+", " ");
	}
}
