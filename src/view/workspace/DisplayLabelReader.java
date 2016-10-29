package view.workspace;

import java.util.ResourceBundle;

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
