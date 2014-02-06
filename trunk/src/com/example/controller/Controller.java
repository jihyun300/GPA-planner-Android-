package com.example.controller;

import android.content.Context;

import com.example.service.GPAService;
import com.example.service.SettingService;

/*
 * created by doyoon
 * GPAservice 의 로직 Settingservice의 로직을 받아와서 액티비티에 뿌림
 * 필요없을수도 있음
 */
public class Controller {
	public GPAService gpaService;
	public SettingService settingService;
	
	//논의 해보기
	public Controller(Context context, int status){
		if(status==1){
			gpaService = new GPAService(context);
			settingService = new SettingService(context);
		}else if(status ==2){
			gpaService = new GPAService(context);
			settingService = null;
		}else{
			gpaService = null;
			settingService = new SettingService(context);
		}
	}
}
