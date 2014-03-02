package com.example.gpaplan_mainpage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SettingActivity_Target extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_activity__target);
		
		
		//저장된 졸업이수학점 받아오기(Read)
		 //졸업 이수학점(전공)이 저장된 곳이 "savedMajor_gra"
			SharedPreferences savedGrad_major=getSharedPreferences("savedMajor_gra",Context.MODE_PRIVATE);
			int getGrad_major;
			int defaultNumber=0;//아무것도 저장되지 않았을때의 값
			getGrad_major=savedGrad_major.getInt(getString(R.string.savedGoalMajor),defaultNumber);
			
		//졸업 이수학점(교양)이 저장된 곳이 "savedLiberal_gra"

			SharedPreferences savedGrad_liberal=getSharedPreferences("savedLiberal_gra",Context.MODE_PRIVATE);
			int getGrad_liberal;
			getGrad_liberal=savedGrad_liberal.getInt(getString(R.string.savedGoalLiberal),defaultNumber);

			TextView textMajor=(TextView)findViewById(R.id.txtGraduate_major);
			TextView textLiberal=(TextView)findViewById(R.id.txtGraduate_liberal);
			textMajor.setText(Integer.toString(getGrad_major));
			textLiberal.setText(Integer.toString(getGrad_liberal));
		//getGrad_major와 getGrad_liberal 값 사용하면 됨!!	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_activity__target, menu);
		return true;
	}
	public void selectedTarget(View view){
		
		EditText editTarget=(EditText)findViewById(R.id.editTarget);
		String targetScore=editTarget.getText().toString();
		
		//목표학점 저장
		SharedPreferences savedTarget=getSharedPreferences("savedTarget",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=savedTarget.edit();
		editor.putFloat(getString(R.string.savedTarget),Float.parseFloat(targetScore));
		editor.commit();
		
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		finish();
	}

}
