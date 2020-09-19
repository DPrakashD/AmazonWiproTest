package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private Properties properties;
	private final String propertyFilePath= "src\\test\\resources\\Config\\config.properties";
	private static ConfigFileReader configFileReader = new ConfigFileReader();	
	 
	 private ConfigFileReader(){
	         BufferedReader reader;
	         try {
	         reader = new BufferedReader(new FileReader(propertyFilePath));
	         properties = new Properties();
	         try {
	         properties.load(reader);
	         reader.close();
	         } catch (IOException e) {
	         e.printStackTrace();
	         }
	         } catch (FileNotFoundException e) {
	         e.printStackTrace();
	         throw new RuntimeException("The properties file not found at " + propertyFilePath);
	         } 
	         }
	 
	 public static ConfigFileReader getConfigFileReader( ) {
	     return configFileReader;}
	 
	
	 public String getUsername(){
		 String userName = properties.getProperty("amazonUsername");
		 if(userName!= null) return userName;
		 else throw new RuntimeException("amazon username is not available in the" + ".properties file."); 
		 }
	
	 public String getPassword(){
		 String passWord = properties.getProperty("amazonPassword");
	 	 if(passWord!= null) return passWord;
		 else throw new RuntimeException("amazon password is not available in the"+ ".properties file."); 
		 }
	 
	 public String getPackageName(){
		 String packageName = properties.getProperty("packageName");
		 if(packageName!= null) return packageName;
		 else throw new RuntimeException("package name is not available in the"+ ".properties file."); 
		 }
	 
	 public String getAppActivity(){
		 String appActivity = properties.getProperty("activityName");
		 if(appActivity!= null) return appActivity;
		 else throw new RuntimeException("app activity name is not available in the"+ ".properties file."); 
		 }
	 
	 public String getDeviceName(){
		 String deviceName = properties.getProperty("deviceName");
		 if(deviceName!= null) return deviceName;
		 else throw new RuntimeException("device name is not available in the"+ ".properties file."); 
		 }

	 public String getProductName(){
		 String ProductName = properties.getProperty("productName");
		 if(ProductName!= null) return ProductName;
		 else throw new RuntimeException("Product name is not available in the"+ ".properties file."); 
		 }
	}
