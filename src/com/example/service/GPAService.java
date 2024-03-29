package com.example.service;

/*
 * created by doyoon
 * dao에서 정보를 빼내와서  메소드로 필요한 부분 구현
 * Ex) 1-1학기 정보를 빼내서(select query)  1-1학기 평점을 구한다
 * 
 * and update by Daehyun 2014 06 29
 * DeleteGpaDb는 db의 내용을 전부삭제하기위해 작성한 메서드
 * GPADao.java의 DeleteAllGpa메소드와 연결됨
 * 
 */
import java.util.*;
import com.example.db.GPADao;
import com.example.db.GPADbHelper;
import com.example.db.GPADto;
import com.example.gpaplan_mainpage.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;

//dao에서 받아온 것들로 서비스로직 구현
public class GPAService {

	public GPADao gpaDao;


	public GPADbHelper gpaDBhelper;
	public SQLiteDatabase gpaDB;

	public static final int TOTAL_SCORE = 0;
	public static final int MAJOR_SCORE = 1;
	public static final int LIBERALARTS_SCORE = 2;
	public static final float init43 =4.3f;
	SharedPreferences s ;
	public int setting;
	public GPAService(Context context) {
		gpaDao = new GPADao(context);
		s = PreferenceManager.getDefaultSharedPreferences(context);
		
		float getScale = Float.parseFloat(s.getString(getString(R.string.savedScale), Float.toString(init43)));
		
		if(getScale==4.3f){
			this.setting=0;
		}
		else{
			this.setting=1;
		}
			
		
		
		//gpaDBhelper = gpaDao.dbHelper;
		//gpaDB = gpaDao.db;
	}

	// dao 여기서부터 정보빼오기

	private String getString(int savedscale) {
		// TODO Auto-generated method stub
		return null;
	}

	// DB에서 전부 빼오기
	public List<GPADto> getAllList() {
		return gpaDao.getAllList();
	}
	//총취득학점
	public int getTotalCredit(){
		return getSumOfCredit();
	}
	//총 평점
	public float getTotalGPA(){
		return getMyTotalGPA();
	}


	// 학년 학기별 성적
	private List<GPADto> getGPADtoList(int year, int semester) {
		return gpaDao.getGPADtoList(year, semester);
	}

	// 성적입력하기 (성적입력 액티비티에서 for문 돌려가면서 이 함수를 반복 수행한다)

	public void insertGPADto(Object gpaDto) {
		gpaDao.insertGPADto(gpaDto);

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
			if(dtoList.get(i).getMajor().equals("교양")){
				liberalCredit += dtoList.get(i).getCredit();
				liberalScore += ConvertService.convertToScore(dtoList.get(i).getGrade(),setting) * (dtoList.get(i).getCredit());
			}
		}
		//교양이나 전공 과목 들은 게 없을 경우에는 -1.0을 리턴 .. 0으로 나누는 케이스를 막기위한 부분
	
		return liberalScore / liberalCredit;
	
	}

	private float getMajorGPA(int year, int semester) {
		List<GPADto> dtoList = getGPADtoList(year,semester);
		int majorCredit = 0;
		float majorScore = 0;
		for(int i=0;i<dtoList.size();i++){
			if(dtoList.get(i).getMajor().equals("전공")){
				majorCredit += dtoList.get(i).getCredit();
				majorScore += ConvertService.convertToScore(dtoList.get(i).getGrade(),setting) * (dtoList.get(i).getCredit());
			}
		}
		//교양이나 전공 과목 들은 게 없을 경우에는 -1.0을 리턴 .. 0으로 나누는 케이스를 막기위한 부분
		
			
		return majorScore / majorCredit;
		
	}

	private float getTotalGPA(int year, int semester) {
		List<GPADto> dtoList = getGPADtoList(year,semester);
		int totalCredit = 0;
		float totalScore = 0;
		for(GPADto dto : dtoList){
			totalCredit += dto.getCredit();
			totalScore += ConvertService.convertToScore(dto.getGrade(),setting) * (dto.getCredit());
		}
		
		//교양이나 전공 과목 들은 게 없을 경우에는 -1.0을 리턴 .. 0으로 나누는 케이스를 막기위한 부분
		
		return totalScore / totalCredit;
		
	}
	private int getSumOfCredit(){
		List<GPADto>  list = getAllList();
		int totalCredit = 0;
		for(GPADto dto : list){
			totalCredit += dto.getCredit();
		}
		return totalCredit;
	}

	private float getMyTotalGPA() {
		List<GPADto> list = getAllList();
		float mytotalGrade = 0;
		int mytotalCredit = 0;
		for(GPADto dto : list){
			mytotalCredit += dto.getCredit();
			mytotalGrade += ConvertService.convertToScore(dto.getGrade(),setting) *( dto.getCredit());
		}
		
		return roundGPA(mytotalGrade,mytotalCredit);
	}
	
	///소수점 둘째자리에서 반올림하기위해 만든 메서드 입니다. 봐주시고 이해 못하겠거나 맘에 안드시면 알려주세요.
	//원래는 그냥 MATH같은 클래스로 구현되어있는 반올림 코드를 사용하고자 했는데 둘째자리까지 반올림이  마땅히 없어서 메서드를 하나 생성해어요.
	private float roundGPA(float grade, int credit){
		float gpa = grade / credit *100;
		gpa = (float) (gpa+0.5);
		gpa = (int)(gpa);
		gpa = (float)(gpa/100);
		return gpa;
	}

	
	//졸업목표학점
	//남은 학기당 맞아야하는 학점 구현하는 메소드   //성적체계 매개변수 필요-->필요없어도 될듯...사용자가 아니까
	public float getTargetGpaPerSemester(int creditForGraduate, float targetGpa) {
		float targetGpaPerSemester = 0.0f;
		float currentGpa = getMyTotalGPA(); 
		int finishedCredit = this.finishedCredit();
		
		targetGpaPerSemester=((targetGpa*creditForGraduate)-(currentGpa*finishedCredit))/
											(creditForGraduate-finishedCredit);
		return Float.parseFloat(String.format("%.2f", targetGpaPerSemester));

	}
	//이수한 학기 구하기/( 필요없는것 같음)
	private int finishedSemester(){
		class YearSemester{
			int year;
			int semester;
			YearSemester(int year,int semester){
				this.year = year;
				this.semester = semester;
			}
		}
		int finishedSemester = 0;
		HashSet<YearSemester> yearSemesterSet = new HashSet<YearSemester>();
		List<GPADto> dtoList = getAllList();
		if(dtoList.isEmpty()){
			return finishedSemester;
		}
		for(GPADto dto : dtoList){
			new YearSemester(dto.getYear(),dto.getSemester());
			yearSemesterSet.add(new YearSemester(dto.getYear(),dto.getSemester()));
		}
		finishedSemester = yearSemesterSet.size();
		return finishedSemester;
	}
	//이수한 학점 구하기
	private int finishedCredit(){
		List<GPADto> dtoList = getAllList();
		int finishedCredit = 0;
		for(GPADto dto : dtoList){
			finishedCredit += dto.getCredit();
		}
		return finishedCredit;
	}
	//학점 달성도 구하기
	public int getGpaAchievement(float targetGpa) {
		float currentGpa = getMyTotalGPA();
		int gpaAchievement = (int)((currentGpa / targetGpa)*100);
		return gpaAchievement>=100 ? 100 : ( (gpaAchievement<=0) ? 0 : gpaAchievement );
	}
	public void DeleteGpaDb(){
		gpaDao.DeleteAllGpa();
	}
}
