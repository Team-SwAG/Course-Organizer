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
import android.widget.TextView;

public class ItemActivity extends ListActivity {

	ArrayList<String> itemList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;
	TextView yearPath;
	TextView semesterPath;
	TextView coursePath;
	TextView criteriaPath;
	String yearName;
	String yearId;
	String semesterName;
	String semesterId;
	String courseName;
	String courseId;
	String criteriaName;
	String criteriaId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		db = new DatabaseHelper(this);
		getItems();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, itemList);
		setListAdapter(aa);

		yearPath = (TextView) findViewById(R.id.tv_itemyear);
		semesterPath = (TextView) findViewById(R.id.tv_itemsemester);
		coursePath = (TextView) findViewById(R.id.tv_itemcourse);
		criteriaPath = (TextView) findViewById(R.id.tv_itemcriteria);
	
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			yearName = bundle.getString(YearTable.COLUMN_NAME);
			yearId = bundle.getString(YearTable.COLUMN_ID);
			semesterName = bundle.getString(SemesterTable.COLUMN_NAME);
			semesterId=bundle.getString(SemesterTable.COLUMN_ID);
			courseName = bundle.getString(CourseTable.COLUMN_NAME);
			courseId=bundle.getString(CourseTable.COLUMN_ID);
			criteriaName = bundle.getString(CriteriaTable.COLUMN_NAME);
			criteriaId=bundle.getString(CriteriaTable.COLUMN_ID);

		}
		yearPath.setText(yearName);
		semesterPath.setText(" ->" + semesterName);
		coursePath.setText(" ->" + courseName);
		criteriaPath.setText(" ->" + criteriaName);
	
	
	}
	
	public void plusItem(View view) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Add an Item");
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
	
	private void getItems() {
		cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + ItemTable.COLUMN_NAME + " FROM "
						+ ItemTable.NAME + " ORDER BY "
						+ ItemTable.COLUMN_NAME + " ASC", null);
	
		itemList.clear();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			itemList.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();
	
	}
	
	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

		cv.put(ItemTable.COLUMN_NAME, s);
		db.getWritableDatabase().insert(ItemTable.NAME, null, cv);
		getItems();
		aa.notifyDataSetChanged();
	}
	public void returnToYear (View v){
		Intent path = new Intent(ItemActivity.this, YearActivity.class);
		startActivity(path);
	}
	
	public void returnToSemester (View v){
		Intent path = new Intent(ItemActivity.this, SemesterActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(YearTable.COLUMN_ID, yearId);
		startActivity(path);
	}
	
	public void returnToCourse (View v){
		Intent path = new Intent(ItemActivity.this, CourseActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(YearTable.COLUMN_ID, yearId);
		path.putExtra(SemesterTable.COLUMN_NAME, semesterName);
		path.putExtra(SemesterTable.COLUMN_ID, semesterId);
		startActivity(path);
	}
	public void returnToCriteria (View v){
		Intent path = new Intent(ItemActivity.this, CriteriaActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(YearTable.COLUMN_ID, yearId);
		path.putExtra(SemesterTable.COLUMN_NAME, semesterName);
		path.putExtra(SemesterTable.COLUMN_ID, semesterId);
		path.putExtra(CourseTable.COLUMN_NAME, courseName);
		path.putExtra(CourseTable.COLUMN_ID, courseId);
		path.putExtra(CriteriaTable.COLUMN_NAME, criteriaName);
		path.putExtra(CriteriaTable.COLUMN_ID, criteriaId);
		startActivity(path);
	}

}
