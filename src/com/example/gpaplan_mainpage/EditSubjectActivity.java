/**
 * 
 */
package com.example.gpaplan_mainpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.db.GPADto;
import com.example.service.GPAService;

/**
 * @author User
 *
 */
public class EditSubjectActivity extends Activity {
	GPAService	 gservice;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// id를 넘겨줘서 DB로 접근.
		Intent intent = getIntent();
		int DBid=0;
		intent.getIntExtra("DBid", DBid);		
		gservice = new GPAService(getApplicationContext());
		GPADto dto_temp = gservice.gpaDao.getDtoById(DBid);
		setContentView(R.layout.item_view);
		
		Button del_button = (Button) findViewById(R.id.delButton);
		del_button.setVisibility(View.INVISIBLE);
		EditText edit_subject = (EditText)findViewById(R.id.editSubject);
		edit_subject.setText(dto_temp.getSubject());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_ok1 :
			//버튼누를시 수행할 명령문
			return true;
		
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.display_add, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
}
