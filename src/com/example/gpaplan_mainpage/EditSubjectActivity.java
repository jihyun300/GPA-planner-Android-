/**
 * 
 */
package com.example.gpaplan_mainpage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.controller.Controller;
import com.example.db.GPADto;
import com.example.service.GPAService;

/**
 * @author User
 *
 */
public class EditSubjectActivity extends Activity {
	GPAService	 gservice;
	EditText edit_subject ;
	ToggleButton edit_major;
	NumberPicker edit_Grades;
	NumberPicker edit_Credit;
	Spinner spinYear;
	Spinner spinSemester;
	GPADto dto_temp;
	private String[] GradeList=new String[]{"A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F","P","NP"};
	private String[] GradeList45=new String[]{"A+","A","B+","B","C+","C","D+","D","F","P","NP"};
	private float getScale;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	private String[] spinCategory_Year={"1","2","3","4"};
	private String[] spinCategory_Semester={"1","2","여름","겨울"}; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// id를 넘겨줘서 DB로 접근.
		Intent intent = getIntent();
		int DBid=intent.getIntExtra("DBid", 0);		
		gservice = new GPAService(getApplicationContext());
		dto_temp = gservice.gpaDao.getDtoById(DBid);
		setContentView(R.layout.activity_display_edit);
		ArrayAdapter<String> adpSpin=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinCategory_Year);
		ArrayAdapter<String> adpSpin_semester=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinCategory_Semester);
		adpSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adpSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		 spinYear=(Spinner)findViewById(R.id.spinner_Year);
		 spinSemester=(Spinner)findViewById(R.id.spinner_Semester);
		spinYear.setAdapter(adpSpin);
		
		spinYear.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				// TODO Auto-generated method stub
				spinYear.setSelection(i);
		
				String selYear=(String)spinYear.getSelectedItem();
				
//				selVersionYear.setText("Selected Android OS:"+selYear);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
			
		});
		
		
		spinSemester.setAdapter(adpSpin_semester);
		spinSemester.setOnItemSelectedListener(new OnItemSelectedListener(){
		
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				// TODO Auto-generated method stub
				spinSemester.setSelection(i);
		
				String selSemster=(String)spinSemester.getSelectedItem();
//				selVersionYear.setText("Selected Android OS:"+selYear);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
	});

		Button del_button = (Button) findViewById(R.id.delButton);
		del_button.setVisibility(View.INVISIBLE);
		edit_subject = (EditText)findViewById(R.id.editSubject);
		
		
		//4.3 4.5인지 읽기
		SharedPreferences savedScale=getSharedPreferences("savedScale",MODE_PRIVATE);
		float init43=4.3f;
		getScale=savedScale.getFloat(getString(R.string.savedScale),init43);
		
		
		edit_Grades = (NumberPicker)findViewById(R.id.editGrades);
		edit_Credit=(NumberPicker)findViewById(R.id.editCredit);
		
		int getGradeInt=0;
		if(getScale==4.3f){
		edit_Grades.setMinValue(0);
		edit_Grades.setMaxValue(14);
		edit_Grades.setDisplayedValues(GradeList);
		for(int i=0;i<GradeList.length;i++)
			if(dto_temp.getGrade().equals(GradeList[i])) getGradeInt=i;
		
		edit_Grades.setValue(getGradeInt);
		}
		else{
			edit_Grades.setMinValue(0);
			edit_Grades.setMaxValue(10);
			edit_Grades.setDisplayedValues(GradeList45);
			for(int i=0;i<GradeList45.length;i++)
				if(dto_temp.getGrade().equals(GradeList45[i])) getGradeInt=i;
		}
		
		edit_Credit.setMinValue(0);
		edit_Credit.setMaxValue(5);
		
		
	
		edit_Credit.setValue(dto_temp.getCredit());
		spinYear.setSelection(dto_temp.getYear()-1);
		spinSemester.setSelection(dto_temp.getSemester()-1);
		edit_major = (ToggleButton)findViewById(R.id.button_MajorOr);
		edit_subject.setText(dto_temp.getSubject());
		edit_major.setChecked(MajorToBoolean(dto_temp.getMajor()));
	}
	private boolean MajorToBoolean(String s) {
		if(s.equals("교양"))
			return false;
		else
			return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_ok1 :
			//버튼누를시 수행할 명령문
			updateDB();
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case android.R.id.home:
					NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//수정버튼을 눌렀을때 수행되는 FUNCTION
	private void updateDB(){
		dto_temp.setCredit(edit_Credit.getValue());
		dto_temp.setSubject(edit_subject.getText().toString());
		if(getScale==4.3f)
			dto_temp.setGrade(GradeList[edit_Grades.getValue()]);
		else
			dto_temp.setGrade(GradeList45[edit_Grades.getValue()]);
		//Major 를 문자형으로 받기
		if(edit_major.isChecked())		
		dto_temp.setMajor("전공");
		else
		dto_temp.setMajor("교양");
		dto_temp.setYear(spinYear.getSelectedItemPosition()+1);
		dto_temp.setSemester(spinSemester.getSelectedItemPosition()+1);
		gservice.gpaDao.updateOneGpa(dto_temp.getId(), dto_temp);
		Toast.makeText(getApplicationContext(), spinYear.getSelectedItemPosition()+" "+spinSemester.getSelectedItemPosition()+" DB에 내용을 업데이트합니다.",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.display_add, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
}
