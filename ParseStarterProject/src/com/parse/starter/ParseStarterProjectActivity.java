package com.parse.starter;

import java.util.ArrayList;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.parse.ParseAnalytics;
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
	String TheID;
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
			    	   parseObject = (ParseObject) listView.getItemAtPosition(position);
			 
			    	   DATED = parseObject.getString("Date");
			    	   EXERCISE = parseObject.getString("Exercise");
			    	   TIME = parseObject.getString("Time");
			    	   MILES = parseObject.getInt("Miles");
			    	   RYE = parseObject.getString("RYE");
			    	   TheID = parseObject.getObjectId();
			    	   
			    	   
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
		                    	Toast.makeText(getApplicationContext(), "Log has been deleted", Toast.LENGTH_SHORT).show();
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
		ParseQueryAdapter<ParseObject> adapter2 =
				new ParseQueryAdapter<ParseObject>(this, new ParseQueryAdapter.QueryFactory<ParseObject>() {
				@SuppressWarnings("unchecked")
				public ParseQuery<ParseObject> create() {
				@SuppressWarnings("rawtypes")
				ParseQuery query = new ParseQuery("TheLog");
				query.whereEqualTo("RYE", rateSpinner);
				query.whereEqualTo("Exercise", activitySpinner);
				return query;
				}});
		adapter2.setTextKey("Date");

     listView.setAdapter(adapter2);
	}
	
	//Navigate to editing screen
	public void onEdit(){
		Intent i = new Intent(this, EditData.class);
    	i.putExtra("DATED", DATED);
    	i.putExtra("EXERCISE", EXERCISE); 
    	i.putExtra("TIME", TIME); 
    	i.putExtra("MILES", MILES);
    	i.putExtra("RYE", RYE);
    	i.putExtra("ID", TheID);
    	startActivity(i);
	}
	//Add new item
	public void onAdd(View view) {
		Intent myIntent = new Intent(this, EditData.class);
		this.startActivity(myIntent);
		}
	//Show all objects 
	public void showAll(View view){
		listView.setAdapter(adapter);
		adapter.loadObjects();
	}
}
