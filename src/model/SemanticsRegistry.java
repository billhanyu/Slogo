package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import exception.SyntacticErrorException;
import model.executable.ProcedureImpl;

public class SemanticsRegistry {
	
	public static final String TOKEN_DICT = "resources/tokens";
	public static final String SPACE_REGEX = "\\s+";
	public static final String TO = "to ";
	
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
			int argsOpen = script.indexOf("[", toIndex);
			int argsClose = script.indexOf("]", toIndex);
			if (argsOpen < 0 || argsClose < 0)
				throw new SyntacticErrorException();
			String token = script.substring(toIndex + TO.length(), argsOpen).trim();
			if (isStdCommand(token) || isConstant(token) || isVariable(token))
				throw new SyntacticErrorException();
			String params = script.substring(argsOpen + 1, argsClose).trim().replaceAll("( )+", " ");
			this.putNumParam(
					token, 
					params.isEmpty()? 0 : params.split(SPACE_REGEX).length
			);
			script = script.substring(toIndex + 1);
		}
	}
	
	public boolean isStdCommand(String token) {
		return stdCmds.contains(token);
	}
	
	public boolean isCustomCommand(String token) {
		// TODO (cx15): IMPL
		return true;
	}
	
	public boolean isConstant(String token) {
		return token.matches(lexicon.getString("constant.regex"));
	}
	
	public boolean isVariable(String token) {
		return token.matches(lexicon.getString("variable.regex"));
	}
	
	public void putNumParam(String token, int argc) {
		name2Argc.put(token, argc);
	}
	
	/**
	 * return -1 if no mapping for key token
	 * @param token
	 * @return
	 */
	public int getNumParam(String token) {
		if (name2Argc.get(token) == null) {
			return -1;
		}
		return name2Argc.get(token);
	}
	
	public void putImpl(String token, ProcedureImpl impl) {
		name2Impl.put(token, impl);
	}
	
	/**
	 * return null if no mapping for key token
	 * @param token
	 * @return
	 */
	public ProcedureImpl getImpl(String token) {
		return name2Impl.get(token);
	}
}
