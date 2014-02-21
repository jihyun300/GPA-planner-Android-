package com.example.gpaplan_mainpage;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controller.Controller;
import com.example.db.GPADao;
import com.example.db.GPADto;

public class DisplayAddActivity extends Activity implements OnClickListener{
	
	private Controller controller  ;
	private LinearLayout dynamicLayout;
	GPADao gpdb;
	private String[] spinCategory_Year={"1","2","3","4"};
	private String[] spinCategory_Semester={"1","2","여름","겨울"}; 
	
	
	MyAdpater adap;
	
	int Year_from_spinYear;
	int Semeseter_from_spinSemester;
	
	Spinner spinYear;
	Spinner spinSemester;
	TextView selVersionYear;
	TextView selVersionSemester;
	
	ArrayList<Class_info> classInfo;
	public ListView listview;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_add);
		gpdb = new GPADao(getApplicationContext());
		controller = new Controller(getApplicationContext());
		// Show the Up button in the action bar.
		setupActionBar();
		dynamicLayout=(LinearLayout)findViewById(R.id.dynamicArea);
		

		View footView = getLayoutInflater().inflate(R.layout.button_view, null);
		     footView.findViewById(R.id.addButton).setOnClickListener(this);
		   
		     
		     //  footView.findViewById(R.id.delButton).setOnClickListener(this);
		
		classInfo = new ArrayList<Class_info>();
		adap = new MyAdpater(this,classInfo);
		listview = (ListView) findViewById(R.id.listview1);
		listview.addFooterView(footView);
		
		listview.setAdapter(adap);
		//ListView 珥덇린��
		Class_info init_cli = new Class_info("","A+", "0", false);
		classInfo.add(init_cli);
	
		
		
		
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
				Year_from_spinYear =i+1;
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
				Semeseter_from_spinSemester=i+1;
				String selSemster=(String)spinSemester.getSelectedItem();
//				selVersionYear.setText("Selected Android OS:"+selYear);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
	});

	}
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_add, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_ok1:
			openOK();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void openOK() {
		// TODO Auto-generated method stub

		
		Toast.makeText(this, "DB에 내용을 추가합니다.", Toast.LENGTH_SHORT).show();
	//GPADto temp = new GPADto( 1, 1, 3, "3.2", "�꾧났", "�듭떊�대줎");
	
	//Iterator iter= classInfo.iterator();
	for(int i =0; i<classInfo.size();i++){
		Class_info ci_temp = classInfo.get(i);
		GPADto dto_temp= new GPADto(Year_from_spinYear,Semeseter_from_spinSemester,
				Integer.parseInt(ci_temp.getCredit()),ci_temp.getGrade(),
				ci_temp.getMajorString(),ci_temp.getSubject());

		controller.insertGPADto(dto_temp);

		}
	}
	
	/**
	 * 占쏙옙占싻놂옙 占쏙옙占싻깍옙 占쌉뤄옙占싹댐옙 占쌉쇽옙
	 */
	public void addLayout(){		
		
		Class_info cl_temp = new Class_info("", "A+", "0", false);
		classInfo.add(cl_temp);
		adap.notifyDataSetChanged();
	
	/*	
		gpdb.db  = gpdb.dbHelper.getWritableDatabase();
		gpdb.db.execSQL("DELETE FROM gpa_table;"); //db없애는 명령어
		gpdb.db.close();
*/
	/*	
======
		
		/*
>>>>>>> .r55
	gpdb.db = gpdb.dbHelper.getWritableDatabase();
	gpdb.db.execSQL("DELETE FROM gpa_table;"); //db없애기
	gpdb.db.close();
	*/
		/*EditText editgrade=(EditText)findViewById(R.id.editGrade);
		EditText editcredit=(EditText)findViewById(R.id.editCredit);
		EditText editsubject=(EditText)findViewById(R.id.editSubject);
		ToggleButton editMajor=(ToggleButton)findViewById(R.id.button_MajorOr);
		
		String inputValue1=editsubject.getText().toString();
		String inputValue2=editgrade.getText().toString();
		String inputValue3=editcredit.getText().toString();
		boolean inputValue4=editMajor.isChecked();
		
		refresh(inputValue1,inputValue2,inputValue3,inputValue4);*/
//		editgrade.setText("");
//		editcredit.setText("");
//		editsubject.setText("");
//		editMajor.setChecked(false);
		
	}

	/*private void refresh(String inputValue1,String inputValue2,String inputValue3,boolean inputValue4) {
			
		// TODO Auto-generated method stub
		Class_info cl1 = new Class_info(inputValue1,inputValue2,inputValue3,inputValue4);
		this.classInfo.add(cl1);
		adap.notifyDataSetChanged();
		
	}*/

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addButton:
			addLayout();
			break;
		case R.id.delButton:
			Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
	}

/*	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		spinYear.setSelection(position);
		String selYear=(String)spinYear.getSelectedItem();
		selVersionYear.setText("Selected Android OS:"+selYear);
	}*/
}
