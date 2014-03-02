package com.example.gpaplan_mainpage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class SettingActivity_Goal extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_activity__goal);
		
		Intent extraIntent=getIntent();
		String message=extraIntent.getStringExtra(SettingActivity_Scale.EXTRA_MESSAGE);
		
	
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_activity__goal, menu);
		return true;
	}
	
	public void selectedPASS(View view){
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}
	

}
