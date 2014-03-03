package com.example.gpaplan_mainpage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SettingActivity_Goal extends Activity {

	TextView editGraduate_major;
	TextView editGraduate_liberal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_activity__goal);
		
		Intent extraIntent=getIntent();
		String message=extraIntent.getStringExtra(SettingActivity_Scale.EXTRA_MESSAGE);
		
	
		
		editGraduate_major=(TextView)findViewById(R.id.editgoal_major);
		editGraduate_major.setText("0");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_activity__goal, menu);
		return true;
	}
	
	public void selectedPASS(View view){
	
		
		//졸업이수학점(전공)저장
		String txtGrad_major=editGraduate_major.getText().toString();
		
		SharedPreferences savedGrad_major=getSharedPreferences("SharedSetting",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=savedGrad_major.edit();		
		editor.putInt(getString(R.string.savedGoalMajor), Integer.parseInt(txtGrad_major));		
		editor.commit();

		//졸업이수학점(교양)저장
	
//		SharedPreferences savedGrad_liberal=getSharedPreferences("savedLiberal_gra",Context.MODE_PRIVATE);
//		SharedPreferences.Editor editor2=savedGrad_liberal.edit();
	
		
		Intent intented=new Intent(this,SettingActivity_Target.class);
		startActivity(intented);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
		finish();
	}
	

}
