package com.example.gpaplan_mainpage;

import android.os.Bundle;
import android.preference.PreferenceManager;
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
		
			Intent intented=new Intent(this,SettingActivity_Target.class);
			startActivity(intented);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			finish();
		
		
	}
	public void selectedOK(View view){
		//졸업이수학점(전공)저장
				String txtGrad_major=editGraduate_major.getText().toString();
				SharedPreferences savedGrad = PreferenceManager.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor=savedGrad.edit();		
				editor.putString(getString(R.string.savedGoalMajor), txtGrad_major);		
				editor.commit();

				
				
				//졸업이수학점(교양)저장
			
			/*//지난시간에 교양 전공 따로 받지 않기로 했음;
			 * 
			 * 	SharedPreferences savedGrad_liberal=getSharedPreferences("SharedSetting",Context.MODE_PRIVATE);
				SharedPreferences savedGrad_liberal = PreferenceManager.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor2=savedGrad_liberal.edit();
				editor2.putString(getString(R.string.savedGoalMajor), txtGrad_major);
				editor2.commit();
				*/
				Intent intented=new Intent(this,SettingActivity_Target.class);
				startActivity(intented);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
				finish();
	}
	
}
