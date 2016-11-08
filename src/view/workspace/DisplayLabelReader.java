package view.workspace;

import java.util.ResourceBundle;

/**
 * @author Chalena Scholl
 * This creates a resource bundle reader, given a properties file to read from.
 */
public class DisplayLabelReader {
	
	 private ResourceBundle myResources;
	 public String myFile;
	 
	 public DisplayLabelReader(String fileLocation){
		 myResources = ResourceBundle.getBundle(fileLocation);
		 myFile = fileLocation;
	 }
	 
	 public String getLabel(String value){
		 return myResources.getString(value);
	 }
	 

}
