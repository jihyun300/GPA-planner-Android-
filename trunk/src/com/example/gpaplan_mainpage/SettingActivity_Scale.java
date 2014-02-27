package com.example.gpaplan_mainpage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class SettingActivity_Scale extends Activity {

	public final static String EXTRA_MESSAGE="com.example.gpaplan_mainpage";
	LinearLayout scaleLayout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this,SplashActivity.class));
		setContentView(R.layout.activity_setting_activity__scale);		
//		scaleLayout=(LinearLayout)findViewById(R.id.scaleLayout);
//		View scaleView= getLayoutInflater().inflate(R.layout.activity_setting_activity__scale,null);
/*		scaleView.findViewById(R.id.buttScale430).setOnClickListener(this);
		scaleView.findViewById(R.id.buttScale450).setOnClickListener(this);
*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting_activity__scale, menu);
		return true;
	}

/*	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttScale430:
			selected430();
			break;
		case R.id.buttScale450:
			selected450();
			break;
			default:
				break;
		}
		
	}
*/	
	
	public void selected450(View view) {
		// TODO Auto-generated method stub
		
		Intent intent=new Intent(this,SettingActivity_Goal.class);
		String message="4.5";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
		
		
		
	}

	public void selected430(View view) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,SettingActivity_Goal.class);
		String message="4.3";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);

	}

}
