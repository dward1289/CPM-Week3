package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class ParseStarterProjectActivity extends Activity {
	
	RadioGroup timeAP;
	RadioGroup startFinish;
	RadioButton radioBtnAP;
	RadioButton radioBtnSF;
	Spinner theSpinner;
	
			
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		
		ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, "TheSchedule");
		  adapter.setTextKey("Day");
		  ListView listView = (ListView) findViewById(R.id.listview);
		  listView.setAdapter(adapter);
	}
	
}
