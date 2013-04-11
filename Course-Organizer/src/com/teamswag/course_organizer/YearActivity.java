package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class YearActivity extends ListActivity {

	private ArrayList<String> yearList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year);

		db = new DatabaseHelper(this);
		getYears();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, yearList);
		setListAdapter(aa);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent semester = new Intent(YearActivity.this, SemesterActivity.class);
		
		String Test = yearList.get(position);
		semester.putExtra("yearP", Test);
		startActivity(semester);
	}

	public void plusYear(View view) {

		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Add a Year");
		final EditText input = new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_NUMBER);
		b.setView(input);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				processAdd(input.getText().toString());
			}
		});
		b.setNegativeButton("CANCEL", null);
		b.create().show();
	}

	private void getYears() {
		cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + YearTable.COLUMN_NAME + " FROM " + YearTable.NAME 
				+ " ORDER BY " + YearTable.COLUMN_NAME + " DESC", null);
	
		yearList.clear();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			yearList.add(String.valueOf(cursor.getInt(0)));
			cursor.moveToNext();
		}
		cursor.close();
	
	}

	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

		cv.put(YearTable.COLUMN_NAME, s);
		db.getWritableDatabase().insert(YearTable.NAME, null, cv);
		getYears();
		aa.notifyDataSetChanged();

	}

}
