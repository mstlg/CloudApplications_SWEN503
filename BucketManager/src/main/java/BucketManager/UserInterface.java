package BucketManager;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;

public class UserInterface {
	
	public static void setUpStartScreen(){
		System.out.println("|------ Welcome to the Google Cloud Bucket Management System ------|");
	}

	public static String chooseSdkApi(){

		System.out.println("\n   What would you like to do today? Use the 'SDK' or the 'API'?");

		boolean keep_looping = false;

		while(!keep_looping){
			String user_input = Keyboard.readInput().toLowerCase();
			if (user_input.equals("sdk")|| user_input.equals("api")){
				return user_input;
			} else {
				System.out.println("This is not a valid selection.");
			}
		}
		return null;
	}

	public static String getCredentials(){

		System.out.println("\nPlease enter the path of your credentials file on your computer:");
		String credentials_path = Keyboard.readInput();
		return credentials_path;

	}

	public static String provideProject(){
		System.out.println("\nPlease enter the name of your project:");
		String project = Keyboard.readInput();
		return project;
	}

	public static StorageClass setStorageClass(){
		System.out.println("\nWhich storage class would you like to use? (Regional, Multi-Regional, Nearline, Coldline)");
		boolean keep_looping = false;
		StorageClass sc;
		
		while(!keep_looping){
			String storage_class = Keyboard.readInput().toUpperCase();
			if (storage_class.equals("REGIONAL")){
				sc = StorageClass.REGIONAL;
				return sc;
			} else if (storage_class.equals("MULTI-REGIONAL")){
				sc = StorageClass.MULTI_REGIONAL;
				return sc;
			} else if(storage_class.equals("NEARLINE")){
				sc = StorageClass.NEARLINE;
				return sc;
			} else if (storage_class.equals("COLDLINE")){
				sc = StorageClass.COLDLINE;
				return sc;
			} else {
				System.out.println("This is not a valid selection.");
			}
		}
		return null;
	}

	public static String setLocation(){
		System.out.println("\nWhich location would you like to use? (australia-southeast1 is recommended)");
		String location = Keyboard.readInput();
		return location;
	}

	public static String menuSelection(){
		System.out.println("Please make a selection:");
		System.out.println("\n1.Create Bucket \n2.Split Bucket \n3.Merge Buckets");
		String selection = Keyboard.readInput();
		boolean keep_looping = false;
		while (!keep_looping){
			if (selection.equals("1") || selection.equals("2") || selection.equals("3")){
				return selection;
			} else {
				System.out.println("Please chose an option between 1, 2, or 3.");
			}
		}
		return null;

	}
	
	public static String bucketName(){
		System.out.println("Enter the name of the bucket: ");
		String bucket_name = Keyboard.readInput();
		return bucket_name;
	}

	
	public static void main(String[] args) {

		setUpStartScreen();
		//String version = chooseSdkApi(); //Returns string of the selection	
		//String credentials_path = getCredentials(); //Returns a string of the credentials path
		//String project = provideProject();//Returns a string of the project
		//String storage_class = setStorageClass();
		//String location = setLocation();

		//Hard coded inputs for testing purposes
		String version = "sdk";
		String credentials_path = "C:/Users/stlaumade/Desktop/SWEN 503/Credentials.json";
		String project = "sinuous-ally-187922";
		StorageClass storage_class = StorageClass.REGIONAL;
		String location = "australia-southeast1";

		System.out.println("\nThese are your selected options:\nVersion: " + version);
		System.out.println("Credentials Location: " + credentials_path);
		System.out.println("Project: " + project);
		System.out.println("Storage Class: " + storage_class);
		System.out.println("Location: " + location + "\n");

		if (version.equals("sdk")){
			SDK sdk = new SDK(credentials_path, project, location, storage_class);
			try {
				Storage s = sdk.connectToGoogle();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} 
			
			String what_to_do = menuSelection();
			if (what_to_do.equals("1")){
				String bucket_name = bucketName();
				sdk.createBucket(bucket_name);
				sdk.uploadFile(bucket_name, "Blob_Test", "Hello");
				
			} else if (what_to_do.equals("2")){
				//Split bucket
			} else if (what_to_do.equals("3")){
				//Merge bucket 
			}

		}
	}

}
