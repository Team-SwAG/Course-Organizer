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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class YearActivity extends ListActivity implements
		OnItemLongClickListener {

	ArrayList<String> yearList = new ArrayList<String>();
	private DatabaseHelper db;
	private Cursor cursor;
	ListView lv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_year);

		ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, yearList);
		setListAdapter(aa);

		lv = getListView();
		lv.setOnItemLongClickListener(this);
		aa.add("1992");
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent semester = new Intent(YearActivity.this,SemesterActivity.class);
		startActivity(semester);
	}


	public void plusYear(View view) {
		
			    AlertDialog.Builder b = new AlertDialog.Builder(this);
			    b.setTitle("Add a Year");
			    final EditText input = new EditText(this);
			    input.setInputType(InputType.TYPE_CLASS_NUMBER);
			    b.setView(input);
			    b.setPositiveButton("OK", new DialogInterface.OnClickListener()
			    {
			        @Override
			        public void onClick(DialogInterface dialog, int whichButton)
			        {
			           // SHOULD NOW WORK
			           processAdd(input.getText().toString());
			        }
			    });
			    b.setNegativeButton("CANCEL", null);
			    b.create().show();
			    
//		LayoutInflater inflater = LayoutInflater.from(this);
//		View addView = inflater.inflate(R.layout.activity_inputyear, null);
//
//		new AlertDialog.Builder(this).setTitle("Add a Year").setView(addView)
//				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						processAdd(((EditText)findViewById(R.id.et_year)).getText().toString());
//						
//					}
//				}).setNegativeButton("Cancel", null).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		new AlertDialog.Builder(this).setTitle("Confirm Delete")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setNegativeButton("Cancel", null).show();
		return true;
	}

	private void processAdd(String s) {
//		ContentValues cv = new ContentValues(1);

//		cv.put(DatabaseHelper.YEAR, s);
//		db.getWritableDatabase().insert("year", DatabaseHelper.YEAR, cv);
//		cursor.requery();
		
		yearList.add(s);
	}

	private void processDelete(long rowId) {
		String[] args = { String.valueOf(rowId) };
		db.getWritableDatabase().delete("year", "_ID=?", args);
	}
	

}
