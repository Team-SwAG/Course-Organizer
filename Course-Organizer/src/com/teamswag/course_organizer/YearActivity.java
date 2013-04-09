package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class YearActivity extends ListActivity {
	
	ArrayList<String> yearList = new ArrayList<String>();
	private DatabaseHelper db;
	private Cursor cursor;
	
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
		LayoutInflater inflater = LayoutInflater.from(this);
		View addView = inflater.inflate(R.layout.activity_inputyear, null);
		final DialogWrapper wrapper = new DialogWrapper(addView);
			
			new AlertDialog.Builder(this)
				.setTitle("Add a Year")
				.setView(addView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						processAdd(wrapper);
					}
				})
				.setNegativeButton("Cancel", null)
				.show();										
	}
	
	private void processAdd(DialogWrapper wrapper) {
		ContentValues cv = new ContentValues(1);
		
		cv.put(DatabaseHelper.YEAR, wrapper.getYear());
		db.getWritableDatabase().insert("year", DatabaseHelper.YEAR, cv);
	}
	
	private void processDelete(long rowId) {
		String[] args = {String.valueOf(rowId)};
		db.getWritableDatabase().delete("year", "_ID=?", args);
	}
	
	class DialogWrapper {
		EditText year;
		View base;
		
		public DialogWrapper(View base) {
			this.base = base;
			year = (EditText) findViewById(R.id.et_year);
		}
		
		String getYear() {
			return getField().getText().toString(); 
		}
		
		private EditText getField() {
			if (year == null)
				year = (EditText) base.findViewById(R.id.et_year);
			return year;
		}
	}
}
