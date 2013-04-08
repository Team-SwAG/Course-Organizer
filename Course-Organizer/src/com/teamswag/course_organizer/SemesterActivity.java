package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class SemesterActivity extends ListActivity {

	ArrayList<String> semesterList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inputsemester);
		
		ArrayAdapter<String> bb = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,semesterList);
		setListAdapter(bb);

	}
	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void plusSemester() {
			final View addView=getLayoutInflater().inflate(R.layout.activity_inputsemester, null);
			
			new AlertDialog.Builder(this)
				.setTitle("Add a Semester")
				.setView(addView)
				.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						ArrayAdapter<String> adapter = (ArrayAdapter<String>)getListAdapter();
						EditText title = (EditText)addView.findViewById(R.id.tv_semester);
						
						adapter.add(title.getText().toString());
					}
				})
				.setNegativeButton("Cancel", null)
				.show();
										
								
	}
}
}
