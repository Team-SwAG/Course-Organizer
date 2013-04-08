package com.teamswag.course_organizer;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
	}

	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void plusYear(View view) {
		
		Intent year = new Intent(StartupActivity.this, YearActivity.class);
		startActivity(year);
		
		
	}
}
