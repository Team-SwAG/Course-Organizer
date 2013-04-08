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

	public void btn_plus(View view) {
		Intent intent = new Intent(this, YearActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	public void plusYear(View view) {
		
		Intent intent = new Intent(this, YearActivity.class);
		startActivity(intent);
		
//		final View addView=getLayoutInflater().inflate(R.layout.activity_inputyear, null);
//		
//		new AlertDialog.Builder(this)
//			.setTitle("Add a Year")
//			.setView(addView)
//			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int whichButton) {
//					ArrayAdapter<String> listAdapter = (ArrayAdapter<String>)getListAdapter();
//					ArrayAdapter<String> adapter = listAdapter;
//					EditText title = (EditText) addView.findViewById(R.id.et_year);
//					
//					adapter.add(title.getText().toString());
//				}
//			})
//			.setNegativeButton("Cancel", null)
//			.show();			
	}
}
