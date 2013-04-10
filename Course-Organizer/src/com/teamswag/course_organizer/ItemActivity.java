package com.teamswag.course_organizer;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class ItemActivity extends ListActivity {

	ArrayList<String> itemList = new ArrayList<String>();
	private ArrayAdapter<String> aa;
	private DatabaseHelper db;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);

		db = new DatabaseHelper(this);
		getItems();

		aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, itemList);
		setListAdapter(aa);

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

}
