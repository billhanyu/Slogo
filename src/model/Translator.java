package model;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author billyu
 * translate the tokens from other languages to English
 * set current language
 */
public class Translator {
	
	public static final String LANGUAGES_PATH = "resources.languages/";
	private Map<String, String> lang2Eng;
	private ResourceBundle englishBundle;
	
	public Translator() {
		englishBundle = ResourceBundle.getBundle(LANGUAGES_PATH + "English");
		lang2Eng = new HashMap<>();
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
		for (String cmdName : langBank.keySet()) {
			String cmdTokens = langBank.getString(cmdName);
			cmdTokens = cmdTokens.replace("\\", "");
			String[] tokens = cmdTokens.split("\\|");
			String englishToken = englishBundle.getString(cmdName).split("\\|")[0];
			englishToken = englishToken.replace("\\", "");
			for (String token : tokens) {
				lang2Eng.put(token, englishToken);
			}
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
	
}
