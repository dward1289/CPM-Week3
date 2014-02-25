package com.parse.starter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class ParseStarterProjectActivity extends Activity {
	Context context;
	Spinner theSpinner;
	String DATED;
	String EXERCISE;
	String TIME;
	int MILES;
	String RYE;
	ParseQueryAdapter<ParseObject> adapter;
	
			
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		
		  //Adapter gets data from database.
		  adapter = new ParseQueryAdapter<ParseObject>(this, "TheLog");
		  adapter.setTextKey("Date");
		  
		  //Populate list view
		  final ListView listView = (ListView) findViewById(R.id.listview);
		  listView.setAdapter(adapter);
		  //Add onClick listener to item
		  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			       public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
			                               long id) {
			    	   ParseObject parseObject = adapter.getItem(position);
			    	   
			    	   DATED = parseObject.getString("Date");
			    	   EXERCISE = parseObject.getString("Exercise");
			    	   TIME = parseObject.getString("Time");
			    	   MILES = parseObject.getInt("Miles");
			    	   RYE = parseObject.getString("RYE");
			    	   
			    	   //Display alert for edit and delete options
			    	   AlertDialog.Builder builder = new AlertDialog.Builder(ParseStarterProjectActivity.this);
		                builder.setTitle("ActiveList");
		                builder.setMessage(DATED+" \n"+"Exercise: "+EXERCISE+"\n"+"Time Completed: "+TIME+"\n"+"Miles Completed: "+MILES+"\n"+"Rated: "+RYE);
		                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface arg0, int arg1) {
		                        //Edit item
		                    }
		                });
		                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface arg0, int arg1) {
		                        //Delete item
		                    }
		                });
		                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface arg0, int arg1) {

		                    }
		                });
		                builder.show();
			    	   
			       }
			  });

	}
	
	public void onQuery(View v){
		Log.i("BUTTON CLICK", "BUTTON WORKING");
	}
   
	
	
}
