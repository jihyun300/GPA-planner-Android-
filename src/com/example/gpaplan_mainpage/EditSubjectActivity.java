/**
 * 
 */
package com.example.gpaplan_mainpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

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
	EditText edit_grade  ;
	EditText edit_credit ;
	ToggleButton edit_major;
	GPADto dto_temp;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// id를 넘겨줘서 DB로 접근.
		Intent intent = getIntent();
		int DBid=intent.getIntExtra("DBid", 0);		
		gservice = new GPAService(getApplicationContext());
		dto_temp = gservice.gpaDao.getDtoById(DBid);
		setContentView(R.layout.item_view);
		
		Button del_button = (Button) findViewById(R.id.delButton);
		del_button.setVisibility(View.INVISIBLE);
		edit_subject = (EditText)findViewById(R.id.editSubject);
		edit_grade = (EditText)findViewById(R.id.editGrade);
		edit_credit= (EditText)findViewById(R.id.editCredit);
		edit_major = (ToggleButton)findViewById(R.id.button_MajorOr);
		edit_subject.setText(dto_temp.getSubject());
		edit_grade.setText(dto_temp.getGrade());
		edit_credit.setText(Integer.toString(dto_temp.getCredit()));
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
		dto_temp.setCredit(Integer.parseInt(edit_credit.getText().toString()));
		dto_temp.setSubject(edit_subject.getText().toString());
		dto_temp.setGrade(edit_grade.getText().toString());
		//Major 를 문자형으로 받기
		if(edit_major.isChecked())		
		dto_temp.setMajor("전공");
		else
		dto_temp.setMajor("교양");
		gservice.gpaDao.updateOneGpa(dto_temp.getId(), dto_temp);
		Toast.makeText(getApplicationContext(), "DB에 내용을 업데이트합니다.",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.display_add, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
}
