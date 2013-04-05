package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class YearActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year);
	}


	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void plusYear() {
			final View addView=getLayoutInflater().inflate(R.layout.activity_input_year, null);
			
			new AlertDialog.Builder(this)
				.setTitle("Add a Year")
				.setView(addView)
				.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						ArrayAdapter<String> adapter = (ArrayAdapter<String>)getListAdapter();
						EditText title = (EditText)addView.findViewById(R.id.tv_year);
						
						adapter.add(title.getText().toString());
					}
				})
				.setNegativeButton("Cancel", null)
				.show();
										
								
	}
}
