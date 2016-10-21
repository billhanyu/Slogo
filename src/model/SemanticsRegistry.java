package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import exception.SyntacticErrorException;
import model.executable.ProcedureImpl;

public class SemanticsRegistry {
	
	public static final String TOKEN_DICT = "resources/tokens";
	public static final String PROP_CLASS = ".class";
	public static final String PROP_ARGC = ".argc";
	public static final String SPACE_REGEX = "\\s+";
	
	public static final String TO = "to ";
	public static final String GOTO = "goto";
	public static final String OPEN_BRACKET = "[";
	public static final String CLOSE_BRACKET = "]";
	public static final int GOTO_DIFF_TO = 2;
	
	private ResourceBundle lexicon;
	private Set<String> stdCmds;
	private Map<String, ProcedureImpl> name2Impl;
	private Map<String, Integer> name2Argc;
	
	public SemanticsRegistry() {
		lexicon = ResourceBundle.getBundle(TOKEN_DICT);
		stdCmds = new HashSet<>();
		lexicon.getString("stdcmd");
		for (String stdToken : lexicon.getString("stdcmd").split(SPACE_REGEX)) {
			stdCmds.add(stdToken);
		}
		name2Impl = new HashMap<>();
		name2Argc = new HashMap<>();
	}
	
	public void register(String script)
			throws SyntacticErrorException {
		int toIndex = -1;
		while( (toIndex = script.indexOf(TO)) != -1) {
			if (script.indexOf(GOTO) + GOTO_DIFF_TO != toIndex) {
				int argsOpen = script.indexOf(OPEN_BRACKET, toIndex);
				int argsClose = script.indexOf(CLOSE_BRACKET, toIndex);
				if (argsOpen < 0 || argsClose < 0)
					throw new SyntacticErrorException();
				String tokenString = script.substring(toIndex + TO.length(), argsOpen).trim();
				Token token = new Token(tokenString, this);
				if (token.isStdCommand() || token.isConstant() || token.isVariable())
					throw new SyntacticErrorException();
				String params = script.substring(argsOpen + 1, argsClose).trim().replaceAll("( )+", " ");
				this.putNumParam(
						tokenString, 
						params.isEmpty()? 0 : params.split(SPACE_REGEX).length
				);
			}
			script = script.substring(toIndex + 1);
		}
	}
	
	public Set<String> getStandardCommands() {
		return stdCmds;
	}
	
	public ResourceBundle getLexicon() {
		return lexicon;
	}
	
	private void putNumParam(String token, int argc) {
		name2Argc.put(token, argc);
	}
	
	public int getNumParam(String token) {
		if (stdCmds.contains(token)) {
			return Integer.parseInt(lexicon.getString(token + PROP_ARGC));
		}
		if (name2Argc.get(token) == null) {
			return -1;
		}
		return name2Argc.get(token);
	}
	
	/**
	 * return -1 if no mapping for key token
	 * @param token
	 * @return
	 */
	public int getNumParam(Token token) {
		return getNumParam(token.toString());
	}
	
	public String getClass(Token token) {
		return lexicon.getString(token + PROP_CLASS);
	}
	
	public void putImpl(Token token, ProcedureImpl impl) {
		name2Impl.put(token.toString(), impl);
	}
	
	/**
	 * return null if no mapping for key token
	 * @param token
	 * @return
	 */
	public ProcedureImpl getImpl(Token token) {
		return name2Impl.get(token.toString());
	}
}
