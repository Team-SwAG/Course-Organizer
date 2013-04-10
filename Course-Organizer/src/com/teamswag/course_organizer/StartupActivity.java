package com.teamswag.course_organizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class StartupActivity extends Activity {

	private DatabaseHelper db;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
		db = new DatabaseHelper(this);
		cursor = db.getReadableDatabase().rawQuery(
				"SELECT " + YearTable.COLUMN_NAME + " FROM " + YearTable.NAME,
				null);
		if (cursor.getCount() != 0) {
			Intent intent = new Intent(StartupActivity.this,
					YearActivity.class);
			startActivity(intent);
			cursor.close();
			finish();
		}
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
				Intent intent = new Intent(StartupActivity.this,
						YearActivity.class);
				startActivity(intent);
			}
		});
		b.setNegativeButton("CANCEL", null);
		b.create().show();
	}

	private void processAdd(String s) {
		ContentValues cv = new ContentValues(1);

		cv.put(YearTable.COLUMN_NAME, s);
		db.getWritableDatabase().insert(YearTable.NAME, null, cv);
	}
}
