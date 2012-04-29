package virginia.edu.techthesis;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SitePageActivity extends Activity {
	ImageView image;
	TextView title,description;
	Site siteObject;	
	
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
    }
    
    private class loadPicture extends AsyncTask<String, Void, Drawable>{

		@Override
		protected Drawable doInBackground(String... params) {
			String url = params[0];
			Drawable drawable = LoadImageFromWeb(url);
			return drawable;
		}
		
		protected void onPostExecute(Drawable d){
//			image.setImageDrawable(d);
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