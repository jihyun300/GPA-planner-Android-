package com.example.controller;

import android.content.Context;

import com.example.db.GPADto;
import com.example.service.GPAService;


/*
 * created by doyoon
 * GPAservice 의 로직 Settingservice의 로직을 받아와서 액티비티에 뿌림
 * 필요없을수도 있음
 */
public class Controller {
	public GPAService gpaService;
	
    
	// 논의 해보기
	public Controller(Context context) {
		gpaService = new GPAService(context);
	
	}
	public int getTotalCredit(){
		return gpaService.getTotalCredit();
	}
	public float getTotalGPA(){
		return gpaService.getTotalGPA();
	}
	public float getGPA(int type, int year, int semester){
		return gpaService.getGPA(type, year, semester);
	}
	public void insertGPADto(Object gpaDto){
		if( gpaDto instanceof GPADto){
			gpaService.insertGPADto(gpaDto);
		}
	}
	public float getTargetGpaPerSemester(int creditForGraduate, float targetGpa) {
		// TODO Auto-generated method stub
		return gpaService.getTargetGpaPerSemester(creditForGraduate,targetGpa);
	}
	public int getGpaAchievement(float targetGpa) {
		return gpaService.getGpaAchievement(targetGpa);
	}
	public void DeleteGpaDb(){
		gpaService.DeleteGpaDb();
	}
	
}   
