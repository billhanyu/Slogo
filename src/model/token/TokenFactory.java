package model.token;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exception.ReflectionFoundNoMatchesException;
import exception.UnrecognizedIdentifierException;
import model.SemanticsRegistry;
import util.ReflectionUtils;

public class TokenFactory {
	
	public static final String SPACE_REGEX = "\\s+";
	public static final String PROP_CLASS = ".class";
	public static final String PROP_REGEX = ".regex";
	
	private SemanticsRegistry semanticsRegistry;
	private List<String> regexMatchable;
	
	public TokenFactory(SemanticsRegistry semanticsRegistry) {
		this.semanticsRegistry = semanticsRegistry;
		regexMatchable = new ArrayList<>();
		regexMatchable = Arrays.asList(
				semanticsRegistry.getLexicon()
				.getString("regexMatchable").trim().split(SPACE_REGEX)
		);
	}

	public Token build(String primitive)
			throws UnrecognizedIdentifierException {
		String type = getType(primitive);
		if (type == null)
			throw new UnrecognizedIdentifierException();
		String className = semanticsRegistry.getLexicon().getString(type + PROP_CLASS);
		Token ret = null;
		try {
			ret = (Token) ReflectionUtils.newInstanceOf(className, primitive, semanticsRegistry);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| ClassNotFoundException | ReflectionFoundNoMatchesException e) {
			throw new UnrecognizedIdentifierException();
		}
		return ret;
	}
	
	private String getType(String primitive) {
		for (String type : regexMatchable) {
			if (matches(primitive, type)) {
				return type;
			}
		}
		if (semanticsRegistry.getStandardCommands().contains(primitive)) 
			return "standardCommand";
		else return "customCommand";
	}
	
	private boolean matches(String primitive, String query) {
		return primitive.matches(semanticsRegistry.getLexicon().getString(query + PROP_REGEX));
	}
}
