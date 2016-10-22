package model;

public class Token {
	
	private String token;
	private SemanticsRegistry semanticsRegistry;
	
	public Token(String token, SemanticsRegistry semanticsRegistry) {
		this.token = token;
		this.semanticsRegistry = semanticsRegistry;
	}
	
	public boolean isStdCommand() {
		return semanticsRegistry.getStandardCommands().contains(token);
	}
	
	public boolean isCustomCommand() {
		return !isStdCommand() && semanticsRegistry.getNumParam(this) >= 0;
	}
	
	public boolean isConstant() {
		return matches("constant.regex");
	}
	
	public boolean isVariable() {
		return matches("variable.regex");
	}
	
	public boolean isOpenBracket() {
		return matches("openBracket");
	}
	
	public boolean isCloseBracket() {
		return matches("closeBracket");
	}
	
	private boolean matches(String query) {
		return token.matches(semanticsRegistry.getLexicon().getString(query));
	}
	
	@Override
	public String toString() {
		return token;
	}
}
