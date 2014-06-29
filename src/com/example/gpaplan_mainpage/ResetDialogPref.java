package com.example.gpaplan_mainpage;

import com.example.controller.Controller;

import android.content.Context;  
import android.content.DialogInterface;  
import android.content.SharedPreferences;  
import android.preference.DialogPreference;  
import android.preference.PreferenceManager;  
import android.util.AttributeSet; 
import android.widget.Toast;

public class ResetDialogPref extends DialogPreference {
	protected Context context;
	protected Controller controller;
	public ResetDialogPref(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context= context;
		controller = new Controller(context);
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		super.onClick(dialog, which);
		if(which == DialogInterface.BUTTON_POSITIVE)
		{
			try{
			controller.DeleteGpaDb();
			}
			catch(Exception e){
				Toast.makeText(context,"DB삭제에서 오류 발생!", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}finally{
				Toast.makeText(context,"DB의 내용이 전부 삭제되었습니다.", Toast.LENGTH_SHORT).show();	
			}
			
		}
	}
}
