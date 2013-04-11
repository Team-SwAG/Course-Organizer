package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SemesterActivity extends ListActivity {

	private ArrayList<String> semesterList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;
	TextView changePathText;
	String yearPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semester);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			yearPath = bundle.getString("yearP");
		}
		
		db = new DatabaseHelper(this);
		getSemesters();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, semesterList);
		setListAdapter(aa);
		
		
		changePathText = (TextView) findViewById(R.id.tv_semesteryear);
		
		changePathText.setText(
			    yearPath);

	}
	
	public void pathYear (View v){
		Intent path = new Intent(SemesterActivity.this, YearActivity.class);
		startActivity(path);;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String semesterP = semesterList.get(position);
		Intent course = new Intent(SemesterActivity.this, CourseActivity.class);
		course.putExtra("semesterP", semesterP);
		course.putExtra("yearP", yearPath);
		startActivity(course);
	}

	public void plusSemester(View view) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Add a Semester");
		final EditText input = new EditText(this);
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


	private void getSemesters() {
		cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + SemesterTable.COLUMN_NAME + " FROM "
						+ SemesterTable.NAME + " ORDER BY "
						+ SemesterTable.COLUMN_NAME + " ASC", null);
	
		semesterList.clear();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			semesterList.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();
	
	}

	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

		cv.put(SemesterTable.COLUMN_NAME, s);
		db.getWritableDatabase().insert(SemesterTable.NAME, null, cv);
		getSemesters();
		aa.notifyDataSetChanged();
	}

}
