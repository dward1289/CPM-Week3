package com.parse.starter;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;


public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		  Parse.initialize(this, "XvKaPKfLwGwa89yGHQ8flcU1vrG0qQV8T3I3ytxA", "SManZDRxGK98OogOkkXdU8xbyBk6vLVDtssRPZXT");



		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		
		
	}

}
