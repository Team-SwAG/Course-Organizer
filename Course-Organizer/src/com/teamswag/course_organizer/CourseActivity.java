package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CourseActivity extends ListActivity implements
		OnItemLongClickListener {

	ArrayList<String> courseList = new ArrayList<String>();
	ArrayAdapter<String> aa;
	DatabaseHelper db;
	Cursor cursor;
	ListView lv;
	TextView yearPath;
	TextView semesterPath;
	String yearName;
	String yearId;
	String semesterName;
	String semesterId;
	TextView courseTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);

		yearPath = (TextView) findViewById(R.id.tv_yearpathcourse);
		semesterPath = (TextView) findViewById(R.id.tv_semesterpath);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {

			semesterName = bundle.getString(SemesterTable.COLUMN_NAME);
			semesterId = bundle.getString(CourseTable.COLUMN_SEMESTER_ID);
			yearName = bundle.getString(YearTable.COLUMN_NAME);
			yearId = bundle.getString(CourseTable.COLUMN_YEAR_ID);
		}
		yearPath.setText(yearName);
		semesterPath.setText("->" + semesterName);

		db = new DatabaseHelper(this);
		populateList();

		aa = new CourseAdapter();
		setListAdapter(aa);

		lv = getListView();
		lv.setOnItemLongClickListener(this);

	}

	class CourseAdapter extends ArrayAdapter<String> {
		CourseAdapter() {
			super(CourseActivity.this, R.layout.row, R.id.tv_leftvalue,
					courseList);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View row = super.getView(position, convertView, parent);
			TextView right = (TextView) row.findViewById(R.id.tv_rightvalue);
			right.setText("TEST GRADE");
			return row;
		}
	}

	public void returnToYear(View v) {
		Intent path = new Intent(CourseActivity.this, YearActivity.class);
		startActivity(path);
		finish();
	}

	public void returnToSemester(View v) {
		Intent path = new Intent(CourseActivity.this, SemesterActivity.class);
		path.putExtra(YearTable.COLUMN_NAME, yearName);
		path.putExtra(SemesterTable.COLUMN_YEAR_ID, yearId);
		startActivity(path);
		finish();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String courseName = courseList.get(position);
		String courseId = CourseTable.getId(courseName, semesterId, db);

		Intent criteria = new Intent(CourseActivity.this,
				CriteriaActivity.class);
		criteria.putExtra(YearTable.COLUMN_NAME, yearName);
		criteria.putExtra(CourseTable.COLUMN_YEAR_ID, yearId);
		criteria.putExtra(SemesterTable.COLUMN_NAME, semesterName);
		criteria.putExtra(CourseTable.COLUMN_SEMESTER_ID, semesterId);
		criteria.putExtra(CourseTable.COLUMN_NAME, courseName);
		criteria.putExtra(CriteriaTable.COLUMN_COURSE_ID, courseId);
		startActivity(criteria);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		final String name = courseList.get(position);

		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle(R.string.confirm_delete);
		b.setPositiveButton(R.string.delete,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						CourseTable.delete(name, semesterId, db);
						populateList();
						aa.notifyDataSetChanged();
					}
				});
		b.setNegativeButton(android.R.string.cancel, null);
		b.create().show();

		return true;
	}

	public void plusCourse(View view) {

		LayoutInflater inflater = LayoutInflater.from(this);
		View inputLayout = inflater.inflate(R.layout.activity_inputgradescale,
				null);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.input_grade_scale);
		builder.setView(inputLayout);
		final EditText coursename = (EditText) inputLayout
				.findViewById(R.id.et_inputcoursename);
		final EditText aPlus = (EditText) inputLayout.findViewById(R.id.a_plus);
		final EditText a = (EditText) inputLayout.findViewById(R.id.a);
		final EditText aMinus = (EditText) inputLayout
				.findViewById(R.id.a_minus);
		final EditText bPlus = (EditText) inputLayout.findViewById(R.id.b_plus);
		final EditText b = (EditText) inputLayout.findViewById(R.id.b);
		final EditText bMinus = (EditText) inputLayout
				.findViewById(R.id.b_minus);
		final EditText cPlus = (EditText) inputLayout.findViewById(R.id.c_plus);
		final EditText c = (EditText) inputLayout.findViewById(R.id.c);
		final EditText cMinus = (EditText) inputLayout
				.findViewById(R.id.c_minus);
		final EditText dPlus = (EditText) inputLayout.findViewById(R.id.d_plus);
		final EditText d = (EditText) inputLayout.findViewById(R.id.d);
		final EditText dMinus = (EditText) inputLayout
				.findViewById(R.id.d_minus);
		builder.setPositiveButton(android.R.string.ok,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						String string_coursename = coursename.getText()
								.toString();
						CourseTable.add(string_coursename, semesterId, yearId,
								db);
						String courseId = CourseTable.getId(string_coursename,
								semesterId, db);

						GradeScaleTable.add(courseId, aPlus.getText()
								.toString(), a.getText().toString(), aMinus
								.getText().toString(), bPlus.getText()
								.toString(), b.getText().toString(), bMinus
								.getText().toString(), cPlus.getText()
								.toString(), c.getText().toString(), cMinus
								.getText().toString(), dPlus.getText()
								.toString(), d.getText().toString(), dMinus
								.getText().toString(), db);

						populateList();
						aa.notifyDataSetChanged();
					}
				});
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.create().show();

	}

	private void populateList() {
		cursor = db.getReadableDatabase()
				.rawQuery(
						"SELECT " + CourseTable.COLUMN_NAME + " FROM "
								+ CourseTable.NAME + " WHERE "
								+ CourseTable.COLUMN_YEAR_ID + "=" + yearId
								+ " AND " + CourseTable.COLUMN_SEMESTER_ID
								+ "=" + semesterId + " ORDER BY "
								+ CourseTable.COLUMN_NAME + " ASC", null);

		courseList.clear();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			courseList.add(cursor.getString(0));
			cursor.moveToNext();
		}
		cursor.close();

	}

}
