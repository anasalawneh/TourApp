package virginia.edu.techthesis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TourAppDemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        
        SiteManager siteManager = SiteManager.getInstance();
        
        //grab the gui...
        TextView tv = (TextView)findViewById(R.id.tour_title);
        Button siteButton = (Button) findViewById(R.id.siteButton);
        Button tourButton = (Button) findViewById(R.id.tourButton);
        Button mapButton = (Button)findViewById(R.id.mapButton);
        
        tourButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tourListIntent = new Intent(TourAppDemoActivity.this, TourListActivity.class);
                startActivity(tourListIntent);
            }
        });
        

        siteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent siteListIntent = new Intent(TourAppDemoActivity.this, SiteListActivity.class);
                startActivity(siteListIntent);
            }
        });
        
        mapButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(TourAppDemoActivity.this, MapViewActivity.class);
				startActivity(i);
				
			}
        	
        });
    }
}
    

