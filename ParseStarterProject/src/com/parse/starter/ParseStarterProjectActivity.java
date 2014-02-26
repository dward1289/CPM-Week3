package com.parse.starter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ParseStarterProjectActivity extends Activity {
	Context context;
	Spinner theSpinner;
	String DATED;
	String EXERCISE;
	String TIME;
	int MILES;
	String RYE;
	String rateSpinner;
	String activitySpinner;
	ParseQueryAdapter<ParseObject> adapter;
	ParseQueryAdapter<ParseObject> adapter2;
	ArrayList<ParseObject> list;
	ArrayList<ParseObject> queryList;
	ListView listView;
	ParseObject parseObject;
	
			
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		
		  //Adapter gets data from database.
		  adapter = new ParseQueryAdapter<ParseObject>(this, "TheLog");
		  adapter.setTextKey("Date");
		  
		  //Populate list view
		  listView = (ListView) findViewById(R.id.listview);
		  listView.setAdapter(adapter);
		  //Add onClick listener to item
		  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			       public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
			                               long id) {
			    	   parseObject = adapter.getItem(position);
			    	   
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
		                    	onEdit();
		                    }
		                });
		                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface arg0, int arg1) {
		                        //Delete item
		                    	parseObject.deleteInBackground();
		                    	adapter.loadObjects();
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
		//Spinner items
		Spinner theSpinnerA = (Spinner)findViewById(R.id.spinnerActive);
		Spinner theSpinnerR = (Spinner)findViewById(R.id.spinnerRate);
		//Selected item from spinners
		rateSpinner = theSpinnerR.getSelectedItem().toString();
		activitySpinner = theSpinnerA.getSelectedItem().toString();
	
		adapter.clear();
		theCall();
		
	}
   
	//Query the data.
	public void theCall(){
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("TheLog");
		query.whereEqualTo("RYE", rateSpinner);
		query.whereEqualTo("Exercise", activitySpinner);
		query.findInBackground(new FindCallback<ParseObject>() {
		   
			@Override
			public void done(List<ParseObject>objects, ParseException e) {
				if (e == null) {
					for (ParseObject object : objects) {

						//Display list of queried objects in listview.
						String newItem = object.getString("Date");
						
						queryList.add(object);
						 
					}
				    } else {
				      // something went wrong
				    }
				  }
				});	
	}
	
	//Navigate to editing screen
	public void onEdit(){
		Intent i = new Intent(this, EditData.class);
    	i.putExtra("DATED", DATED);
    	i.putExtra("EXERCISE", EXERCISE); 
    	i.putExtra("TIME", TIME); 
    	i.putExtra("MILES", MILES);
    	i.putExtra("RYE", RYE); 
    	startActivity(i);
	}
}
