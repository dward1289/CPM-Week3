package com.parse.starter;


import java.util.Calendar;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;


public class EditData extends Activity {

	String DATED;
	String EXE;
	int MILES;
	String TIME;
	String RYE;
	DatePicker datePicker;
	
	//UI Items
	EditText dateTxt;
	EditText timeTxt;
	EditText mileTxt;
	RadioButton GREAT;
	RadioButton WELL;
	RadioButton TOUGH;
	RadioButton WALK;
	RadioButton RUN;
	RadioButton BOTH;
	Button buttonDate;
	
			
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edited);
		
		//UI Items
		dateTxt = (EditText)findViewById(R.id.dEdit);
		timeTxt = (EditText)findViewById(R.id.TEdit);
		mileTxt = (EditText)findViewById(R.id.MileEdit);
		GREAT = (RadioButton)findViewById(R.id.radioGreat);
		WELL = (RadioButton)findViewById(R.id.radioWell);
		TOUGH = (RadioButton)findViewById(R.id.radioTough);
		WALK = (RadioButton)findViewById(R.id.radioWalk);
		RUN = (RadioButton)findViewById(R.id.radioRun);
		BOTH = (RadioButton)findViewById(R.id.radioBoth);
		
		//Get data for selected item
		Bundle extras = getIntent().getExtras();
		  if (extras != null) {
		
		   DATED = extras.getString("DATED");
		   EXE = extras.getString("EXERCISE"); 
		   MILES =  extras.getInt("MILES"); 
		   TIME = extras.getString("TIME");
		   RYE = extras.getString("RYE"); 
		  }
		  
		  //Set text to to appropriate text fields
		 dateTxt.setText(DATED);
		 timeTxt.setText(TIME);
		 mileTxt.setText(String.valueOf(MILES));
		 
		//Fill in rate of exercise of object selected from list view
		 if(RYE.equals("Great")){
			 GREAT.setChecked(true);
		 }else if(RYE.equals("Well")){
			 WELL.setChecked(true);
		 }else if(RYE.equals("Tough")){
			 TOUGH.setChecked(true);
		 }
		 
		 //Fill in exercise of object selected from list view
		 if(EXE.equals("Walk")){
			 WALK.setChecked(true);
		 }else if(EXE.equals("Run")){
			 RUN.setChecked(true);
		 }else if(EXE.equals("Both")){
			 BOTH.setChecked(true);
		 }
	}
	
	//Date picker created
		 public void selectDate(View view) {
	   	  	DialogFragment newFragment = new SelectDateFragment();
	   	  	newFragment.show(getFragmentManager(), "DatePicker");
	   	  }
	   	  
		 //Display Date in text view
		 public void populateSetDate(int year, int month, int day) {
	   	  	dateTxt.setText(month+"/"+day+"/"+year);
	   	  }
		 
		 //Get date from date picker
	   	  @SuppressLint("ValidFragment")
		  public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	   		  @Override
	   		  public Dialog onCreateDialog(Bundle savedInstanceState) {
	   			  final Calendar calendar = Calendar.getInstance();
	   			  int yy = calendar.get(Calendar.YEAR);
	   			  int mm = calendar.get(Calendar.MONTH);
	   			  int dd = calendar.get(Calendar.DAY_OF_MONTH);
	   			  return new DatePickerDialog(getActivity(), this, yy, mm, dd);
	   	  }
	   	   
	   		  public void onDateSet(DatePicker view, int yy, int mm, int dd) {
	   			  populateSetDate(yy, mm+1, dd);
	   		  }
	   	  }

}


