package com.example.service;

public class ConvertService {

	public static float convertToScore(String str) {
		if (SettingService.gpaSys == 0) {
			return convertToScore(str);
		} else {
			return convertToScore(str);
		}
	}

	private static float convertToScore43(String str) {
		if (str.equals("A+")) {
			return 4.3f;
		} else if (str.equals("A")) {
			return 4.0f;
		} else if (str.equals("A-")) {
			return 3.7f;
		} else if (str.equals("B+")) {
			return 3.3f;
		} else if (str.equals("B")) {
			return 3.0f;
		} else if (str.equals("B-")) {
			return 2.7f;
		} else if (str.equals("D+")) {
			return 2.3f;
		} else if (str.equals("D")) {
			return 2.0f;
		} else if (str.equals("D-")) {
			return 1.7f;
		} else {
			return 0f;
		}

	}

	private static float convertToScore45(String str) {
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

	// if(settingService.)
}
