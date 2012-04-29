package virginia.edu.techthesis;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import virginia.edu.techthesis.SiteListActivity.PlaceSearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SitePageActivity extends Activity {
	ImageView image;
	TextView title,description;
	Site siteObject;	
	Button webButton;
	String url;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sitepage);
        
        //get the site object...
        Intent intent = getIntent();
        Site siteObject = (Site) intent.getParcelableExtra("site");

        //get the gui from the layout files...
    	title = (TextView)findViewById(R.id.title);
    	image = (ImageView)findViewById(R.id.image);
    	description = (TextView)findViewById(R.id.description);
        webButton = (Button)findViewById(R.id.webPageButton);
    	
        //perform a place detail search request...
        
    	//set the page...
    	title.setText(siteObject.getName());
    	description.setText(siteObject.getDescription());
    	
    	int imageId = getResources().getIdentifier(siteObject.getName().toLowerCase(), "drawable", getPackageName());
    	if(imageId != 0){
    		image.setImageResource(imageId);	
    	}
    	else{
    		new loadPicture().execute(siteObject.icon);
    	}
    	/*String imageString = siteObject.getImageFile();

    	int imageId = getResources().getIdentifier(imageString, "drawable", getPackageName());
    	image.setImageResource(imageId);*/
    	new PlaceDetailsSearch(new ArrayList<NameValuePair>()).execute();
    	
    	webButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SitePageActivity.this, WebPageViewActivity.class);
				i.putExtra("url", url);
				startActivity(i);				
			}
    		
    	});
    }
    
    private class loadPicture extends AsyncTask<String, Void, Drawable>{

		@Override
		protected Drawable doInBackground(String... params) {
			String url = params[0];
			Drawable drawable = LoadImageFromWeb(url);
			return drawable;
		}
		
		protected void onPostExecute(Drawable d){
			image.setImageDrawable(d);
		}
    	
    }
    
  //thread to perform place detail search request
    class PlaceDetailsSearch extends AsyncTask<String, Void, String> {

		// Fill in the API key you want to use.
		private static final String API_KEY = "AIzaSyAk4Ur6RglVpcgJsaz7yE0AADUz9rElO38";

		// The different Places API endpoints.
		private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
		
		private static final boolean PRINT_AS_STRING = false;

		ArrayList<NameValuePair> nameValuePairs;
		HttpClient client;
		String result;

		public PlaceDetailsSearch(ArrayList<NameValuePair> nvp) {
			this.nameValuePairs = nvp;
			this.client = new DefaultHttpClient();
		}

		public String doSearch() {
			String line;
			try {
				String request_url = PLACES_SEARCH_URL + "reference=" + siteObject.getReference()
						+ "&sensor=true&key=" + API_KEY;
				
				HttpPost httpPost = new HttpPost(request_url);
				HttpResponse httpResponse = client.execute(httpPost);
				HttpEntity entity = httpResponse.getEntity();
				InputStream is = entity.getContent();

				// get response string...
				BufferedReader bf = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				while ((line = bf.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				result = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public String parsePlace(String str) {
			try {
				JSONObject resultObject = new JSONObject(str);
				String url = resultObject.getString("url");
				return url;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}

		}

		@Override
		protected String doInBackground(String... params) {
			PlaceDetailsSearch ps = new PlaceDetailsSearch(nameValuePairs);
			String result = ps.doSearch();
			String siteurl = ps.parsePlace(result);
			url = siteurl;
			return url;
		}

		protected void onPostExecute(String result) {
			//places = result;
			//addSiteButtons(siteList);
		}
	}
    
  private Drawable LoadImageFromWeb(String url){
	  try {
		InputStream is = (InputStream)new URL(url).getContent();
		Drawable d = Drawable.createFromStream(is, "srcName");
		return d;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	} 
	  
  }
    
}