package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class YearActivity extends ListActivity {
	
	ArrayList<String> yearList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,yearList);
		setListAdapter(aa);
	}


	@Override
	public void onResume() {
		super.onResume();
	}
	
	private void plusYear() {
			final View addView=getLayoutInflater().inflate(R.layout.activity_inputyear, null);
			
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
