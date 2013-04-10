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

public class CriteriaActivity extends ListActivity {
	
	ArrayList<String> criteriaList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_criteria);
		
		db = new DatabaseHelper(this);
		getCriteria();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, criteriaList);
		setListAdapter(aa);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(CriteriaActivity.this, ItemActivity.class);
		startActivity(intent);
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
