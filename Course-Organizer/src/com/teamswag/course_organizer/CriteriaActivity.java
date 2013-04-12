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

public class CriteriaActivity extends ListActivity {
	
	ArrayList<String> criteriaList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;
	TextView yearPath;
	TextView semesterPath;
	TextView coursePath;
	String yearName;
	String yearId;
	String semesterName;
	String semesterId;
	String courseName;
	String courseId;

	
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criteria);
		
		db = new DatabaseHelper(this);
		getCriteria();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, criteriaList);
		setListAdapter(aa);
		
		yearPath = (TextView) findViewById(R.id.tv_criteriayear);
		semesterPath = (TextView) findViewById(R.id.tv_criteriasemester);
		coursePath = (TextView) findViewById(R.id.tv_criteriacourse);
		
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			yearName = bundle.getString(YearTable.COLUMN_NAME);
			yearId = bundle.getString(YearTable.COLUMN_ID);
			semesterName = bundle.getString(SemesterTable.COLUMN_NAME);
			semesterId=bundle.getString(SemesterTable.COLUMN_ID);
			courseName = bundle.getString(CourseTable.COLUMN_NAME);
			courseId=bundle.getString(CourseTable.COLUMN_ID);

		}
		
		yearPath.setText(yearName);
		semesterPath.setText(" ->" + semesterName);
		coursePath.setText(" ->" + courseName);
		

		
	}
	
	public void returnToYear (View v){
		Intent path = new Intent(CriteriaActivity.this, YearActivity.class);
		startActivity(path);
	}
	
	public void returnToSemester (View v){
		Intent path = new Intent(CriteriaActivity.this, SemesterActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(YearTable.COLUMN_ID, yearId);
		startActivity(path);
	}
	
	public void returnToCourse (View v){
		Intent path = new Intent(CriteriaActivity.this, CourseActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(YearTable.COLUMN_ID, yearId);
		path.putExtra(SemesterTable.COLUMN_NAME, semesterName);
		path.putExtra(SemesterTable.COLUMN_ID, semesterId);
		startActivity(path);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent items = new Intent(CriteriaActivity.this, ItemActivity.class);
		
		String criteriaName = criteriaList.get(position);
		String criteriaId = CriteriaTable.getId(criteriaName, db);

		items.putExtra(YearTable.COLUMN_NAME, yearName);
		items.putExtra(YearTable.COLUMN_ID, yearId);
		items.putExtra(SemesterTable.COLUMN_NAME, semesterName);
		items.putExtra(SemesterTable.COLUMN_ID, semesterId);
		items.putExtra(CourseTable.COLUMN_NAME, courseName);
		items.putExtra(CourseTable.COLUMN_ID, courseId);
		items.putExtra(CriteriaTable.COLUMN_NAME, criteriaName);
		items.putExtra(CriteriaTable.COLUMN_ID, criteriaId);
		startActivity(items);
	}
	
	public void plusCriteria(View view) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Add a Criterion");
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
	
	private void getCriteria() {
		cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + CriteriaTable.COLUMN_NAME + " FROM "
						+ CriteriaTable.NAME + " ORDER BY "
						+ CriteriaTable.COLUMN_NAME + " ASC", null);
	
		criteriaList.clear();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			criteriaList.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();
	
	}
	
	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

		cv.put(CriteriaTable.COLUMN_NAME, s);
		db.getWritableDatabase().insert(CriteriaTable.NAME, null, cv);
		getCriteria();
		aa.notifyDataSetChanged();
	}
}
