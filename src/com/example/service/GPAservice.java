package com.example.service;

/*
 * created by doyoon
 * dao에서 정보를 빼내와서  메소드로 필요한 부분 구현
 * Ex) 1-1학기 정보를 빼내서(select query)  1-1학기 평점을 구한다
 */
import java.util.*;
import com.example.db.GPAdao;
import com.example.db.GPAdbHelper;
import com.example.db.GPAdto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//dao에서 받아온 것들로 서비스로직 구현
public class GPAservice {

	public GPAdao gpaDao;
	// public MySetting settingDao; //만들어야함

	public GPAdbHelper gpaDBhelper;
	public SQLiteDatabase gpaDB;

	// public MySettingdbHelper settingDBhelper;
	// public SQLiteDatabase settingDB;

	public GPAservice(Context context) {
		gpaDao = new GPAdao(context);
		gpaDBhelper = gpaDao.dbHelper;
		gpaDB = gpaDao.db;

	}

	// dao 여기서부터 정보빼오기

	// DB에서 전부 빼오기
	public List<GPAdto> getAllList() {
		return gpaDao.getAllList();
	}

	// 1-1 성적 빼오기
	public List<GPAdto> getList_1_1() {
		return gpaDao.getList_1_1();
	}

	// 1-2 성적 빼오기
	public List<GPAdto> getList_1_2() {
		return gpaDao.getList_1_2();
	}

	// 2-1 성적 빼오기
	public List<GPAdto> getList_2_1() {
		return gpaDao.getList_2_1();
	}

	// 2-2 성적 빼오기
	public List<GPAdto> getList_2_2() {
		return gpaDao.getList_2_2();
	}

	// 3-1 성적 빼오기
	public List<GPAdto> getList_3_1() {
		return gpaDao.getList_3_1();
	}

	// 3-2 성적 빼오기
	public List<GPAdto> getList_3_2() {
		return gpaDao.getList_3_2();
	}

	// 4-1 성적 빼오기
	public List<GPAdto> getList_4_1() {
		return gpaDao.getList_4_1();
	}

	// 4-2 성적 빼오기
	public List<GPAdto> getList_4_2() {
		return gpaDao.getList_4_2();
	}

	// 쭉 학기 정보 빼오기

	// 성적입력하기 (성적입력 액티비티에서 for문 돌려가면서 이 함수를 반복 수행한다)
	public void insertOneGPA(Object obj) {
		gpaDao.insertOneGPA(obj);
	}

	// 여기서 부터 dao 에서 뺴온 정보로 비즈니스 로직 담장하는 부분
	// ex) 1-1학기 정보중 교양 학점 계산 등등등

	/* 1-1학기 */
	// 이 메소드는 1-1학기의 전체 평점을 구하자...
	public float getGPA_1_1() {
		List<GPAdto> dtoList = getList_1_1();
		int dtoListSize = dtoList.size();
		float gpaScore_1_1 = 0f;
		int totalCredit_1_1 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_1_1 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_1_1 += dtoList.get(i).getCredit();
		}
		dtoList = null; // gc

