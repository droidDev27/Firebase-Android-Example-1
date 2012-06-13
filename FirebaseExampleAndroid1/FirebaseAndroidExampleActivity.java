package yourdroid.firebaseexamplepackage.namespace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FirebaseAndroidExampleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        /*
         *   Put new username in Firebase leaderboard data on win	   
         */
		try {
			   String newUsername = "IjustWon"; // new username
			   String pointValue = "10"; //point value to put in Firebase 
			   String firebaseAddtoString = "http://gamma.firebase.com/EnterYourFirebaseHere/Winners/" +newUsername+ ".json"; // use your Firebase URL
			   HttpClient http = new DefaultHttpClient(); 
			    HttpPut putmethod = new HttpPut(firebaseAddtoString); //http put
			    putmethod.setEntity(new StringEntity(pointValue)); 
			    HttpResponse response = http.execute(putmethod); 

	
		 } catch (MalformedURLException e) {
			  // TODO Auto-generated catch block
		    	e.printStackTrace();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } 
        

        
        
	    /* 
	     * Get  Firebase leaderboard data at your Firebase URL (returned as JSON object)
	     */
       	HttpClient client = new DefaultHttpClient();
       	HttpGet request = new HttpGet("http://gamma.firebase.com/EnterYourFirebaseHere/Winners.json"); //use your Firebase URL
       	HttpResponse response;
		try {
			response = client.execute(request);
			
	       	// Get the response
	       	BufferedReader rd = new BufferedReader
	       		(new InputStreamReader(response.getEntity().getContent()));
	       			
	       	String line = "";
	 	   TextView textView22=(TextView)findViewById(R.id.leaderboardDataTextView);  //set the response JSON in a textview

	       	while ((line = rd.readLine()) != null) {
	       		
	       		textView22.setText(line);
	       	}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
        
    }
}