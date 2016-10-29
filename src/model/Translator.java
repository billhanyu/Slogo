package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author billyu
 * translate the tokens from other languages to English
 * set current language
 */
public class Translator {
	
	public static final String LANGUAGES_PATH = "resources.languages/";
	private Map<String, String> lang2Eng;
	private Map<String, String> eng2Lang;
	private ResourceBundle englishBundle;
	
	public Translator() {
		englishBundle = ResourceBundle.getBundle(LANGUAGES_PATH + "English");
		lang2Eng = new HashMap<>();
		eng2Lang = new HashMap<>();
		setLanguage("English");
	}
	
	/**
	 * from a multi-language token, use lang2Std to get the standard name
	 * then use the English resource bundle to translate to English
	 * since our tokens.properties is written with English
	 * @param language
	 */
	public void setLanguage(String language) {
		ResourceBundle langBank = ResourceBundle.getBundle(LANGUAGES_PATH + language);
		lang2Eng = new HashMap<>();
		eng2Lang = new HashMap<>();
		for (String cmdName : langBank.keySet()) {
			String cmdTokens = langBank.getString(cmdName);
			cmdTokens = cmdTokens.replace("\\", "");
			String[] tokens = cmdTokens.split("\\|");
			String englishToken = englishBundle.getString(cmdName).split("\\|")[0];
			englishToken = englishToken.replace("\\", "");
			for (String token : tokens) {
				lang2Eng.put(token, englishToken);
			}
			eng2Lang.put(cmdName, tokens[0]);
		}
	}
	
	/**
	 * @param tokenString the token to be translated
	 * @return token translated to English
	 * if the token is not found in the lang2eng library, return the original token
	 */
	public String translateToken(String tokenString) {
		if (!lang2Eng.containsKey(tokenString)) {
			return tokenString;
		}
		return lang2Eng.get(tokenString);
	}
	
	/**
	 * @param tokenString the token to be translated from English to preset language
	 * @return token translated to the original language
	 * since eng2Lang chooses the first one in the values set for a cmdName
	 * the foreign language token returned is arbitrary
	 */
	public String reverseTranslateToken(String tokenString) {
		if (!eng2Lang.containsKey(tokenString)) {
			return tokenString;
		}
		return eng2Lang.get(tokenString);
	}
	
	/**
	 * Translate into English a given script token-wise using delim as delimiter
	 * @param script
	 * @param delim
	 * @return
	 */
	public String translate(String script, String delim) {
		List<String> tokens = Arrays.asList(script.split(delim));
		return tokens.stream()
				.map(token -> translateToken(token))
				.collect(Collectors.joining(" "));
	}
	
	/**
	 * Translate back to the user selected language a given script
	 * token-wise using delim as delimiter
	 * @param script
	 * @param delim
	 * @return
	 */
	public String translateBackUserLang(String script, String delim) {
		List<String> tokens = Arrays.asList(script.split(delim));
		return tokens.stream()
				.map(token -> reverseTranslateToken(token))
				.collect(Collectors.joining(" "));
	}
	
}
