package com.example.service;

/*
 * created by doyoon
 * dao에서 정보를 빼내와서  메소드로 필요한 부분 구현
 * Ex) 1-1학기 정보를 빼내서(select query)  1-1학기 평점을 구한다
 */
import java.util.*;
import com.example.db.GPADao;
import com.example.db.GPADbHelper;
import com.example.db.GPADto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//dao에서 받아온 것들로 서비스로직 구현
public class GPAService {

	public GPADao gpaDao;
	// public MySetting settingDao; //만들어야함

	public GPADbHelper gpaDBhelper;
	public SQLiteDatabase gpaDB;

	public static final int TOTAL_SCORE = 0;
	public static final int MAJOR_SCORE = 1;
	public static final int LIBERALARTS_SCORE = 2;
	
	public GPAService(Context context) {
		gpaDao = new GPADao(context);
		//gpaDBhelper = gpaDao.dbHelper;
		//gpaDB = gpaDao.db;
	}

	// dao 여기서부터 정보빼오기

	// DB에서 전부 빼오기
	public List<GPADto> getAllList() {
		return gpaDao.getAllList();
	}

	// 학년 학기별 성적
	public List<GPADto> getGPADtoList(int year, int semester) {
		return gpaDao.getGPADtoList(year, semester);
	}

	// 성적입력하기 (성적입력 액티비티에서 for문 돌려가면서 이 함수를 반복 수행한다)
	public void insertOneGPA(Object obj) {
		gpaDao.insertOneGPA(obj);
	}

	// 여기서 부터 dao 에서 뺴온 정보로 비즈니스 로직 담장하는 부분
	// ex) 1-1학기 정보중 교양 학점 계산 등등등

	public float getGPA(int type , int year , int semester){
		switch(type){
		case GPAService.TOTAL_SCORE:
			return getTotalGPA(year,semester);
		case GPAService.MAJOR_SCORE:
			return getMajorGPA(year,semester);
		case GPAService.LIBERALARTS_SCORE:
			return getLiberalArtsGPA(year,semester);
		}
		return 0.0f;
	}
	
	private float getLiberalArtsGPA(int year, int semester) {
		List<GPADto> dtoList = getGPADtoList(year,semester);
		int liberalCredit = 0;
		float liberalScore = 0;
		for(int i=0;i<dtoList.size();i++){
			if(dtoList.get(i).getMajor().equals("liberal")){
				liberalCredit += dtoList.get(i).getCredit();
				liberalScore += ConvertService.convertToScore(dtoList.get(i).getGrade()) * (dtoList.get(i).getCredit());
			}
		}
		return liberalScore / liberalCredit;
	}

	private float getMajorGPA(int year, int semester) {
		List<GPADto> dtoList = getGPADtoList(year,semester);
		int majorCredit = 0;
		float majorScore = 0;
		for(int i=0;i<dtoList.size();i++){
			if(dtoList.get(i).getMajor().equals("major")){
				majorCredit += dtoList.get(i).getCredit();
				majorScore += ConvertService.convertToScore(dtoList.get(i).getGrade()) * (dtoList.get(i).getCredit());
			}
		}
		return majorScore / majorCredit;
	}

	private float getTotalGPA(int year, int semester) {
		List<GPADto> dtoList = getGPADtoList(year,semester);
		int totalCredit = 0;
		float totalScore = 0;
		for(GPADto dto : dtoList){
			totalCredit += dto.getCredit();
			totalScore += ConvertService.convertToScore(dto.getGrade()) * (dto.getCredit());
		}
		return totalScore / totalCredit;
	}
}
