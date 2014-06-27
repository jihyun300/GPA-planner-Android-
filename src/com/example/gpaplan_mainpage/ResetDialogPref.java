package com.example.gpaplan_mainpage;

import android.content.Context;  
import android.content.DialogInterface;  
import android.content.SharedPreferences;  
import android.preference.DialogPreference;  
import android.preference.PreferenceManager;  
import android.util.AttributeSet; 
import android.widget.Toast;

public class ResetDialogPref extends DialogPreference {
	protected Context context;
	public ResetDialogPref(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context= context;
	}
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		super.onClick(dialog, which);
		if(which == DialogInterface.BUTTON_POSITIVE)
		{
			Toast.makeText(context,"OK button Pussed", Toast.LENGTH_SHORT).show();
		}
	}
}
