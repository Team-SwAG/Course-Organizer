package com.teamswag.course_organizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_startup);
	}

	public void btn_plus(View view) {
		Intent intent = new Intent(this, YearActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
}