		return gpaScore_1_1 / totalCredit_1_1;
	}

	// 이 메소드는 1-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_1_1() {
		List<GPAdto> dtoList = getList_1_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_1_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_1_1++;
			}
		}

		float gpaMajorScore_1_1 = 0f;
		int totalMajorCredit_1_1 = 0;

		for (int i = 0; i < majorNum_1_1; i++) {
			gpaMajorScore_1_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_1_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_1_1 / totalMajorCredit_1_1;
	}

	// 1-1 교양 학점 구하기 메소드
	public float getLiberalArt_1_1() {
		List<GPAdto> dtoList = getList_1_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_1_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_1_1++;
			}
		}

		float gpaMajorScore_1_1 = 0f;
		int totalMajorCredit_1_1 = 0;

		for (int i = 0; i < majorNum_1_1; i++) {
			gpaMajorScore_1_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_1_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_1_1 / totalMajorCredit_1_1;
	}

	/* 1-2학기 */
	// 이 메소드는 1-2학기의 전체 평점을 구하자...
	public float getGPA_1_2() {
		List<GPAdto> dtoList = getList_1_2();
		int dtoListSize = dtoList.size();
		float gpaScore_1_2 = 0f;
		int totalCredit_1_2 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_1_2 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_1_2 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_1_2 / totalCredit_1_2;
	}

	// 이 메소드는 1-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_1_2() {
		List<GPAdto> dtoList = getList_1_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_1_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_1_2++;
			}
		}

		float gpaMajorScore_1_2 = 0f;
		int totalMajorCredit_1_2 = 0;

		for (int i = 0; i < majorNum_1_2; i++) {
			gpaMajorScore_1_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_1_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_1_2 / totalMajorCredit_1_2;
	}

	// 1-2 교양 학점 구하기 메소드
	public float getLiberalArt_1_2() {
		List<GPAdto> dtoList = getList_1_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_1_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_1_2++;
			}
		}

		float gpaMajorScore_1_2 = 0f;
		int totalMajorCredit_1_2 = 0;

		for (int i = 0; i < majorNum_1_2; i++) {
			gpaMajorScore_1_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_1_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_1_2 / totalMajorCredit_1_2;
	}

	// 2-1학기
	// 이 메소드는 2-1학기의 전체 평점을 구하자...
	public float getGPA_2_1() {
		List<GPAdto> dtoList = getList_2_1();
		int dtoListSize = dtoList.size();
		float gpaScore_2_1 = 0f;
		int totalCredit_2_1 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_2_1 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_2_1 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_2_1 / totalCredit_2_1;
	}

	// 이 메소드는 2-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_2_1() {
		List<GPAdto> dtoList = getList_2_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_2_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_2_1++;
			}
		}

		float gpaMajorScore_2_1 = 0f;
		int totalMajorCredit_2_1 = 0;

		for (int i = 0; i < majorNum_2_1; i++) {
			gpaMajorScore_2_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_2_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_2_1 / totalMajorCredit_2_1;
	}

	// 2-1 교양 학점 구하기 메소드
	public float getLiberalArt_2_1() {
		List<GPAdto> dtoList = getList_2_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_2_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_2_1++;
			}
		}

		float gpaMajorScore_2_1 = 0f;
		int totalMajorCredit_2_1 = 0;

		for (int i = 0; i < majorNum_2_1; i++) {
			gpaMajorScore_2_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_2_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_2_1 / totalMajorCredit_2_1;
	}

	// 2-2학기
	// 이 메소드는 2-2학기의 전체 평점을 구하자...
	public float getGPA_2_2() {
		List<GPAdto> dtoList = getList_2_2();
		int dtoListSize = dtoList.size();
		float gpaScore_2_2 = 0f;
		int totalCredit_2_2 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_2_2 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_2_2 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_2_2 / totalCredit_2_2;
	}

	// 이 메소드는 2-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_2_2() {
		List<GPAdto> dtoList = getList_2_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_2_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_2_2++;
			}
		}

		float gpaMajorScore_2_2 = 0f;
		int totalMajorCredit_2_2 = 0;

		for (int i = 0; i < majorNum_2_2; i++) {
			gpaMajorScore_2_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_2_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_2_2 / totalMajorCredit_2_2;
	}

	// 2-2 교양 학점 구하기 메소드
	public float getLiberalArt_2_2() {
		List<GPAdto> dtoList = getList_2_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_2_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_2_2++;
			}
		}

		float gpaMajorScore_2_2 = 0f;
		int totalMajorCredit_2_2 = 0;

		for (int i = 0; i < majorNum_2_2; i++) {
			gpaMajorScore_2_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_2_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_2_2 / totalMajorCredit_2_2;
	}

	// 3-1학기
	// 이 메소드는 3-1학기의 전체 평점을 구하자...
	public float getGPA_3_1() {
		List<GPAdto> dtoList = getList_3_1();
		int dtoListSize = dtoList.size();
		float gpaScore_3_1 = 0f;
		int totalCredit_3_1 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_3_1 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_3_1 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_3_1 / totalCredit_3_1;
	}

	// 이 메소드는 3-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_3_1() {
		List<GPAdto> dtoList = getList_3_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_3_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_3_1++;
			}
		}

		float gpaMajorScore_3_1 = 0f;
		int totalMajorCredit_3_1 = 0;

		for (int i = 0; i < majorNum_3_1; i++) {
			gpaMajorScore_3_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_3_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_3_1 / totalMajorCredit_3_1;
	}

	// 3-1 교양 학점 구하기 메소드
	public float getLiberalArt_3_1() {
		List<GPAdto> dtoList = getList_3_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_3_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_3_1++;
			}
		}

		float gpaMajorScore_3_1 = 0f;
		int totalMajorCredit_3_1 = 0;

		for (int i = 0; i < majorNum_3_1; i++) {
			gpaMajorScore_3_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_3_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_3_1 / totalMajorCredit_3_1;
	}

	// 3-2학기
	// 이 메소드는 3-2학기의 전체 평점을 구하자...
	public float getGPA_3_2() {
		List<GPAdto> dtoList = getList_3_2();
		int dtoListSize = dtoList.size();
		float gpaScore_3_2 = 0f;
		int totalCredit_3_2 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_3_2 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_3_2 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_3_2 / totalCredit_3_2;
	}

	// 이 메소드는 3-2학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_3_2() {
		List<GPAdto> dtoList = getList_3_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_3_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_3_2++;
			}
		}

		float gpaMajorScore_3_2 = 0f;
		int totalMajorCredit_3_2 = 0;

		for (int i = 0; i < majorNum_3_2; i++) {
			gpaMajorScore_3_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_3_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_3_2 / totalMajorCredit_3_2;
	}

	// 3-2 교양 학점 구하기 메소드
	public float getLiberalArt_3_2() {
		List<GPAdto> dtoList = getList_3_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_3_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_3_2++;
			}
		}

		float gpaMajorScore_3_2 = 0f;
		int totalMajorCredit_3_2 = 0;

		for (int i = 0; i < majorNum_3_2; i++) {
			gpaMajorScore_3_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_3_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_3_2 / totalMajorCredit_3_2;
	}

	// 4-1학기
	// 이 메소드는 4-1학기의 전체 평점을 구하자...
	public float getGPA_4_1() {
		List<GPAdto> dtoList = getList_4_1();
		int dtoListSize = dtoList.size();
		float gpaScore_4_1 = 0f;
		int totalCredit_4_1 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_4_1 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_4_1 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_4_1 / totalCredit_4_1;
	}

	// 이 메소드는 4-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_4_1() {
		List<GPAdto> dtoList = getList_4_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_4_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_4_1++;
			}
		}

		float gpaMajorScore_4_1 = 0f;
		int totalMajorCredit_4_1 = 0;

		for (int i = 0; i < majorNum_4_1; i++) {
			gpaMajorScore_4_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_4_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_4_1 / totalMajorCredit_4_1;
	}

	// 4-1 교양 학점 구하기 메소드
	public float getLiberalArt_4_1() {
		List<GPAdto> dtoList = getList_4_1();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_4_1 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_4_1++;
			}
		}

		float gpaMajorScore_4_1 = 0f;
		int totalMajorCredit_4_1 = 0;

		for (int i = 0; i < majorNum_4_1; i++) {
			gpaMajorScore_4_1 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_4_1 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_4_1 / totalMajorCredit_4_1;
	}

	// 4-1학기
	// 이 메소드는 4-1학기의 전체 평점을 구하자...
	public float getGPA_4_2() {
		List<GPAdto> dtoList = getList_4_2();
		int dtoListSize = dtoList.size();
		float gpaScore_4_2 = 0f;
		int totalCredit_4_2 = 0;
		for (int i = 0; i < dtoListSize; i++) {
			gpaScore_4_2 += convertToScore(dtoList.get(i).getGrade())
					* (dtoList.get(i).getCredit());
			totalCredit_4_2 += dtoList.get(i).getCredit();
		}

		dtoList = null; // gc
		return gpaScore_4_2 / totalCredit_4_2;
	}

	// 이 메소드는 4-1학기의 전공 평점을 구하자 문제점:전공 교양의 구분을 어떻게 할지 true false or String
	public float getMajorGPA_4_2() {
		List<GPAdto> dtoList = getList_4_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_4_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("major")) {
				newList.add(dtoList.get(i));
				majorNum_4_2++;
			}
		}

		float gpaMajorScore_4_2 = 0f;
		int totalMajorCredit_4_2 = 0;

		for (int i = 0; i < majorNum_4_2; i++) {
			gpaMajorScore_4_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_4_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_4_2 / totalMajorCredit_4_2;
	}

	// 4-2 교양 학점 구하기 메소드
	public float getLiberalArt_4_2() {
		List<GPAdto> dtoList = getList_4_2();
		List<GPAdto> newList = new ArrayList<GPAdto>();

		int dtoListSize = dtoList.size();
		int majorNum_4_2 = 0;
		//
		for (int i = 0; i < dtoListSize; i++) {
			if (dtoList.get(i).getMajor().trim().equals("liberal")) {
				newList.add(dtoList.get(i));
				majorNum_4_2++;
			}
		}

		float gpaMajorScore_4_2 = 0f;
		int totalMajorCredit_4_2 = 0;

		for (int i = 0; i < majorNum_4_2; i++) {
			gpaMajorScore_4_2 += convertToScore(newList.get(i).getGrade())
					* (newList.get(i).getCredit());
			totalMajorCredit_4_2 += newList.get(i).getCredit();
		}

		dtoList = null;
		newList = null;
		return gpaMajorScore_4_2 / totalMajorCredit_4_2;
	}

	public int addAllGpa() {
		gpaDao.getAllList();
		return 0;
	}

	// 학점을 점수로 바꿔주는 메소드?? EX] A+ --> 4.5 다른 클래스로 뺄수잇으면 빼자!
	public float convertToScore(String str) {
		if (str.equals("A+")) {
			return 4.5f;
		} else if (str.equals("A")) {
			return 4.0f;
		} else if (str.equals("B+")) {
			return 3.5f;
		} else if (str.equals("B")) {
			return 3.0f;
		} else if (str.equals("C+")) {
			return 2.5f;
		} else if (str.equals("C")) {
			return 2.0f;
		} else if (str.equals("D+")) {
			return 1.5f;
		} else if (str.equals("D")) {
			return 1.0f;
		} else {
			return 0f;
		}
	}
}
