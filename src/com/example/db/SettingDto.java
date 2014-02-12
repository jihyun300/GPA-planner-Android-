package com.example.db;

public class SettingDto {
	private int settingId; // auto increment
	private int gpaSystem; // 성적체계 4.5 or 4.3    0이면 4.3  1이면 4.5
	private float gpaTarget; // 목표성적
	private int creditForGraduate; // 졸업요구 학점

	public SettingDto(int settingId, int gpaSystem, float gpaTarget,
			int creditForGraduate) {
		super();
		this.settingId = settingId;
		this.gpaSystem = gpaSystem;
		this.gpaTarget = gpaTarget;
		this.creditForGraduate = creditForGraduate;
	}
	

	public SettingDto(int gpaSystem, float gpaTarget, int creditForGraduate) {
		super();
		this.gpaSystem = gpaSystem;
		this.gpaTarget = gpaTarget;
		this.creditForGraduate = creditForGraduate;
	}


	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public int getGpaSystem() {
		return gpaSystem;
	}

	public void setGpaSystem(int gpaSystem) {
		this.gpaSystem = gpaSystem;
	}

	public float getGpaTarget() {
		return gpaTarget;
	}

	public void setGpaTarget(float gpaTarget) {
		this.gpaTarget = gpaTarget;
	}

	public int getCreditForGraduate() {
		return creditForGraduate;
	}

	public void setCreditForGraduate(int creditForGraduate) {
		this.creditForGraduate = creditForGraduate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SettingDto [settingId=");
		builder.append(settingId);
		builder.append(", gpaSystem=");
		builder.append(gpaSystem);
		builder.append(", gpaTarget=");
		builder.append(gpaTarget);
		builder.append(", creditForGraduate=");
		builder.append(creditForGraduate);
		builder.append("]");
		return builder.toString();
	}

}
