package BucketManager;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.StorageOptions.Builder;

public class SDK {

	String credentials_path;
	String project_id;
	String location;
	StorageClass storage_class;
	Storage storage;

	public SDK(String credentials, String p_id, String loc, StorageClass s_class){
		credentials_path = credentials;
		project_id = p_id;
		storage_class = s_class;
		location = loc;
	}

	public Storage connectToGoogle() throws FileNotFoundException, IOException{

		Builder builder = StorageOptions.newBuilder();

		builder.setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(credentials_path)));

		builder.setProjectId(project_id);

		storage = builder.build().getService();

		return storage;
	}

	public Bucket createBucket(String bucket_name){

		try{
			Bucket bucket = storage.create(BucketInfo.newBuilder(bucket_name).setStorageClass(StorageClass.REGIONAL).setLocation(location).build());
			System.out.printf("\nBucket %s created.%n", bucket.getName());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void uploadFile(String bucket_name, String blobName, String message){

		Bucket bucket = storage.get(bucket_name);
		try{
			InputStream content = new ByteArrayInputStream(message.getBytes());
			Blob blob = bucket.create(blobName, content, "text/plain");
			System.out.printf("\nBlob %s created.%n", blob.getName());
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
